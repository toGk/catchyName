package com.game.catchyname.Game;

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
import com.game.cathyname.level.Level;

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
	
	private Champion champion;
	private Level level;
	
	private Screen screen;
	private JFrame frame;
	private JButton pauseButton;
	private Keyboard key;
	
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
	
	class ButtonListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			if(e.getSource().equals(pauseButton)) {				
				if(paused==false) {
					pauseButton.setText("Resume");
				}else {
					pauseButton.setText("  Pause  ");
				}
				paused =! paused;
			}
		}
	}
	
	public GameFrame(GameDataList datalist, GameData gamedata) {
		this.datalist = datalist;
		this.gamedata = gamedata;
		champion = gamedata.getPlayer().getChampion();
		level = Level.spawn;
		champion.init(level);
		screen = new Screen(width, height);
		paused = false;

		frame = new JFrame();
		pauseButton = new JButton("  Pause  ");
		
		//frame.setLayout(new GridLayout ());
		frame.setLayout(new FlowLayout ());
		frame.add(pauseButton);
		
		ButtonListener btnListener = new ButtonListener();
		pauseButton.addActionListener(btnListener);
		
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
	
	private void save() {
		datalist.saveData(gamedata);
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
			if(paused==false) {
			long now = System.nanoTime();
			delta += (now - lastTime)/nanoConversion; //millis 
			lastTime = now;
			while(delta>=1) {	
				this.requestFocus(true);
				key.update();
				int xa = 0, ya = 0;
				if(key.up)ya--;
				if(key.down)ya++;
				if(key.left)xa--;
				if(key.right)xa++;
				if(key.f1) {
					save();
				}
					
				if((xa != 0 || ya != 0)){
					champion.move(xa,ya);
				}
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
		  }
		}
		stop();
	}
	public void paintComponent(Graphics g) {
		screen.clear();
		level.render(champion.getX() - screen.width /2,champion.getY() - screen.height /2, screen);
		champion.render(screen);
		for(int i=0;i<pixels.length;i++) {
			pixels[i] = screen.pixels[i];
		}
		g.drawImage(image, 0, 0, getWidth(), getHeight(), null);
		g.dispose(); 
	}
}
