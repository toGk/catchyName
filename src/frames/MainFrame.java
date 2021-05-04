package frames;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import domain.GameDataList;
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
	private JButton clearAllData;
	private GameDataList datalist;
	
	public MainFrame() {
		datalist = GameDataList.loadGame();
		if(datalist==null) {
			datalist = new GameDataList();
		}
	    panel= new JPanel();
	    newGame = new JButton("New Game");
	    loadGame = new JButton("Load Game");
	    clearAllData = new JButton("Clear All Data");
	    
	    
	    panel.add(newGame);
	    panel.add(loadGame);
	    panel.add(clearAllData);
	    
	    newGame.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
    			new NewGameFrame(datalist);
    			dispose();
            }
        });
	    loadGame.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
    			new LoadFrame(datalist);
    			dispose();
            }
        });
		clearAllData.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
    			datalist = new GameDataList();
    			datalist.saveGame();
            }
        });
		
		this.getContentPane().add(panel);
		
		this.setVisible(true);
		this.setSize(300,300);
		setTitle("Main Frame");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
