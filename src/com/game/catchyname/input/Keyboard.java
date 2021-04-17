package com.game.catchyname.input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Keyboard implements KeyListener{
	private boolean[] keys = new boolean[125];
	public boolean up,down,left,right,f1,f2,f3; // keeps track if I pressed this keys
	
	
	public void update() {
		
	up = keys[KeyEvent.VK_UP] || keys[KeyEvent.VK_W];
	down = keys[KeyEvent.VK_DOWN] || keys[KeyEvent.VK_S];
	right = keys[KeyEvent.VK_RIGHT] || keys[KeyEvent.VK_D];
	left = keys[KeyEvent.VK_LEFT] || keys[KeyEvent.VK_A];
	f1 = keys[KeyEvent.VK_F1];
	f2 = keys[KeyEvent.VK_F2];
	f3 = keys[KeyEvent.VK_F3];
		
	
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
