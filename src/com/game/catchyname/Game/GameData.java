package com.game.catchyname.Game;

import java.io.Serializable;
import java.util.ArrayList;

public class GameData implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ArrayList<NPC> allNpcs;
	private ArrayList<Monument> allMonuments;
	private ItemList allItems;
	
	public GameData() {
		allNpcs = new ArrayList<NPC>();
		allMonuments =new ArrayList<Monument>();
		allItems = new ItemList();
	}

}
