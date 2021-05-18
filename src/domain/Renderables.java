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
	
	public abstract void render(Screen screen);
}
