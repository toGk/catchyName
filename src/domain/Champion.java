package domain;

import com.game.catchyname.graphics.Sprite;

import domain.lists.ItemList;
import utilities.Coordinates;

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
