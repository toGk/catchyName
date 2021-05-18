package domain;

import java.io.Serializable;

import com.game.catchyname.graphics.Screen;
import com.game.catchyname.graphics.Sprite;

import utilities.Coordinates;

public abstract class Entity extends Renderables implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	protected int x,y;
	
	protected int dir; //east,west....
	protected boolean moving = false;
	
	protected int hp = 10;
	protected int attack = 2;

	public Entity(Coordinates spawn,Sprite sprite) {
		this.spawn = spawn;
		x = spawn.getX();
		y = spawn.getY();
		this.sprite = sprite;
	}	
	
	public void damage(Entity target) {
		target.hp-=this.attack;
	}
	
	protected void move(int xa , int ya,Level level) {
	
	}
	
	protected boolean collision(int xa, int ya,Level level) {	
		for(int c=0 ; c<4 ; c++) {	
			//-16 ΠΑΡΑΔΟΧΗ for player to be rendered in the middle of the screen, because to be pretty I have to render +1 tile in width
			// and +1 in height
			//checking 4 neighboring tiles if any of them or more are solid.If so doesn't allow moving toward them
			//	%2 and /2 because 4 corners in a tile, *10 how big is the hitbox, +-B(BUFFER)
			int xt = ((x + xa) + c % 2 *10 - 16 +2)/16;	//  how wide is the horizontal hitbox
			int yt = ((y + ya) + c / 2 * 14  -16 +1)/16;	//	how big vertically is the hitbox
			if(level.getTile(xt,yt).solid()) {
				return true;
			}
		}
		return false;	
	}
	
	public void render(Screen screen) {
		screen.renderPlayer(x-16, y-16, sprite);
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}
	
	public Coordinates getCoordinates() {
		return spawn;
	}
	
	public boolean isAlive() {
		return hp>0;
	}
	


}
