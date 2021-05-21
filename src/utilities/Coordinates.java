package utilities;

import java.io.Serializable;

public class Coordinates implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int x;
	private int y;
	private final int TILE_SIZE=16;
	private int hitbox;
	
	public Coordinates(int x,int y,int hitbox) {
		this.x = x*TILE_SIZE;
		this.y = y*TILE_SIZE;
		this.hitbox = hitbox;
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	
	public boolean equals(Object o) {
		Coordinates temp = (Coordinates) o;
		int totalHitbox = temp.hitbox + this.hitbox;
		if((Math.abs(temp.x - this.x)<=totalHitbox)&&(Math.abs(temp.y - this.y)<=totalHitbox)) {
			return true;
		}else {
			return false;
		}	
	}
	
	public void update(int x2, int y2) {
		this.x = x2;
		this.y = y2;
	}

	public boolean isFurtherThan(Coordinates coordinates, int distance) {
		if(Math.pow(Math.abs(this.x- coordinates.x),2)+Math.pow(Math.abs(this.y- coordinates.y),2)>=Math.pow(distance, 2)) {
			return true;
		}else {
			return false;
		}
	}
}
