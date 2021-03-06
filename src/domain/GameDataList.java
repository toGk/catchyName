package domain;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.HashMap;

public final class GameDataList implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private HashMap<String,GameData> data;
	
	public GameDataList() {
		data = new HashMap<String,GameData>();
	}
	
	public GameData getData(String name) {
		return data.get(name);
	}
	
	public void saveData(String name, GameData gamedata) {
		this.data.put(name, gamedata);
	}
	
	public void saveGame() {
		try {
			FileOutputStream fileOut = new FileOutputStream("game.ser");
			ObjectOutputStream out = new ObjectOutputStream(fileOut);	
			out.writeObject(this);
			out.close();
		    fileOut.close();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		
	}
	
	public static GameDataList loadGame() {
		try {
			FileInputStream fileIn = new FileInputStream("game.ser");
			ObjectInputStream in = new ObjectInputStream(fileIn);
			GameDataList data = (GameDataList)in.readObject();
		    in.close();
		    fileIn.close();
		    return data;
		} catch (IOException e1) {
			e1.printStackTrace();
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		}
		return null;
	}

	public void printData() {
		System.out.println("Player's Name ||| score!!!");
		for(String key: data.keySet()) {
			data.get(key).getPlayer().printData();
		}
	}

}
