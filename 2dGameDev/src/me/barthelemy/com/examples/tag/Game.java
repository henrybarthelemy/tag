package me.barthelemy.com.examples.tag;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.util.Random;

public class Game implements Runnable {
	private Color backgroundGrey = new Color(90,82,85);
	private Color spriteBlue = new Color(27,133,184);
	private Color spriteGreen = new Color(85,158,131);
	
	
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
	
	public final int width = 900;
	public final int height = 600;
	
	private String countdownString;
	private int countdown = 0;

	private Rectangle player1;
	private Rectangle player2;
	
	public Game() {
		l = new Launcher("Tag Game!", width, height);
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
		
		g.setColor(Color.BLACK);
		g.drawString(countdownString, 10, 10);

		// Rendering
		player1.render(g);
		player2.render(g);
		

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
				countdown --;
			}
		}

		stop();
	}

	private void tick() {
		keyManager.tick();
		player2.tick();
		player1.tick();

		countdownString = "Timeleft: " + countdown;
		if(countdown == 0) {
			if(player1.hasTNT()) player1.setDead(true);
			if(player2.hasTNT()) player2.setDead(true);
		}
		
		if(keyManager.getKeyDown(82)) {
			player2.resetPosition();
			player1.resetPosition();
			countdown = 30;
			player2.setDead(false);
			player1.setDead(false);
		}
		
		if(player1.checkColide(player2)) colide();
		
	}
	
	private void colide() {
		if(player1.hasTNT()) {
			player2.setHasTNT(true);
			player1.setHasTNT(false);
			player1.resetPosition();
			player2.resetPosition();
		} else {
			player2.setHasTNT(false);
			player1.setHasTNT(true);
			player1.resetPosition();
			player2.resetPosition();
		}
	} 
	
	

	private void init() {
		countdown = 30;
		countdownString = "Timeleft: " + countdown;
		keyManager = new KeyboardManager();
		l.getFrame().addKeyListener(keyManager);
		l.getCanvas().addKeyListener(keyManager);
		player1 = new Rectangle(0, 0, spriteBlue, 50, 50, 38, 37, 40, 39, keyManager, width, height);
		player2 = new Rectangle(800, 500, spriteGreen, 50, 50, 87, 65, 83, 68, keyManager, width, height);
		
		Random rand = new Random();
		int random = rand.nextInt(2);
		if(random == 1) {
			player1.setHasTNT(true);
		} else {
			player2.setHasTNT(true);
		}
		
		
	}

}
