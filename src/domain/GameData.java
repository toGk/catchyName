package domain;

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
	private Screen screen;
	
	public GameData(Player player,String levelid) {
		this.player = player;
		this.champion = player.getChampion();
		level = new Level(levelid);
		allmobs = new MobList(level,champion.coordinates);
		allItems = new ItemList(level);
	}
	
	public void printData() {
		player.printData();
	}

	public void render() {
		level.render(champion.getCoordinates().getX() - screen.getWidth() /2,champion.getCoordinates().getY() - screen.getHeight() /2, screen);
		allmobs.render(screen, allItems, player);
		allItems.render(screen);
		champion.render(screen);
	}

	public void update(boolean[] keyCode) {
	    champion.update(level,keyCode,this);
	    allmobs.update(level,keyCode,this);
	}
	
	public Player getPlayer() {
		return player;
	}

	public MobList getAllmobs() {
		return allmobs;
	}

	public Level getLevel() {
		return level;
	}

	public void setScreen(Screen screen) {
		this.screen = screen;	
	}
	
	public Screen getScreen() {
		return screen;
	}

	public ItemList getAllItems() {
		return allItems;
	}

	public boolean gameIsOn() {
		return champion.isAlive();
	}
}
