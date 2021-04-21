package com.game.catchyname.Game;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
/*
 * this class is a JFrame for the main menu:NEW GAME, LOAD GAME, SETTINGS, SCOREBOARD
 */

public class MainFrame extends JFrame{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel panel;
	private JButton newGame;
	private JButton loadGame;
	
	public MainFrame() {
	    panel= new JPanel();
	    newGame = new JButton("New Game");
	    loadGame = new JButton("Load Game");
	    
	    
	    panel.add(newGame);
	    panel.add(loadGame);
	    
		
		ButtonListener btnListener = new ButtonListener();
		newGame.addActionListener(btnListener);
		loadGame.addActionListener(btnListener);
		
		this.getContentPane().add(panel);
		
		this.setVisible(true);
		this.setSize(300,300);
		setTitle("Main Frame");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}


class ButtonListener implements ActionListener{
	@Override
	public void actionPerformed(ActionEvent e) {
		GameDataList datalist = GameDataList.loadGame();
		if(datalist==null) {
			datalist = new GameDataList();
		}
		if(e.getSource().equals(newGame)) {
			new NewGameFrame(datalist);
			dispose();
		}else if(e.getSource().equals(loadGame)) {
			new LoadFrame(datalist);
			dispose();
		}
	}
}
}
