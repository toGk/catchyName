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

import domain.Champion;
import domain.GameData;
import domain.GameDataList;
import domain.Item;
import domain.Level;
import domain.lists.ItemList;
import domain.lists.MobList;

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
	private ItemList allItems;
	private MobList allMobs;
    
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
	
	public GameFrame(GameDataList datalist, String name) {
		this.datalist = datalist;
		this.gamedata = datalist.getData(name);
		champion = gamedata.getPlayer().getChampion();
		level = gamedata.getLevel();
		allItems = gamedata.getItemList();
		allMobs = gamedata.getMobList();
		
		screen = new Screen(width, height);
		paused = false;

		frame = new JFrame();
		pauseButton = new JButton("  Pause  ");
		
		//frame.setLayout(new GridLayout ());
		frame.setLayout(new FlowLayout ());
		frame.add(pauseButton);
		
		pauseButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
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
	
	private void saveGame() {
		datalist.saveGame();
		frame.setTitle("SAVED");
	}
	
	private void pickItem() {
		Item temp = allItems.getItem(champion.getCoordinates());
		if(temp!=null) {
		   champion.pickItem(temp);
		   allItems.remove(champion.getCoordinates(),temp);
		   frame.setTitle("Item picked!!!");
		}
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
					saveGame();
				}
				if(key.f2) {
					pickItem();
				}
				if((xa != 0 || ya != 0)){
					champion.move(xa,ya,level);
				}
				delta--; 
				updates++;
			}
			frames++;
			repaint();
			if(System.currentTimeMillis()  - timer > 1000) {//1sec = 1000 millis
				timer+= 1000;// to display other than the first time correctly the FPS AND UPDATES
				
				frame.setTitle("Game" +"|" +updates+" UPS |"+frames+" FPS" + champion.getCoordinates() + " + " + champion.getX()+ " + " + champion.getY()+"+"+allItems.getC().keySet());
				frames=0;
				updates=0;
			}
		  }
		}
		stop();
	}
	
	private void render() {
		screen.clear();
		level.render(champion.getX() - screen.getWidth() /2,champion.getY() - screen.getHeight() /2, screen);
		allMobs.render(screen, allItems);
		allItems.render(screen);
		champion.render(screen);	
	}
	
	public void paintComponent(Graphics g) {
		render();	
		for(int i=0;i<pixels.length;i++) {
			pixels[i] = screen.pixels[i];
		}
		g.drawImage(image, 0, 0, getWidth(), getHeight(), null);
		g.dispose(); 
	}
}
