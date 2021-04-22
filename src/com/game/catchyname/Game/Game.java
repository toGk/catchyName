package com.game.catchyname.Game;
import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JPanel;

//import com.game.catchyname.entity.mob.Player;
import com.game.catchyname.graphics.Screen;
import com.game.cathyname.level.Level;

public final class Game extends Canvas implements Runnable{

	private static final long serialVersionUID = 1L;
	
	//Size
	public static int width = 300;
	public static int height = width / 16*9; // to be 16:9 aspect ratio(most new monitors)
	public static int scale = 3;    // I am gonna create a 900px windows with the performance of a 300px game
	
	private String title = "Game";
	private Thread gameThread; 
	private JFrame frame;
	private boolean running = false; 
	
	private BufferedImage image = new BufferedImage(width,height,BufferedImage.TYPE_INT_RGB); //creating an image with an accesible buffer
	private int[] pixels = ((DataBufferInt)image.getRaster().getDataBuffer()).getData(); //Accessing the image, converting image into an array of pixels
	
	private Screen screen;
	private Keyboard key;
	private Level level;
	private Player player;
	private Champion champion;
	private GameData data;
	private GameDataList datalist;
	
	private long score;
	
	private PauseFrame pframe;

	private boolean gActive;

	private JPanel panel;
	
	 class Keyboard implements KeyListener{

		private boolean[] keys = new boolean[125];
		public boolean up,down,left,right,f1,f2; // keeps track if I pressed this keys
		public boolean active = true;
		
		public void update() {
			if(active) {
		up = keys[KeyEvent.VK_UP] || keys[KeyEvent.VK_W];
		down = keys[KeyEvent.VK_DOWN] || keys[KeyEvent.VK_S];
		right = keys[KeyEvent.VK_RIGHT] || keys[KeyEvent.VK_D];
		left = keys[KeyEvent.VK_LEFT] || keys[KeyEvent.VK_A];
		f1 = keys[KeyEvent.VK_F1];
		f2 = keys[KeyEvent.VK_F2];
			}
		}
		public void keyTyped(KeyEvent e) {

		}
		public void keyPressed(KeyEvent e) {
		    if(active)keys[e.getKeyCode()] = true; 
		}
		public void keyReleased(KeyEvent e) {
			if(active)keys[e.getKeyCode()] = false; 
		}
	}
	
	class PauseFrame extends JInternalFrame{
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		private JPanel panel;
		private JButton pause;
		private boolean pauseActive;
		
		public boolean getPauseValue() {
			return pauseActive;
		}

		public PauseFrame(){
			pauseActive = false;
			
		    panel= new JPanel();
		    pause = new JButton("Pause");		 
		    
		    panel.add(pause);
			
			ButtonListener btnListener = new ButtonListener();
			pause.addActionListener(btnListener);
			
			this.getContentPane().add(panel);
			
			this.setVisible(true);
			this.setSize(300,300);
			setTitle("Pause Frame");
			this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		}
		
		class ButtonListener implements ActionListener{
			@Override
			public void actionPerformed(ActionEvent e) {
				if(e.getSource().equals(pause)) {
					if(pauseActive==false) {
						pause.setText("Resume");
						gActive = false;
					}else {
						pause.setText("Pause");
						gActive = true;
					}
					pauseActive =! pauseActive;
				}
			}
		}
	}
	
	public void save() {
		 //datalist.saveGame(data);
	}
	private boolean test;
	public Game(GameDataList datalist, GameData data) {
		this.datalist = datalist;
		this.data = data;
		this.champion = data.getPlayer().getChampion();
		gActive = true;
		
		pframe= new PauseFrame();
		panel = new JPanel();
		//this.getContentPane().add(panel);
		
		//this.setVisible(true);
		//this.setPreferredSize(new Dimension(width * scale, height * scale));
		//setTitle("THE TITLE OF THE GAME");
		//this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		frame = new JFrame();
		
		level = Level.spawn;
		Dimension size = new Dimension(width * scale, height * scale); //Resolution
		setPreferredSize(size);
//		System.out.println("The dimension of the game is" + size+ " and the height is" + height);
		
		screen  = new Screen(width, height);
		key = new Keyboard();
		level = Level.spawn;
		champion.init(level);
		
		addKeyListener(key);
		champion.init(level);
		
		key = new Keyboard();
		
		addKeyListener(key);
		
		frame.setResizable(true);
		/*frame.addWindowListener(new WindowAdapter() {
			   public void windowClosing(WindowEvent evt) {
			     if(GameDataList.reading==false) {
			    	  System.err.println("Exit");
			    	  System.exit(0);
			     }
			   }
			  });*/
		 JButton pause = new JButton("Pause");
		 test = false;
		class ButtonListener implements ActionListener{
			@Override
			public void actionPerformed(ActionEvent e) {
				if(e.getSource().equals(pause)) {
					if(test==false) {
						pause.setText("Resume");
						gActive = false;
					}else {
						pause.setText("Pause");
						gActive = true;
					}
					test =! test;
				}
			}
		}
		
		ButtonListener btnListener = new ButtonListener();
		pause.addActionListener(btnListener);
		panel.add(pause);
		frame.getContentPane().add(pframe);
	    pframe.add(this);
		//frame.getContentPane().add(new PauseFrame());
		frame.getContentPane().add(panel);
		
		//frame.add(this);//fills the window with an istance of Game, because of Canvas
		frame.pack(); // will make the window the size of the Preffered Size
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		start();
		
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
	
	public void run() {
		long timer = System.currentTimeMillis();
		long lastTime = System.nanoTime();
		final double nanoConversion = 1000000000.0 / 60.0 ;// limits to 60 updates per second Timer
		double delta = 0;
		int frames = 0;
		int updates = 0;
		requestFocus();// frame is Focused, don't have to click the frame to make it in Focus
		while (running) {
			if(test==false) {
				//Keyboard.active = true;
			long now = System.nanoTime();
			delta += (now - lastTime)/nanoConversion; //millis 
			lastTime = now;
			while(delta>=1) {		
				key.update();
					int xa = 0, ya = 0;
					if(key.up)ya--;
					if(key.down)ya++;
					if(key.left)xa--;
					if(key.right)xa++;
					if(key.f1) {
			             //this.damage(attack);
						//new MainFrame();
					}	
					if(key.f2) {
						save();
					}
					if(xa != 0 || ya != 0) champion.move(xa,ya);		
				delta--; 
				updates++;
			}
			frames++;
			render();//display image	
			if(System.currentTimeMillis()  - timer > 1000) {//1sec = 1000 millis
				timer+= 1000;// to display other than the first time correctly the FPS AND UPDATES
				
				frame.setTitle(title +"|" +updates+" UPS |"+frames+" FPS"+"|  SCORE  "+score);
				frames=0;
				updates=0;
			}//System.out.println("game run");
		  }
		}
		stop();
	}
	
	public void render() {
		BufferStrategy bs = getBufferStrategy();
		if(bs == null) {
			createBufferStrategy(3);
			return;
		}
		screen.clear();
		int xScroll = champion.getX() - screen.width /2;
		int yScroll = champion.getY() - screen.height /2;
		//using xScroll and yScroll to render g amount of tiles right, left, bottom, and up of the player who is located in the middle of the screen
		level.render(xScroll,yScroll, screen);
		champion.render(screen);
		//Copying the array of the Screen class to + yOffset the array
		for(int i=0;i<pixels.length;i++) {
			pixels[i] = screen.pixels[i];
		}
		Graphics g = bs.getDrawGraphics();//created a link between g and the actual Buffer
		g.drawImage(image, 0, 0, getWidth(), getHeight(), null);
		g.dispose(); 
		bs.show();
	}
}
