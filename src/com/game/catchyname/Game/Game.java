package com.game.catchyname.Game;
import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;

import javax.swing.JFrame;

import com.game.catchyname.entity.mob.Player;
import com.game.catchyname.graphics.Screen;
import com.game.catchyname.input.Keyboard;
import com.game.cathyname.level.Level;
import com.game.cathyname.level.TileCoordinate;

public class Game extends Canvas implements Runnable{

	private static final long serialVersionUID = 1L;
	
	//Size
	public static int width = 300;
	public static int height = width / 16*9; // to be 16:9 aspect ratio(most new monitors)
	public static int scale = 3;    // I am gonna create a 900px windows with the performance of a 300px game
	//Game STATES
	private enum STATE{
		MENU,
		GAME
	}
	private STATE State = STATE.MENU;
	
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
	//private Menu menu;
	private long score;
	
	
	public Game() {
		Dimension size = new Dimension(width * scale, height * scale); //Resolution
		setPreferredSize(size);
//		System.out.println("The dimension of the game is" + size+ " and the height is" + height);
		
		screen  = new Screen(width, height);
		frame = new JFrame();
		key = new Keyboard();
		level = Level.spawn;
		
		TileCoordinate playerSpawn = new TileCoordinate(19,50);	//spawning position of player
		player = new Player(playerSpawn.getX(),playerSpawn.getY(),key);	// drawing player in the given position
		player.init(level);
		
		addKeyListener(key);
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
			long now = System.nanoTime();
			delta += (now - lastTime)/nanoConversion; //millis 
			lastTime = now;
			while(delta>=1) {
				update();
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
			}
		}
		stop();
	}
	
	
	public void update() {
		key.update();
		player.update();
	}
	
	
	public void render() {
		BufferStrategy bs = getBufferStrategy();
		if(bs == null) {
			createBufferStrategy(3);
			return;
		}
		screen.clear();
		int xScroll = player.x - screen.width /2;
		int yScroll = player.y - screen.height /2;
		//using xScroll and yScroll to render g amount of tiles right, left, bottom, and up of the player who is located in the middle of the screen
		level.render(xScroll,yScroll, screen);
		player.render(screen);
		
		
		//Copying the array of the Screen class to + yOffset the array
		for(int i=0;i<pixels.length;i++) {
			pixels[i] = screen.pixels[i];
		}
		
		Graphics g = bs.getDrawGraphics();//created a link between g and the actual Buffer
		g.drawImage(image, 0, 0, getWidth(), getHeight(), null);
		g.dispose(); 
		bs.show();
	}
	
	
	
	public static void main(String[] args ) {
		
		Game game = new Game();
		game.frame.setResizable(false);
		
		game.frame.add(game);//fills the window with an istance of Game, because of Canvas
		game.frame.pack(); // will make the window the size of the Preffered Size
		game.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		game.frame.setLocationRelativeTo(null);
		game.frame.setVisible(true);
		game.start();
	}
	
	
	
	
	
	
}
