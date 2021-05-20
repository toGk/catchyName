package champions;

import java.awt.event.KeyEvent;

import com.game.catchyname.graphics.Sprite;

import domain.Champion;
import domain.GameData;
import domain.Level;
import domain.Mob;
import utilities.Coordinates;

public class Archer extends Champion{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Archer(Coordinates playerSpawn,Sprite sprite) {
		super(playerSpawn,sprite);
	}
		
	public void update(Level level,boolean[] keyCode,GameData data) {
		super.update(level, keyCode, data);
		if(keyCode[KeyEvent.VK_C])this.lightAttack(data);
		if(keyCode[KeyEvent.VK_V])test();
	}
	
	protected void lightAttack(GameData data) {
		Mob temp = data.getAllmobs().getMob(this.getCoordinates());
		if(temp!=null) {
			super.damage(temp);
		}
	}

	public void test() {
		System.out.println("success");
	}
}
