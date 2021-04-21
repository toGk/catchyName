package com.game.catchyname.Game;

import com.game.catchyname.graphics.Screen;
import com.game.catchyname.graphics.Sprite;

public abstract class Champion extends Mob{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ItemList inventory;
	
	public ItemList getInventory() {
		return inventory;
	}
	
	public Champion(Coordinates playerSpawn) {
		super(playerSpawn);
		inventory = new ItemList();
	}
	
	public void render(Screen screen) {

		int xx = getX()-16;
		int yy = getY()-16;
		screen.renderPlayer(xx, yy, Sprite.testingSprite);
	}

}
