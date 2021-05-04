package com.game.catchyname.Game;

import java.io.Serializable;
import java.util.ArrayList;

public class ItemList implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ArrayList<Item> items;
	
	public ItemList() {
		items = new ArrayList<Item>();
	}
	
	public ArrayList<Item> getItems(){
		return items;
	}

}
