package domain.lists;

import java.io.Serializable;
import java.util.ArrayList;

import domain.Item;

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
