package com.game.cathyname.level;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.Serializable;

import javax.imageio.ImageIO;

import com.game.catchyname.level.tile.SpawnLevel.Tile;

public class SpawnLevel extends Level implements Serializable{

	public SpawnLevel(String path) {
		super(path);
	}
	
	protected void loadLevel(String path) {
		try {
			BufferedImage image = ImageIO.read(SpawnLevel.class.getResource(path));
			int w = width = image.getWidth();
			int h = height = image.getHeight();
			
			tiles = new int[w*h];
			image.getRGB(0, 0,w,h,tiles,0,w);// converting image to array of every pixel color
		}catch(IOException e) {
			System.err.println("Exception! Could not load level file");
		}
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
}

