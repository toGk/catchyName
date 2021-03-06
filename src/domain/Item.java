package domain;

import java.io.Serializable;

import com.game.catchyname.graphics.Screen;
import com.game.catchyname.graphics.Sprite;

import utilities.Coordinates;

public class Item extends Renderables implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;	

	public Item(Coordinates coordinates, Sprite sprite) {
		super(coordinates,sprite);
	}

	@Override
	public void render(Screen screen) {
		screen.renderPlayer(coordinates.getX()-16, coordinates.getY()-16, sprite);
	}

	@Override
	public Coordinates getCoordinates() {
		return coordinates;
	}
}
