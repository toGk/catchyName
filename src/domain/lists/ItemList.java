package domain.lists;

import java.io.Serializable;
import java.util.HashMap;
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
	private HashMap<Coordinates,Item> items;
	private Random random;
	
	public ItemList(Level level) {
		items = new HashMap<Coordinates,Item>();
		
		random = new Random();
		int itemCounter = random.nextInt(10);
		int hitbox = 50;

		for(int i=0;i<itemCounter;i++) {
			int xLimit = random.nextInt(100); 
		    int yLimit = random.nextInt(100); 
			//if(level.getTile(xLimit,yLimit).solid()) {
			   Coordinates temp = new Coordinates(xLimit,yLimit,hitbox,level.getId());
			   items.put(temp, new Item(temp,Sprite.testingSprite));
			//}
		}
	}
	
	public void putItem(Coordinates coordinates,Item item) {
	    items.put(coordinates, item);
	}
	
	public Item getItem(Coordinates coordinates){
		return items.get(coordinates);
	}

	public void render(Screen screen) {
		for(Coordinates coordinates:items.keySet()) {
			items.get(coordinates).render(screen);
	    }
	}
	
	public void remove(Coordinates coordinates, Item temp) {
		items.remove(coordinates, temp);
	}
	
	
	public HashMap<Coordinates,Item> getC(){
		return items;
	}

}
