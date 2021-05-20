package domain.lists;

import java.io.Serializable;
import java.util.ArrayList;
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
	private ArrayList<Mob> mobs;
	private Random random;
	
	public MobList(Level level) {
		mobs = new ArrayList<Mob>();
		
		random = new Random();
		int mobCounter = random.nextInt(10000);
		int hitbox = 5;
		
		for(int i=0;i<mobCounter;i++) {
			int xLimit = random.nextInt(100); 
		    int yLimit = random.nextInt(100); 
			Coordinates temp = new Coordinates(xLimit,yLimit,hitbox);
			mobs.add(new Mob(temp,Sprite.testingSprite));
		}
	}
	
	public void render(Screen screen,ItemList allItems) {
		ArrayList<Mob> temps = new ArrayList<Mob>();
		for(Mob temp:mobs) {
				if(temp.isAlive()) {
			       temp.render(screen);
		        }else {
			       allItems.addItem(temp.getItem());
			       temp.removeItem();
			       temps.add(temp);
			    }
	    }
		mobs.removeAll(temps);
	}

	public Mob getMob(Coordinates coordinates) {
		for(Mob mob:mobs) {
			if(mob.isInLocation(coordinates)) {
				return mob;
			}
		}
		return null;
	}

}
