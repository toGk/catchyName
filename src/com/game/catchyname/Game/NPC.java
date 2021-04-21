package com.game.catchyname.Game;

import com.game.catchyname.graphics.Screen;
import com.game.catchyname.level.tile.SpawnLevel.Tile;

public class NPC extends Mob{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Item loot;
	
	public NPC(Coordinates spawn) {
		super(spawn);
	}
	
	public Item getItem() {
		return loot;
	}
	
	public void update() {
		
	}
	
	public void render(Screen screen) {
		int xx = getX()-16;
		int yy = getY()-16;
		//screen.renderPlayer(xx, yy, Sprite.spawnBlood);
		screen.renderTile(xx, yy, Tile.spawnBlood);
	}

}
