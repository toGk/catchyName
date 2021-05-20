package frames;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import com.game.catchyname.graphics.Sprite;

import champions.Archer;
import champions.Assassin;
import champions.Mage;
import domain.Champion;
import domain.GameData;
import domain.GameDataList;
import domain.Player;
import utilities.Coordinates;

/*
 * this class is a JFrame for the NEW GAME options:
 * 
 * CHOOSE CHAMPION CLASS - ENTER NAME
 * 
 * then create actual gameplay, creates GameFrame()
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
	
	public NewGameFrame(GameDataList datalist) {
		this.datalist = datalist;
		
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

	    archer.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
            	try {
					checkAccount();
					createGame(new Archer(new Coordinates(19,50,5),Sprite.testingSprite));
				} catch (Exception e1) {
					e1.printStackTrace();
				}
            }
        });
	    assassin.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
            	try {
					checkAccount();
					createGame(new Assassin(new Coordinates(19,50,5),Sprite.testingSprite));
				} catch (Exception e1) {
					e1.printStackTrace();
				}
            }
        });
	    mage.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
            	try {
					checkAccount();
					createGame(new Mage(new Coordinates(19,50,5),Sprite.testingSprite));
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
		setTitle("New Game Frame");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	private void createGame(Champion champion){
	     datalist.saveData(name.getText(),new GameData(new Player(name.getText(),champion),"0"));
	     new GameFrame(datalist,name.getText());
	     dispose();
	}
	
	private void checkAccount() throws Exception {
		if( datalist.getData(name.getText()) != null) {
			printResult();
			throw new Exception();
		}		
	}
	
	private void printResult() {
		result.setText("user " + name.getText() + " already exists");
	}
}
