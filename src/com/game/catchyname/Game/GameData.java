package com.game.catchyname.Game;

import java.io.Serializable;
import java.util.ArrayList;

public final class GameData implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ArrayList<Monument> allMonuments;
	private ItemList allItems;
	private Player player;
	private NPC[] NPCS = {new NPC(new Coordinates(0,10))};
	
	public GameData(String name,Champion champion) {
		allMonuments =new ArrayList<Monument>();
		allItems = new ItemList();
		player = new Player(name,champion);
	}

	public Player getPlayer() {
		return player;
	}
	public ItemList getItemList() {
		return allItems;
	}
	public ArrayList<Monument> getMonuments() {
		return allMonuments;
	}

	
	
	public NPC[] getNpcs() {
		return NPCS;
	}

}
