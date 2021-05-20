package champions;

import java.awt.event.KeyEvent;

import com.game.catchyname.graphics.Sprite;

import domain.Champion;
import domain.GameData;
import domain.Level;
import utilities.Coordinates;

public class Mage extends Champion{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Mage(Coordinates playerSpawn,Sprite sprite) {
		super(playerSpawn,sprite);
	}

	public void update(Level level,boolean[] keyCode,GameData data) {
		super.update(level, keyCode, data);
		if(keyCode[KeyEvent.VK_C]);
		if(keyCode[KeyEvent.VK_V]);
	}
}
