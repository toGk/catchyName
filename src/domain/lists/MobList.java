package domain.lists;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

import com.game.catchyname.graphics.Screen;
import com.game.catchyname.graphics.Sprite;

import domain.GameData;
import domain.Level;
import domain.Mob;
import domain.Player;
import utilities.Coordinates;

public class MobList implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ArrayList<Mob> mobs;
	private Random random;
	
	public MobList(Level level, Coordinates target) {
		mobs = new ArrayList<Mob>();
		
		random = new Random();
		int mobCounter = random.nextInt(10000);
		int hitbox = 5;
		
		for(int i=0;i<mobCounter;i++) {
			int xLimit = random.nextInt(100); 
		    int yLimit = random.nextInt(100);
		    if(level.getTile(xLimit, yLimit).solid()==false) {
		    	Coordinates temp = new Coordinates(xLimit,yLimit,hitbox);
			     mobs.add(new Mob(temp,Sprite.testingSprite,target));
		    }
			
		}
	}
	
	public void render(Screen screen,ItemList allItems, Player player) {
		ArrayList<Mob> temps = new ArrayList<Mob>();
		for(Mob temp:mobs) {
			if(temp!=null&&mobs!=null) {
				if(temp.isAlive()) {
			       temp.render(screen);
		        }else {
		           temp.givePoints(player);
			       allItems.addItem(temp.getItem());
			       temp.removeItem();
			       temps.add(temp);
			    }
			}
	    }
		mobs.removeAll(temps);
	}

	public Mob getMob(Coordinates coordinates) {
		Mob temp;
		for(Mob mob:mobs) {
			if(mobs!=null&&mob!=null) {
			   if(mob.isInLocation(coordinates)) {
				   temp =mob;
				   return temp;
			    }
			}
		}
		return null;
	}

	public void update(Level level, boolean[] keyCode, GameData gameData) {
		for(Mob mob:mobs) {
			mob.update(level,keyCode,gameData);
		}
	}

	public void sort() {
		Collections.sort(mobs);
	}
}
