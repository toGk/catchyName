package domain;

import java.awt.event.KeyEvent;
import java.util.ArrayList;

import com.game.catchyname.graphics.Sprite;

import utilities.Coordinates;

public abstract class Champion extends Entity{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ArrayList<Item> inventory;
	protected int direction;

	
	public Champion(Coordinates playerSpawn,Sprite sprite) {
		super(playerSpawn,sprite);
		inventory = new ArrayList<Item>();
	}
	
	public void pickItem(Item item) {
		inventory.add(item);
	}
	
	public ArrayList<Item> getInventory() {
		return inventory;
	}
	
	public void update(Level level, boolean[] keyCode, GameData gameData) {
		int xa=0,ya=0;
        if(keyCode[KeyEvent.VK_UP] || keyCode[KeyEvent.VK_W]) ya--;
        if(keyCode[KeyEvent.VK_DOWN] || keyCode[KeyEvent.VK_S])ya++;
        if(keyCode[KeyEvent.VK_RIGHT] || keyCode[KeyEvent.VK_D]) xa++;
        if(keyCode[KeyEvent.VK_LEFT] || keyCode[KeyEvent.VK_A]) xa--;
        move(xa,ya,level);
    }
}
