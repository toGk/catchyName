package com.game.catchyname.entity.mob;

import com.game.catchyname.entity.Entity;
import com.game.catchyname.graphics.Sprite;
import com.game.cathyname.level.Level;

public abstract class Mob extends Entity{

	protected Sprite sprite;
	protected int dir =0; //east,west....
	protected boolean moving = false;
	
	
	public void move(int xa , int ya) {
		if(xa != 0 && ya != 0) {
			move(xa,0);
			move(0,ya);
			return ;	//if I dont return the will be moving slowly
		}
		
		if(xa >0) dir=1; // east
		if(xa <0) dir=3; // west
		if(ya > 0) dir =2;//south
		if(ya <0) dir =0; //north
		
		// -1 , 0 ,1 
		if(!collision(xa,ya)) {
			x+= xa;
			y+= ya;
		}
		
	}
	
	public void update() {
		
	}
	
	private boolean collision(int xa, int ya) {
		boolean solid = false;
		
		for(int c=0 ; c<4 ; c++) {	
			//-16 ΠΑΡΑΔΟΧΗ for player to be rendered in the middle of the screen, because to be pretty I have to render +1 tile in width
			// and +1 in height
			//checking 4 neighboring tiles if any of them or more are solid.If so doesn't allow moving toward them
			//	%2 and /2 because 4 corners in a tile, *10 how big is the hitbox, +-B(BUFFER)
			int xt = ((x + xa) + c % 2 *10 - 16 +2)/16;	//  how wide is the horizontal hitbox
			int yt = ((y + ya) + c / 2 * 14  -16 +1)/16;	//	how big vertically is the hitbox
			if(level.getTile(xt,yt).solid()) solid =true;
		}
		return solid;
		
	}
	
	public void render() {
		
	}
}
