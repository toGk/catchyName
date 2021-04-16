package com.game.cathyname.level;

import com.game.catchyname.graphics.Screen;
import com.game.catchyname.graphics.Sprite;
import com.game.catchyname.level.tile.SpawnLevel.Tile;

public class Level {
	// Because will have only 1 level loaded at a time I am gonna make a global variable level
	protected int width,height;
	protected int[] tilesInt; 											// to know in witch index I am gonna create a tile
	protected int[] tiles;
	public static Level spawn = new SpawnLevel("/levels/Spawn.png/");
	
	
	public Level(int width, int height) {
		
		this.width = width;
		this.height = height;
		tilesInt = new int[width*height];												//contains indexes for the type of tile in each position
		generateLevel();
		
	}
	
	public Level(String path) {
		loadLevel(path);
	}
	
	protected void generateLevel() {
	}
	
	protected void loadLevel(String path) {
	}
	
	public void update() {
	}
	
	public void render(int xScroll, int yScroll, Screen screen) {
		//CORNER PINS
		//x0 left side of the screen
		//>>4 == /16 . I am dividing into tiles instead of pixels
		// Because I am dealing with rendering tiles its not the same as rendering a player cause a player can be in between 2 tiles but a tile is after the other.Bitwise is a lot faster 
		screen.setOffset(xScroll, yScroll);
		int x0 = xScroll >> 4; 
		int x1 = (xScroll + screen.width + 16) >> 4; 		
		int y0 = yScroll >>4;
		int y1 = (yScroll + screen.height +16) >>4;		
		
		//rendering z amount of tiles right,left,up,bottom of the player location on screen.He'll be in the middle
		for(int y = y0 ; y < y1 ; y++) {				// render every pixel that is seen in the frame
			for(int x = x0 ; x < x1 ; x++) {
				getTile(x,y).render(x,y,screen);			//tile precision not pixel precision
				
			}
		}
		
	}
	
	public Tile getTile(int x, int y) {
		if(x<0 || y<0 || x>= width || y>= height)return Tile.spawnVoid;
		if(tiles[x+y*width] == Tile.colSpawnGrass)return Tile.spawn_GrassTile;
		if(tiles[x+y*width] == Tile.colSpawnWater )return Tile.spawnWaterTile;
		if(tiles[x+y*width] == Tile.colSpawnBricks1 )return Tile.spawnBricks1;
		if(tiles[x+y*width] == Tile.colSpawnBricks2 )return Tile.spawnBricks2;
		if(tiles[x+y*width] == Tile.colSpawnBlood )return Tile.spawnBlood;
		return Tile.spawnVoid;
	}
	
	private void time() {
		
	}
	
}
