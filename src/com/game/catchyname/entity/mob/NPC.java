package com.game.catchyname.entity.mob;

import com.game.catchyname.graphics.Screen;

public class NPC extends Mob{

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
