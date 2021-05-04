package frames;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import domain.GameDataList;

/*
 * this class is a JFrame for the LOAD GAME options:
 * 
 * ENTER NAME ->Search inside a file for the pair((HashMap)) of the name and data
 * 
 * throws exceptions: FileNotFound, DataNotFound(wrong key) - from the GameDataList
 * prints the possible error message at JFrame
 * 
 * 
 * then create actual gameplay, creates GameFrame()
 */

public class LoadFrame extends JFrame{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel panel;
	private JButton loadGame;
	private JButton back;
	private JTextField name;
	private JTextArea result;
	private GameDataList datalist;
	
	public LoadFrame(GameDataList datalist) {
		this.datalist = datalist;
	    panel= new JPanel();
	    loadGame = new JButton("Load Game");
	    back = new JButton("back");
	    name = new JTextField("Please enter your name");
	    
	    result = new JTextArea();
	    result.setEditable(false);
	    
		
	    panel.add(loadGame);
	    panel.add(name);
	    panel.add(back);
	    panel.add(result);
		
	    loadGame.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
            	try {
					checkAccount();
					new GameFrame(datalist,name.getText());
    			    dispose();
				} catch (Exception e1) {
					e1.printStackTrace();
				}

            }
        });
	    back.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
    			new MainFrame();
    			dispose();
            }
        });
		
		this.getContentPane().add(panel);
		
		this.setVisible(true);
		this.setSize(300,300);
		setTitle("Load Frame");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	private void checkAccount() throws Exception {
		if( datalist.getData(name.getText()) == null) {
			printResult();
			throw new Exception();
		}
	}
	
	private void printResult() {
		result.setText("user " + name.getText() + " NOT exists");
	}
}
