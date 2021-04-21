package com.game.catchyname.graphics;

import java.awt.Canvas;
import java.io.Serializable;
import java.util.Random;

import com.game.catchyname.level.tile.SpawnLevel.Tile;

public class Screen {//implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public final int MAP_SIZE = 8;
	public final int MAP_SIZE_MASK = MAP_SIZE -1;
	private final int TILES=64;
	public int width, height;
	public int[] pixels;
	public int[]tiles = new int[TILES*TILES];    //4096 tiles
	public int xOffset,yOffset;
	private Random random = new Random();   
	
	
	
	public Screen(int width, int height) {
		this.width = width;
		this.height = height;
		pixels = new int[width*height];    //0-50.399 pixels
		
		for(int i=0; i< MAP_SIZE *MAP_SIZE ;i++) {
			tiles[i]= random.nextInt(0xffffff);
			tiles[0] = 0;
		}
	
		
	}
	
	//set everything to black
	public void clear() {
		for(int i=0; i<pixels.length;i++) {
			pixels[i] = 0;
		}
	}
	
	public void renderTile(int xp , int yp, Tile tile) {
		
		//relative  to certain object
		//y-> pixels currently rendering , yp->location where is gonna be rendered is gonna be offseted by yp value
		//xa is x absolute*/
		
		xp -= xOffset;// this 2 lines, when I move right the map must not move right but left to give sensation of moving player right
		yp -= yOffset;
		
		for( int y=0; y< tile.sprite.SIZE ; y++) {	
			int ya = y + yp;    					
			for( int x=0; x< tile.sprite.SIZE ; x++) {
				int xa = x + xp;   
				if(xa < -tile.sprite.SIZE || xa >= width || ya < 0 || ya >= height)break;
				if(xa<0) xa=0;
				//^^^^^^^^^only render the tiles I am seeing and not all the map, important line of code
				pixels[xa+ya*width] = tile.sprite.pixels[x+y*tile.sprite.SIZE];
				//^^^^^^^^^where the sprite is rendered----------which pixels of the sprite are rendered
				
			}
		}

	}
	
	
	
	public void setOffset(int xOffset, int yOffset) {
		this.xOffset = xOffset;
		this.yOffset = yOffset;
	}
	
	public void renderPlayer(int xp, int yp, Sprite sprite) {
		xp -= xOffset;
		yp -= yOffset;
		
		// the x,y in the for will change temp to check to given image from patataki . Default 16 both
		
		for( int y=0; y< 32; y++) {	
			int ya = y + yp;    					
			for( int x=0; x< 32 ; x++) {
				int xa = x + xp;   
				if(xa < -32 || xa >= width || ya < 0 || ya >= height)break;
				if(xa<0) xa=0;
				int col =sprite.pixels[x+y*32];
				if(col != 0xffc2185b)
				//^^^^^^^^^only render the tiles I am seeing and not all the map, important line of code
					pixels[xa+ya*width] = sprite.pixels[x+y*sprite.SIZE];
				//^^^^^^^^^where the sprite is rendered----------which pixels of the sprite are rendered
			}
		}
	}
	
	public void renderItem(int xp, int yp, Tile tile) {
		xp -= xOffset;
		yp -= yOffset;
		
		for(int y=0 ; y<tile.sprite.SIZE ; y++) {
			int ya = y+yp;
			for(int x=0 ; x<tile.sprite.SIZE ; x++) {
				int xa = x + xp;
				if(xa < -16 || xa >= width || ya < 0 || ya >= height)break;
				if(xa<0) xa=0;
				int col = tile.sprite.pixels[x+y*tile.sprite.SIZE];
				if(col != 0xffffac75)
					pixels[xa+ya*width] = tile.sprite.pixels[x+y*tile.sprite.SIZE];
					
			}
		}
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
