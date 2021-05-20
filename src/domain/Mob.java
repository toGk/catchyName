package domain;

import com.game.catchyname.graphics.Sprite;

import utilities.Coordinates;

public class Mob extends Entity{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Item loot;
	
	public Mob(Coordinates spawn,Sprite sprite) {
		super(spawn,sprite);
	}
	
	public Item getItem() {
		return loot;
	}

	public void removeItem() {
		loot = null;
	}
}
