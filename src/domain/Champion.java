package domain;

import java.util.ArrayList;

import com.game.catchyname.graphics.Sprite;

import utilities.Coordinates;

public abstract class Champion extends Entity{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ArrayList<Item> inventory;
	

	
	public Champion(Coordinates playerSpawn,Sprite sprite) {
		super(playerSpawn,sprite);
		inventory = new ArrayList<Item>();
	}
	
	public void pickItem(Item item) {
		inventory.add(item);
	}
	
	public ArrayList<Item> getInventory() {
		return inventory;
	}
	
	public void move(int xa , int ya,Level level) {
		if(xa != 0 && ya != 0) {
			move(xa,0,level);
			move(0,ya,level);
			return ;	//if I dont return the will be moving slowly
		}
		
		if(xa >0) dir=1; // east
		if(xa <0) dir=3; // west
		if(ya > 0) dir =2;//south
		if(ya <0) dir =0; //north
		
		if(!collision(xa,ya,level)) {
			x += xa;
			y += ya;
		}
		spawn.update(x,y);	
	}
}
