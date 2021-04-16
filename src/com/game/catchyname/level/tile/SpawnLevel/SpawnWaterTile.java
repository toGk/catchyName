package com.game.catchyname.level.tile.SpawnLevel;

import com.game.catchyname.graphics.Screen;
import com.game.catchyname.graphics.Sprite;

public class SpawnWaterTile extends Tile{
	
	public SpawnWaterTile(Sprite sprite) {
		super(sprite);
	}

	public void render(int x, int y, Screen screen) {
		screen.renderTile(x<<4, y<<4, this);	//converting back to pixels precision
	}
}
