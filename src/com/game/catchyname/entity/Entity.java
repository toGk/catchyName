 package com.game.catchyname.entity;

import java.util.Random;

import com.game.catchyname.graphics.Screen;
import com.game.cathyname.level.Level;

public abstract class Entity {

	public int x,y;
	private boolean removed = false;
	protected Level level;
	protected final Random random = new Random();
	
	
	
	public void update() {
		
	}
	
	public void render(Screen screen) {
		
	}
	
	public void remove() {
		removed = true;
	}
	
	public boolean isRemoved() {
		return removed;
	}
	
	public void init(Level level) {
		this.level = level;
	}
	
	
	
}
