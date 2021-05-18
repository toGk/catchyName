package domain;

import java.io.Serializable;

import domain.lists.ItemList;
import domain.lists.MobList;

public final class GameData implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ItemList allItems;
	private Player player;
	private Level level;
	private MobList allmobs;
	
	public GameData(String name,Champion champion) {
		level = new Level(champion.getCoordinates().getLevelId());
		allmobs = new MobList(level);
		allItems = new ItemList(level);
		player = new Player(name,champion);
	}
	
	public MobList getMobList() {
		return allmobs;
	}

	public Player getPlayer() {
		return player;
	}
	public ItemList getItemList() {
		return allItems;
	}

	public Level getLevel() {
		return level;
	}
}
