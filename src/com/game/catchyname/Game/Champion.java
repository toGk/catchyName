package com.game.catchyname.Game;

import com.game.catchyname.graphics.Sprite;

public abstract class Champion extends Entity{
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
		this.sprite = Sprite.testingSprite;
	}
}
