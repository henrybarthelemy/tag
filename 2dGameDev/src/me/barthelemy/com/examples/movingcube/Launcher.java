package me.barthelemy.com.examples.movingcube;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;

import javax.swing.JFrame;

public class Launcher {
	private JFrame frame;
	private Canvas canvas;

	public static void main(String[] args){
		Game game = new Game();
		game.start();
	}
	
	public Launcher(){
		
		frame = new JFrame("Moving Rectangle");
		frame.setSize(900, 600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		frame.setFocusable(true);
		frame.requestFocusInWindow();
		frame.setLocationRelativeTo(null);
		frame.setResizable(false);
		frame.setVisible(true);
		
		Dimension dimension = new Dimension(900, 600);
		
		canvas = new Canvas();
		canvas.setSize(900, 600);
		canvas.setPreferredSize(dimension);

		canvas.requestFocusInWindow();
		canvas.setFocusable(true);
		canvas.setMaximumSize(dimension);
		canvas.setMinimumSize(dimension);
		canvas.setVisible(true);
		
		frame.add(canvas);
		frame.pack();
		
	}
	

	
	public JFrame getFrame() {
		return frame;
	}

	public Canvas getCanvas() {
		return canvas;
	}

}
