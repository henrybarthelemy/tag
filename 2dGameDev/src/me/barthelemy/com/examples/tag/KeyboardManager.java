package me.barthelemy.com.examples.tag;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyboardManager implements KeyListener {
	private boolean[] keys;
	
	
	
	public KeyboardManager(){
		keys = new boolean[256];
		
		for(int i = 0; i < keys.length; i++) {
			keys[i] = false;
		}
	}
	
	public void tick(){
		
	}
	
	
	@Override
	public void keyTyped(KeyEvent e) {
		keys[e.getKeyCode()] = true;
		System.out.print(e.getKeyCode());
	
		
	}
	public boolean getKeyDown(int keyCode) {
		return keys[keyCode];
	}
	
	@Override
	public void keyPressed(KeyEvent e) {
		System.out.println(e.getKeyCode());
		keys[e.getKeyCode()] = true;
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		keys[e.getKeyCode()] = false;
		
	}
}
