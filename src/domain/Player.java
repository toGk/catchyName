package domain;

import java.io.Serializable;


public class Player implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Champion champion;
	private String name;
	
	public Player(String name,Champion champion) {
		this.champion = champion;
		this.name = name;
	}

	public Champion getChampion() {
		return champion;
	}
	
	public String getName() {
		return name;
	}

}
