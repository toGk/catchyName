package domain.lists;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Random;

import com.game.catchyname.graphics.Screen;
import com.game.catchyname.graphics.Sprite;

import domain.Level;
import domain.Mob;
import utilities.Coordinates;

public class MobList implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private HashMap<Coordinates,Mob> mobs;
	private Random random;
	
	public MobList(Level level) {
		mobs = new HashMap<Coordinates,Mob>();
		
		random = new Random();
		int mobCounter = random.nextInt(10);
		int xLimit = random.nextInt(10); 
		int yLimit = random.nextInt(10); 
		int hitbox = 50;
		for(int i=0;i<mobCounter;i++) {
			//if(!level.getTile(xLimit,yLimit).solid()) {
			   Coordinates temp = new Coordinates(xLimit,yLimit,hitbox,level.getId());
			   mobs.put(temp, new Mob(temp,Sprite.testingSprite));
			//}
		}
	}
	
	public void render(Screen screen,ItemList allItems) {
		for(Coordinates coordinates:mobs.keySet()) {
			Mob temp = mobs.get(coordinates);
			if(temp.isAlive()) {
				temp.render(screen);
			}else {
				allItems.putItem(temp.getCoordinates(),temp.getItem());
				temp.removeItem();
				mobs.remove(coordinates, temp);
			}
	    }
	}
	
	public Mob getMob(Coordinates coordinates) {
		return mobs.get(coordinates);
	}

}
