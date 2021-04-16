package com.game.catchyname.graphics;

public class Sprite {

	public final int SIZE;
	private int x,y;
	public int[] pixels;
	private SpriteSheet sheet;
	
	
	
	//Player Sprites
	public static Sprite playerFront = new Sprite(16,5,0,SpriteSheet.tiles);
	public static Sprite playerRight = new Sprite(16,6,0,SpriteSheet.tiles);
	public static Sprite playerLeft = new Sprite(16,3,1,SpriteSheet.tiles);
	public static Sprite playerBehind = new Sprite(16,4,1,SpriteSheet.tiles);
	//General Sprites
	public static Sprite grass = new Sprite(16,0,0,SpriteSheet.tiles);  
	public static Sprite rock = new Sprite(16,0,2,SpriteSheet.tiles);
	public static Sprite flower = new Sprite(16,0,1,SpriteSheet.tiles);
	public static Sprite voidSprite = new Sprite(16,0x1b87e0);
	//Spawn Level Sprites
	public static Sprite spawnGrass= new Sprite(16,1,0,SpriteSheet.spawnLevelTiles);
	public static Sprite spawnWater= new Sprite(16,0,1,SpriteSheet.spawnLevelTiles);
	public static Sprite spawnBricks1= new Sprite(16,0,0,SpriteSheet.spawnLevelTiles);
	public static Sprite spawnBricks2= new Sprite(16,0,2,SpriteSheet.spawnLevelTiles);
	public static Sprite spawnWood= new Sprite(16,1,1,SpriteSheet.spawnLevelTiles);
	public static Sprite spawnBlood= new Sprite(16,2,0,SpriteSheet.spawnLevelTiles);
	public static Sprite spawnVoid = new Sprite(16,1,2,SpriteSheet.spawnLevelTiles);
	//Attack Sprites
	public static Sprite potion = new Sprite(16,9,0,SpriteSheet.tiles);
	public static Sprite arrow = new Sprite(16,10,0,SpriteSheet.tiles);
	public static Sprite ultimate = new Sprite(16,11,0,SpriteSheet.tiles);
	public static Sprite lightning = new Sprite(16,12,0,SpriteSheet.tiles);

	public static Sprite testingSprite = new Sprite(32,0,0,SpriteSheet.test);
	
	
	
	
	public Sprite(int size, int x, int y, SpriteSheet sheet) {
		SIZE = size;
		this.x = x * size;// because every cell in the spritesheet is 16x16px
		this.y = y * size;
		this.sheet = sheet;
		pixels = new int[SIZE * SIZE];
		load();
	}
	
	public Sprite(int size, int color) {
		SIZE = size;
		pixels = new int[SIZE*SIZE];
		setColor(color);
	}
	
	private void setColor(int color) {
	
	 	for(int i=0 ; i< SIZE*SIZE ; i++) {
	 		pixels[i] = color;
	 	}
	}
	
	private void load() {
		for(int y=0 ; y < SIZE ; y++) {
			for(int x=0 ; x< SIZE ; x++) {
				pixels[x+y*SIZE] = sheet.pixels[( x + this.x) + ( y+ this.y) * sheet.SIZE];
			}
		}
	}
	// extracting a single sprite out of my sprite sheet.
	//I use a single spritesheet rather than multiple because then for a big project I would need 1000 files
	//But lower quality 
	
	
	
	
}
