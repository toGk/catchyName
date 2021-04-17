package com.game.catchyname.Game;

import java.io.Serializable;

import com.game.catchyname.input.Keyboard;

public class Player implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Champion champion;
	
	public Player() {
		//champion = new Champion();
	}

	public void setChampion(int x, int y, Keyboard key) {
		champion = new Champion(x,y,key);
		
	}

	public Champion getChampion() {
		return champion;
	}

}
