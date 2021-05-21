package domain;

import java.awt.event.KeyEvent;
import java.util.ArrayList;

import com.game.catchyname.graphics.Sprite;

import domain.lists.ItemList;
import utilities.Coordinates;

public abstract class Champion extends Entity{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ArrayList<Item> inventory;
	
	public Champion(Coordinates playerSpawn,Sprite sprite) {
		super(playerSpawn,sprite);
		inventory = new ArrayList<Item>();
	}
	
	public ArrayList<Item> getInventory() {
		return inventory;
	}
	
	public void update(Level level, boolean[] keyCode, GameData gameData) {
		super.update(level,keyCode,gameData);
		if(keyCode[KeyEvent.VK_F2])pickItem(gameData);
		int xa=0,ya=0;
        if(keyCode[KeyEvent.VK_UP]) ya--;
        if(keyCode[KeyEvent.VK_DOWN])ya++;
        if(keyCode[KeyEvent.VK_RIGHT]) xa++;
        if(keyCode[KeyEvent.VK_LEFT]) xa--;
        move(xa,ya,level);
    }
	
	private void pickItem(GameData data) {
		ItemList list = data.getAllItems();
		Item temp = list.getItem(this.getCoordinates());
		if(temp!=null) {
			inventory.add(temp);
		    list.remove(temp);
		}
	}
}
