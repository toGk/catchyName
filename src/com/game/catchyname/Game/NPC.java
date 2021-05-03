package com.game.catchyname.Game;

import com.game.catchyname.graphics.Sprite;

public class NPC extends Entity{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Item loot;
	
	public NPC(Coordinates spawn) {
		super(spawn);
		this.sprite = Sprite.testingSprite;
	}
	
	public Item getItem() {
		return loot;
	}
}
