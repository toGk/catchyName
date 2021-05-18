package domain;

import com.game.catchyname.graphics.Sprite;

import utilities.Coordinates;

public class Mob extends Entity{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Item loot;
	
	public Mob(Coordinates spawn,Sprite sprite) {
		super(spawn,sprite);
	}
	
	public Item getItem() {
		return loot;
	}

	public void removeItem() {
		loot = null;
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
