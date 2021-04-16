package com.game.catchyname.input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import com.game.catchyname.graphics.Screen;

public class Keyboard implements KeyListener{
	private boolean[] keys = new boolean[120];
	public boolean up,down,left,right; // keeps track if I pressed this keys
	
	
	public void update() {
		
	up = keys[KeyEvent.VK_UP] || keys[KeyEvent.VK_W];
	down = keys[KeyEvent.VK_DOWN] || keys[KeyEvent.VK_S];
	right = keys[KeyEvent.VK_RIGHT] || keys[KeyEvent.VK_D];
	left = keys[KeyEvent.VK_LEFT] || keys[KeyEvent.VK_A];
		
	
	}
	
	public void keyTyped(KeyEvent e) {
		
		
	}

	
	public void keyPressed(KeyEvent e) {
	
		keys[e.getKeyCode()] = true;
	}

	
	public void keyReleased(KeyEvent e) {
		
		keys[e.getKeyCode()] = false;
	}

}
