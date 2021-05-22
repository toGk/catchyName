package frames;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import domain.GameDataList;

public class ScoreBoardFrame extends JFrame{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private JPanel panel;
	private JButton back;
	private JTextArea result;
	private CustomOutputStream stream;

	public ScoreBoardFrame(GameDataList datalist) {
		panel = new JPanel();
		result = new JTextArea("Results");
	    result.setEditable(false);
		back = new JButton("back");
		
		panel.add(result);
		panel.add(back);
		
		stream = new CustomOutputStream();
		
		stream.redirectStream(result);
		datalist.printData();
		try {
			stream.close();
		} catch (IOException e1) {
			e1.printStackTrace();
		}

	    back.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
    		    new MainFrame();
    		    dispose();
            }
        });
		
		this.getContentPane().add(panel);
		
        this.setVisible(true);
        this.setSize(300,300);
        this.setTitle("Score Board");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
