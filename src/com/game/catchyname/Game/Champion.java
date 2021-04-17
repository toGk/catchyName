package com.game.catchyname.Game;

import com.game.catchyname.graphics.Screen;
import com.game.catchyname.graphics.Sprite;
import com.game.catchyname.input.Keyboard;

public class Champion extends Mob{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ItemList inventory;
	private Keyboard input;
	
	public Champion() {
		super();
		inventory = new ItemList();
	}
	
	public Champion(int x, int y, Keyboard input) {
		this.x = x;
		this.y = y;
		this.input = input;
	}
	
	public Champion(Keyboard input) {
		this.input = input;
	}
	
	public void update() {
		int xa = 0, ya = 0;
		if(input.up)ya--;
		if(input.down)ya++;
		if(input.left)xa--;
		if(input.right)xa++;
		if(input.f1) {
			new TestFrame(); //stop thread->game.pause()
		}		
		
		
		if(xa != 0 || ya != 0) move(xa,ya);
		//System.out.println("the player  location is " + x +"    , " + y);
	}
	
	public void render(Screen screen) {

		int xx = x-16;
		int yy = y-16;
		screen.renderPlayer(xx, yy, Sprite.testingSprite);
	}

}
