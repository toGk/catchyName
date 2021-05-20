package domain;

import java.awt.event.KeyEvent;
import java.io.Serializable;

import com.game.catchyname.graphics.Screen;

import domain.lists.ItemList;
import domain.lists.MobList;

public final class GameData implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Player player;
	private Champion champion;
	private Level level;
	private MobList allmobs;
	private ItemList allItems;
	
	public GameData(Player player,String levelid) {
		this.player = player;
		this.champion = player.getChampion();
		level = new Level(levelid);
		allmobs = new MobList(level);
		allItems = new ItemList(level);
	}
	
	public void printData() {
		player.printData();
	}

	private void pickItem() {
		Item temp = allItems.getItem(champion.getCoordinates());
		if(temp!=null) {
			champion.pickItem(temp);
		    allItems.remove(temp);
		}
	}

	public void render(Screen screen) {
		level.render(champion.getCoordinates().getX() - screen.getWidth() /2,champion.getCoordinates().getY() - screen.getHeight() /2, screen);
		allmobs.render(screen, allItems);
		allItems.render(screen);
		champion.render(screen);
	}

	public void update(boolean[] keyCode) {
		if(keyCode[KeyEvent.VK_F2])pickItem();
	    champion.update(level,keyCode,this);
	}

	public MobList getAllmobs() {
		return allmobs;
	}

	public Level getLevel() {
		return level;
	}
}
