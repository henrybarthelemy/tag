package me.barthelemy.com.Monday.GameLoopExample;

public class GameLoop implements Runnable {
	Boolean running;

	public GameLoop() {
		running = true;
		run();
	}

	@Override
	public void run() {

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
			//DELTA = "CHANGE IN" (also represented by a triangle in physics) 
			deltaU += (currentTime - initialTime) / timeU;
			deltaF += (currentTime - initialTime) / timeF;
			initialTime = currentTime;

			if (deltaU >= 1) {
				/* UPDATES INPUT "UPS" - 
				 * GAMES LIKE OVERWATCH USE 60, GAMES LIKE MINECRAFT USE 20
				 * ALSO REFERED TO AS TICKS PER SECOND (TPS)
				 */
				ticks++;
				deltaU--;
			}

			if (deltaF >= 1) {
				// UPDATE YOU "FRAME" (RUNS 60 FRAMES PER SECOND)
				frames++;
				deltaF--;
			}

			if (System.currentTimeMillis() - timer > 1000) {
				// PRINTS OUT FRAMERATE AND TICKS PER SECOND. (SHOULD BE UPS AND
				// FPS RESPECTIVLY)
				System.out.println(String.format("UPS(TPS): " + ticks +" FPS: " + frames));
				frames = 0;
				ticks = 0;
				timer += 1000;
			}
		}
	}
}
