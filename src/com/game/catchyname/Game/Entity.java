package com.game.catchyname.Game;

import java.io.Serializable;

import com.game.catchyname.graphics.Screen;
import com.game.catchyname.graphics.Sprite;
import com.game.cathyname.level.Level;

public abstract class Entity implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	protected Coordinates spawn;
	protected int x,y;
	
	protected int dir; //east,west....
	protected boolean moving = false;
	
	protected int hp = 10;
	protected int attack = 2;
	protected Sprite sprite;

	public Entity(Coordinates spawn) {
		this.spawn = spawn;
		x = spawn.getX();
		y = spawn.getY();
	}	
	
	public void damage(Entity target) {
		target.hp-=this.attack;
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
		
	}
	
	private boolean collision(int xa, int ya,Level level) {	
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

}
