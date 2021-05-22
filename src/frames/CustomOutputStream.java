package frames;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;

import javax.swing.JTextArea;

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
