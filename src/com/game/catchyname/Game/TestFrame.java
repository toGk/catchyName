package com.game.catchyname.Game;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class TestFrame extends JFrame{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private JPanel panel;
	private JButton save;
    
	public TestFrame() {
		panel= new JPanel();
		save = new JButton("Save");
		
		ButtonListener btnListener = new ButtonListener();
		panel.add(save);
			
		save.addActionListener(btnListener);
		
		this.getContentPane().add(panel);
		
		this.setVisible(true);
		this.setSize(300,300);
		setTitle("THE TITLE OF THE GAME");
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }
	
	class ButtonListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			if(e.equals(save)) {
				System.out.println("TEST");
			}
			
		}

	}
}
