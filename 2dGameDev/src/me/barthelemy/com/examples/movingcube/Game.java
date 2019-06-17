package me.barthelemy.com.examples.movingcube;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferStrategy;

public class Game implements Runnable {
	private Color backgroundGrey = new Color(90,82,85);
	private Color spriteBlue = new Color(27,133,184);
	private Color spriteRed = new Color(85,158,131);
	
	
	private Launcher l;
	private BufferStrategy bs;
	private Graphics g;
	private Thread thread;
	private Boolean running = false;
	private KeyboardManager keyManager;

	private int posY = 0;
	private int posX = 0;

	private int posXvel = 0;
	private int posYvel = 0;

	public Game() {
		l = new Launcher();
	}

	public synchronized void start() {
		if (running)
			return;

		running = true;
		thread = new Thread(this);
		thread.start();
	}

	public synchronized void stop() {
		if (!running)
			return;
		running = false;
		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	private void render() {
		bs = l.getCanvas().getBufferStrategy();
		if (bs == null) {
			l.getCanvas().createBufferStrategy(3);
			return;
		}
		g = bs.getDrawGraphics();

		g.setColor(backgroundGrey);
		g.fillRect(0, 0, 900, 600);

		// Rendering

		g.setColor(spriteRed);
		g.fillRect(posX, posY, 50, 50);

		bs.show();
		g.dispose();

	}

	@Override
	public void run() {
		init();
		long initialTime = System.nanoTime();
		int UPS = 20;
		int FPS = 60;
		final double timeU = 1000000000 / UPS;
		final double timeF = 1000000000 / FPS;
		double deltaU = 0, deltaF = 0;
		int frames = 0, ticks = 0;
		long timer = System.currentTimeMillis();

		while (running) {

			long currentTime = System.nanoTime();
			// DELTA = "CHANGE IN" (also represented by a triangle in physics)
			deltaU += (currentTime - initialTime) / timeU;
			deltaF += (currentTime - initialTime) / timeF;
			initialTime = currentTime;

			if (deltaU >= 1) {
				/*
				 * UPDATES INPUT "UPS" - GAMES LIKE OVERWATCH USE 60, GAMES LIKE MINECRAFT USE
				 * 20 ALSO REFERED TO AS TICKS PER SECOND (TPS)
				 */
				tick();
				ticks++;
				deltaU--;
			}

			if (deltaF >= 1) {
				// UPDATE YOU "FRAME" (RUNS 60 FRAMES PER SECOND)
				render();
				frames++;
				deltaF--;
			}

			if (System.currentTimeMillis() - timer > 1000) {
				// PRINTS OUT FRAMERATE AND TICKS PER SECOND. (SHOULD BE UPS AND
				// FPS RESPECTIVLY)
				// System.out.println(String.format("UPS(TPS): " + ticks +" FPS: " + frames));
				frames = 0;
				ticks = 0;
				timer += 1000;
			}
		}

		stop();
	}

	private void tick() {
		keyManager.tick();
		/*
		 * BASIC if(keyManager.w) posY--; if(keyManager.s) posY++; if(keyManager.a)
		 * posX--; if(keyManager.d) posX++;
		 */

		// MORE ADVANCED (WITH ACCELERATION LIKE) (real world like)
		if (Math.abs(posXvel) < 10) {
			if (keyManager.a)
				posXvel -= 2;
			if (keyManager.d)
				posXvel += 2;
		}
		if (Math.abs(posYvel) < 10) {
			if (keyManager.w)
				posYvel -= 2;
			if (keyManager.s)
				posYvel += 2;
		}
		
		posX += posXvel;
		posY += posYvel;

		if (posXvel > 0)
			posXvel--;
		if (posXvel < 0)
			posXvel++;
		if (posYvel > 0)
			posYvel--;
		if (posYvel < 0)
			posYvel++;
	}

	private void init() {
		keyManager = new KeyboardManager();
		l.getFrame().addKeyListener(keyManager);
		l.getCanvas().addKeyListener(keyManager);
	}

}
