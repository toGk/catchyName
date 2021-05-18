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

	public Item(Coordinates coordinates, Sprite testingSprite) {
		this.spawn = coordinates;
		this.sprite = testingSprite;
	}

	@Override
	public void render(Screen screen) {
		screen.renderPlayer(spawn.getX()-16, spawn.getY()-16, sprite);
	}

}
