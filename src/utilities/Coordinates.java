package com.game.catchyname.Game;

import java.io.Serializable;

public final class Coordinates implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int x;
	private int y;
	private final int TILE_SIZE=16;
	
	public Coordinates(int x,int y) {
		this.x = x*TILE_SIZE;
		this.y = y*TILE_SIZE;
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}

	@Override
	public boolean equals(Object o) {
		Coordinates temp = (Coordinates) o;
		return (temp.x == this.x&&temp.y == this.y);	
	}

}
