package domain;

import java.util.Random;

import com.game.catchyname.graphics.Sprite;

import utilities.Coordinates;

public class Mob extends Entity implements Comparable<Mob>{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Item loot;
	private Random random;
	private Coordinates target;
	private int points = 10;
	
	public Mob(Coordinates spawn,Sprite sprite, Coordinates target) {
		super(spawn,sprite);
		this.target = target;
	}
	
	public Item getItem() {
		return loot;
	}

	public void removeItem() {
		loot = null;
	}
	
	public void update(Level level, boolean[] keyCode, GameData gameData) {
		random = new Random();
		int xa = random.nextInt(4); 
	    int ya = random.nextInt(4); 
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
			x -= xa*xa;
			y -= ya*ya;
		}
		coordinates.update(x,y);
	}

	@Override
	public int compareTo(Mob o) {
		double distance_this_target = Math.pow(this.coordinates.getX()-target.getX(),2) + Math.pow(this.coordinates.getY()-target.getY(),2);
		double distance_temp_target = Math.pow(o.coordinates.getX()-target.getX(),2) + Math.pow(o.coordinates.getY()-target.getY(),2);
		if(distance_this_target>distance_temp_target) {
			return 1;
		}else if(distance_this_target==distance_temp_target) {
		    return 0;
		}else {
			return -1;
		}
	}

	public void givePoints(Player player) {
		player.addPoints(points);
	}
}
