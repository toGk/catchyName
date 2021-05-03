package com.game.catchyname.Game;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;

import javax.swing.JTextArea;

/*THIS IS A UTIL CLASS
 * 
 * USAGE: REDIRECTING A PRINT METHOD/STREAM INTO A SPECIFIC AREA OF A FRAME
 * 
 * GUIDE/EXAMPLE:
 *         
 *         private CustomOutputStream mystream;
 *         private JTextArea area; //or equivalent
 *         
 *         myclass(){
 *               mystream = new CustomOutputStream();//it is initialized when i plan to first 
 *                                                   //execute a method that generates a stream
 *                                                   //and i plan to redirect it 
 *               mystream.redirectStream(area);
 *               System.out.print(); //or anything that may produce a stream
 *               
 *               mystream.redirectStream(area2);
 *               amethod2();
 *               
 *               mystream.redirectStream(area3);
 *               amethod3();
 *               
 *               ...
 *               
 *               try {
			    stream.close(); //and at the end of the block i close it(or when there is a termination)
		         } catch (IOException e1) {
			        e1.printStackTrace();
		         }
 *         }
 * 
 */

public class CustomOutputStream extends OutputStream{
    private JTextArea textArea;

	public CustomOutputStream() {
		textArea=null;       
    }
     
    @Override
    public void write(int b) throws IOException {
        // redirects data to the text area
        textArea.append(String.valueOf((char)b));
        // scrolls the text area to the end of data
        textArea.setCaretPosition(textArea.getDocument().getLength());
    }

	public void redirectStream(JTextArea textArea) {
		this.textArea = textArea; 
        System.setOut(new PrintStream(this));
        System.setErr(new PrintStream(this));	
	}

}
