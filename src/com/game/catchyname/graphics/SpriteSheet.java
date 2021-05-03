package com.game.catchyname.graphics;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.Serializable;

import javax.imageio.ImageIO;

public class SpriteSheet implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String path;
	public final int SIZE;
	public int[] pixels;
	
	public static SpriteSheet tiles = new SpriteSheet("/textures/sheets/SpriteSheet.png/",256);
	public static SpriteSheet spawnLevelTiles = new SpriteSheet("/textures/sheets/SpawnLevelSprites.png/",48);
	public static SpriteSheet test = new SpriteSheet("/textures/sheets/assasin1frontstep1.png/",32);
	
	public SpriteSheet(String path, int size) {
		
		this.path = path;
		this.SIZE = size;
		pixels = new int[SIZE * SIZE];
		load();
	}
	
	private void load() {
		try {
			BufferedImage image = ImageIO.read(SpriteSheet.class.getResource(path));
			int w = image.getWidth();
			int h = image.getHeight();
			image.getRGB(0, 0, w, h, pixels, 0, w);
		
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	
	
	
	
	
}
