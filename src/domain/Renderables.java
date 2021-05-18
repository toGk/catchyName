package domain;

import com.game.catchyname.graphics.Screen;
import com.game.catchyname.graphics.Sprite;

import utilities.Coordinates;

public abstract class Renderables {
	protected Sprite sprite;
	protected Coordinates spawn;
	
	public abstract void render(Screen screen);
}
