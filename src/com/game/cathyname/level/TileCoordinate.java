package com.game.cathyname.level;

import java.util.Random;

import com.game.catchyname.Game.Game;

public class TileCoordinate {

	private int x,y;
	private final int TILE_SIZE=16;
	private Random random;
	
	public TileCoordinate(int x, int y) {
		this.x = x*TILE_SIZE;
		this.y = y*TILE_SIZE;
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	
	public int[] xy() {
		int[] r= new int[2];
		r[0] = x;
		r[1] = y;
		return r;
	}
	
	public int[] randomXY() {
		int[] r = new int[2];
		r[0] = random.nextInt(Game.width);
		r[1] = random.nextInt(Game.height);
		return r;
	}
}
