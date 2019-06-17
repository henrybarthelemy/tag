package me.barthelemy.com.examples.movingcube;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyboardManager implements KeyListener {
	private boolean[] keys;
	public boolean w, a, s, d;
	
	
	
	public KeyboardManager(){
		keys = new boolean[256];
	}
	
	public void tick(){
		w = keys[KeyEvent.VK_W];
	    d = keys[KeyEvent.VK_D];
		a = keys[KeyEvent.VK_A];
		s = keys[KeyEvent.VK_S];
	}

	
	@Override
	public void keyTyped(KeyEvent e) {
		keys[e.getKeyCode()] = true;
		System.out.println("GO DOWN?");
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		keys[e.getKeyCode()] = true;
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		keys[e.getKeyCode()] = false;
		
	}
}
