package domain;

import java.util.Random;

import com.game.catchyname.graphics.Sprite;

import utilities.Coordinates;

public class Mob extends Entity{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Item loot;
	private Random random;
	
	public Mob(Coordinates spawn,Sprite sprite) {
		super(spawn,sprite);
	}
	
	public Item getItem() {
		return loot;
	}

	public void removeItem() {
		loot = null;
	}
	
	public void update(Level level, boolean[] keyCode, GameData gameData) {
		random = new Random();
		int xa = random.nextInt(10); 
	    int ya = random.nextInt(10); 
		move(xa,ya,level);
	}
	
	public void move(int xa , int ya,Level level) {
		if(xa != 0 && ya != 0) {
			move(xa,0,level);
			move(0,ya,level);
			return ;	//if I dont return the will be moving slowly
		}
		
		if(!collision(xa,ya,level)) {
			x += xa;
			y += ya;
		}else {
			x -= xa;
			y -= ya;
		}
		coordinates.update(x,y);
		updateAttacks(attackmoves);
	}
}
