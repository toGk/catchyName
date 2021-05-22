package domain;

import java.io.Serializable;


public class Player implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Champion champion;
	private String name;
	private int score;
	
	public Player(String name,Champion champion) {
		this.champion = champion;
		this.name = name;
		score=0;
	}

	public Champion getChampion() {
		return champion;
	}

	public void printData() {
		System.out.println(name + " --- " + score);
	}

	public void addPoints(int points) {
		score+=points;
	}

}
