package com.game.catchyname.level.tile.SpawnLevel;

import com.game.catchyname.graphics.Screen;
import com.game.catchyname.graphics.Sprite;

public class Tile {
	
	
	// a tile will have a sprite 
	//even when an error will occur a sprite will be displayed
	public int x,y;
	public Sprite sprite;

	
	public static Tile spawn_GrassTile = new SpawnGrassTile(Sprite.spawnGrass);
	public static Tile spawnWaterTile = new SpawnWaterTile(Sprite.spawnWater);
	public static Tile spawnWood = new SpawnWoodTile(Sprite.spawnWood);
	public static Tile spawnBricks1 = new SpawnWallTile(Sprite.spawnBricks1);
	public static Tile spawnBricks2 = new SpawnWallTile(Sprite.spawnBricks2);
	public static Tile spawnBlood = new SpawnBloodTile(Sprite.spawnBlood);
	public static Tile spawnVoid = new SpawnVoidTile(Sprite.spawnVoid);
	
	
	// colors used in Spawn image for representing Spawn Level Tiles
	public final static int colSpawnGrass = 0xff00ff21;
	public final static int colSpawnBlood = 0xffff0000;
	public final static int colSpawnBricks1 = 0xff404040;
	public final static int colSpawnBricks2 = 0xffffd800;
	public final static int colSpawnWater = 0xff0026ff;
	public final static int colSpawnLocation = 0xff00ffff;
	
	
	
	//attacks
//	public final Tile potionTile = new PotionTile(Sprite.potion);
//	public final Tile lightningTile = new LightningTile(Sprite.lightning);
//	public final Tile ultimateTile = new UltimateTile(Sprite.arrow);
//	public final Tile arrow = new ArrowTile(Sprite.arrow);
	
	
	
	public Tile(Sprite sprite) {
		this.sprite = sprite;
	}
	
	public void render(int x, int y, Screen screen) {
		
	}
	
	public boolean solid() {
		return false;
	}
	
	
	
	
	
	
	
	
	
	
	
	
}
