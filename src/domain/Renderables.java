package domain;

import java.io.Serializable;

import com.game.catchyname.graphics.Screen;
import com.game.catchyname.graphics.Sprite;

import utilities.Coordinates;

public abstract class Renderables implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	protected Sprite sprite;
	protected Coordinates spawn;
	
	public Renderables(Coordinates spawn, Sprite sprite) {
		this.sprite = sprite;
		this.spawn = spawn;
	}

	public abstract void render(Screen screen);
	
	public abstract Coordinates getCoordinates();
	
	public boolean isInLocation(Coordinates coordinates) {
		return this.spawn.equals(coordinates);
	}
}
