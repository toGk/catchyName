package com.game.catchyname.Game;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/*
 * this class is a JFrame for the NEW GAME options:
 * 
 * CHOOSE CHAMPION CLASS - ENTER NAME
 * 
 * then create actual gameplay, creates Game()
 */

public class NewGameFrame extends JFrame{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel panel;
	private JButton archer;
	private JButton assassin;
	private JButton mage;
	private JButton back;
	private JTextField name;
	private GameDataList datalist;
	private JTextArea result;
	private CustomOutputStream stream;
	
	public NewGameFrame(GameDataList datalist) {
		this.datalist = datalist;
		stream = new CustomOutputStream();
		
	    panel= new JPanel();
	    archer = new JButton("Archer");
	    assassin = new JButton("Assassin");
	    mage = new JButton("Mage");
	    name = new JTextField("Please enter your name");
	    back = new JButton("back");
	    result = new JTextArea();
	    
	    result = new JTextArea();
	    result.setEditable(false);
	    
	    panel.add(archer);
	    panel.add(assassin);
	    panel.add(mage);
	    panel.add(name);
	    panel.add(back);
	    panel.add(result);
		
		ButtonListener btnListener = new ButtonListener();
		archer.addActionListener(btnListener);
		assassin.addActionListener(btnListener);
		mage.addActionListener(btnListener);
		back.addActionListener(btnListener);
		
		this.getContentPane().add(panel);
		
		this.setVisible(true);
		this.setSize(300,300);
		setTitle("New Game Frame");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}


class ButtonListener implements ActionListener{
	@Override
	public void actionPerformed(ActionEvent e) {
		Coordinates playerSpawn = new Coordinates(19,50);
		if(e.getSource().equals(back)) {
		    new MainFrame();
		    dispose();
	    }else if(datalist.getData(name.getText()) == null){
		
		    if(e.getSource().equals(archer)) {
			    new GameFrame(datalist,new GameData(name.getText(),new Archer(playerSpawn)));
			    dispose();
		    }else if(e.getSource().equals(assassin)) {
			    new GameFrame(datalist, new GameData(name.getText(),new Assassin(playerSpawn)));
			    dispose();
		    }else if(e.getSource().equals(mage)) {
			    new GameFrame(datalist, new GameData(name.getText(),new Mage(playerSpawn)));
			    dispose();
		    }
		}else {
			stream.redirectStream(result);
			System.out.println("user " + name.getText() + " already exists");
		}
        try {
			stream.close();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		
	}
}
}
