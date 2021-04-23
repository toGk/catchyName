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
	private NPC[] NPCS = new NPC[1];
	
	public GameData(String name,Champion champion) {
		for(int i=0;i<NPCS.length;i++) {
			NPCS[i] = new NPC(new Coordinates(19,50));
		}
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
