package domain.lists;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;

import com.game.catchyname.graphics.Screen;
import com.game.catchyname.graphics.Sprite;

import domain.Item;
import domain.Level;
import utilities.Coordinates;

public class ItemList implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ArrayList<Item> items;
	private Random random;
	
	public ItemList(Level level) {
		items = new ArrayList<Item>();
		
		random = new Random();
		int itemCounter = random.nextInt(1000);
		int hitbox = 5;

		for(int i=0;i<itemCounter;i++) {
			int xLimit = random.nextInt(100); 
		    int yLimit = random.nextInt(100); 
			Coordinates temp = new Coordinates(xLimit,yLimit,hitbox);
			items.add(new Item(temp,Sprite.testingSprite));
		}
	}
	
	public void addItem(Item item) {
	    items.add(item);
	}
	
	public Item getItem(Coordinates coordinates){
		for(Item item:items) {
			if(item!=null) {
				if(item.isInLocation(coordinates)) {
				    return item;
			    }
			}
		}
		return null;
	}

	public void render(Screen screen) {
		for(Item item:items) {
			if(item!=null) {
			   item.render(screen);
			}
	    }
	}
	
	public void remove(Item temp) {
		items.remove(temp);
	}
	
	public ArrayList<Item> getC(){
		return items;
	}

}
