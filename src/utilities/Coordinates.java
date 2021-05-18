package utilities;

import java.io.Serializable;

public final class Coordinates implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int x;
	private int y;
	private final int TILE_SIZE=16;
	private int hitbox;
	private int levelid;
	
	public Coordinates(int x,int y,int hitbox,int levelid) {
		this.x = x*TILE_SIZE;
		this.y = y*TILE_SIZE;
		this.hitbox = hitbox;
		this.levelid = levelid;
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}

	@Override
	/*public boolean equals(Object o) {
		Coordinates temp = (Coordinates) o;
		return (temp.x == this.x&&temp.y == this.y);	
	}*/
	
   public int hashCode() {
       return levelid;
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
	
	public int getLevelId() {
		return levelid;
	}

}
