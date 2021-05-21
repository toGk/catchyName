package frames;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import com.game.catchyname.graphics.Screen;

import domain.GameData;
import domain.GameDataList;

public class GameFrame extends JPanel implements Runnable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final int width = 300;
	private final int height = width / 16*9;
	private final int scale = 3;
	private BufferedImage image = new BufferedImage(width,height,BufferedImage.TYPE_INT_RGB); //creating an image with an accesible buffer
	private int[] pixels = ((DataBufferInt)image.getRaster().getDataBuffer()).getData();
	
	private GameDataList datalist;
	private GameData gamedata;
    
	private boolean running;
	private Thread gameThread;
	private boolean paused;
	
	private Screen screen;
	private JFrame frame;
	private JButton pauseButton;
	private Keyboard key;
	
	class Keyboard implements KeyListener{
		private boolean[] keycodes = new boolean[125];

		public void keyTyped(KeyEvent e) {
			
		}
		public void keyPressed(KeyEvent e) {
			keycodes[e.getKeyCode()] = true; 
		}
		public void keyReleased(KeyEvent e) {
			keycodes[e.getKeyCode()] = false; 
		}
	}
	
	public GameFrame(GameDataList datalist, String name) {
		this.datalist = datalist;
		this.gamedata = datalist.getData(name);
		screen = new Screen(width, height);
		gamedata.setScreen(screen);


		paused = false;

		frame = new JFrame();
		pauseButton = new JButton("  Pause  ");
		
		//frame.setLayout(new GridLayout ());
		frame.setLayout(new FlowLayout ());
		frame.add(pauseButton);
		
		pauseButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
            	//TODO:PAUSEFRAME
				if(paused==false) {
					pauseButton.setText("Resume");
				}else {
					pauseButton.setText("  Pause  ");
				}
				paused =! paused;
            }
        });
		
		key = new Keyboard();
		this.addKeyListener(key);

		frame.setVisible(true);
		frame.setResizable(true);
		frame.getContentPane().add(this);
		this.setPreferredSize(new Dimension(width * scale, height * scale));
		frame.setSize(width * scale + 150,height * scale+50);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		start();
	}
	
	private synchronized void saveGame() {
		datalist.saveGame();
		frame.setTitle("SAVED");
	}
	
	public synchronized void start() {
		running = true;
		gameThread = new Thread(this, "Game");
		gameThread.start(); 
	}

	public synchronized void stop() {
		running = false;
		try {
			gameThread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void run() {
		long timer = System.currentTimeMillis();
		long lastTime = System.nanoTime();
		final double nanoConversion = 1000000000.0 / 60.0 ;// limits to 60 updates per second Timer
		double delta = 0;
		int frames = 0;
		int updates = 0;
		while (running) {
			if(paused==false&&gamedata.gameIsOn()) {
			long now = System.nanoTime();
			delta += (now - lastTime)/nanoConversion; //millis 
			lastTime = now;
			while(delta>=1) {
				this.requestFocus(true);
				if(key.keycodes[KeyEvent.VK_F1]) {
					saveGame();
				}
                gamedata.update(key.keycodes);	
				delta--; 
				updates++;
			}
			frames++;
		    repaint();
			if(System.currentTimeMillis()  - timer > 1000) {//1sec = 1000 millis
				timer+= 1000;// to display other than the first time correctly the FPS AND UPDATES			
				frame.setTitle("Game" +"|" +updates+" UPS |"+frames+" FPS");
				frames=0;
				updates=0;
			}
		  }else {
			  //TODO:endFrame
		  }
		}
		stop();
	}

	public void paintComponent(Graphics g) {
		screen.clear();
		gamedata.render();	
		for(int i=0;i<pixels.length;i++) {
			pixels[i] = screen.pixels[i];
		}
		g.drawImage(image, 0, 0, getWidth(), getHeight(), null);
		g.dispose(); 
	}
}
