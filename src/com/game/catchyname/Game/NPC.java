package com.game.catchyname.Game;

import com.game.catchyname.graphics.Screen;

public class NPC extends Mob{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Item loot;
	
	public NPC(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public void update() {
		
	}
	
	public void render(Screen screen) {
		int xx = x-16;
		int yy = y-16;
		screen.renderPlayer(xx, yy, sprite);
	}

}
