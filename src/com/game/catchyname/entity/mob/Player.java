package com.game.catchyname.entity.mob;

import com.game.catchyname.graphics.Screen;
import com.game.catchyname.graphics.Sprite;
import com.game.catchyname.input.Keyboard;

public class Player extends Mob{
	private Keyboard input;
	private String loginName;
	private String loginPassword;
	
	public Player(int x, int y, Keyboard input) {
		this.x = x;
		this.y = y;
		this.input = input;
	}
	
	public Player(Keyboard input) {
		this.input = input;
	}
	
	public void setloginPassword(String password) {
		this.loginPassword = password;
	}
	
	
	
	
	
	
	public void update() {
		int xa = 0, ya = 0;
		if(input.up)ya--;
		if(input.down)ya++;
		if(input.left)xa--;
		if(input.right)xa++;
		
		
		if(xa != 0 || ya != 0) move(xa,ya);
		//System.out.println("the player  location is " + x +"    , " + y);
	}
	
	public void render(Screen screen) {

		int xx = x-16;
		int yy = y-16;
		screen.renderPlayer(xx, yy, Sprite.testingSprite);
	}
	
	
	
	
	
	
	
	
	
}
