package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.Random;

import javax.swing.JPanel;

import entity.Enemy;
import entity.Player;
import tile.TileManager;

public class GamePanel extends JPanel implements Runnable{
//test
	//screen settings
	final int originalTileSize = 16; // 16x16 tile
	final int scale = 3;
	
	public final int tileSize = originalTileSize * scale; // 48x48 tile
	public final int maxScreenCol = 16;
	public final int maxScreenRow = 12;
	public final int screenWidth = tileSize * maxScreenCol; // 768 pixels
	public final int screenHeight = tileSize * maxScreenRow; // 576 pixels
	
	//FPS
	int FPS = 60;
	
	//SYSTEM
	TileManager tileM = new TileManager(this);
	KeyHandler keyH = new KeyHandler();
	Sound sound = new Sound();
	Thread gameThread;
	public CollisionChecker cChecker = new CollisionChecker(this);
	
	
	//ENTITY / OBJECT
	Enemy enemy = new Enemy(this);
	Player player = new Player(this, keyH);
	
	public GamePanel() {
		
		this.setPreferredSize(new Dimension(screenWidth, screenHeight));
		this.setBackground(Color.black);
		this.setDoubleBuffered(true);
		this.addKeyListener(keyH);
		this.setFocusable(true);
		
		gameSetup();
	}
	
	public void gameSetup() {
		Random rn = new Random();
		int answer = rn.nextInt(2);
		switch (answer) {
			case 0:
				playMusic(0);
				break;
			case 1:
				playMusic(1);
				break;
		}
	}

	public void startGameThread() {
		gameThread = new Thread(this);
		gameThread.start();
	}
	
	
	@Override
	public void run() {
		
		double drawInterval = 1000000000/FPS;
		double delta = 0;
		long lastTime = System.nanoTime();
		long currentTime;
		long timer = 0;
		int drawCount = 0;
		
		while(gameThread != null) {
			
			currentTime = System.nanoTime();
			delta+= (currentTime - lastTime) / drawInterval;
			timer +=(currentTime - lastTime);
			lastTime = currentTime;
			
			if(delta >= 1) {
				update();
				repaint();
				delta--;
				drawCount++;
			}
			
			if(timer >= 1000000000) {
				System.out.println("FPS: " + drawCount);
				drawCount = 0;
				timer = 0;
			}
		}
	}
		
		public void update() {
			
		enemy.update();
		player.update();
			
		}
		
		public void paintComponent(Graphics g) {
			
			super.paintComponent(g);
			
			Graphics2D g2 = (Graphics2D)g;
			
			tileM.draw(g2);
			
			enemy.draw(g2);
			
			player.draw(g2);
			
			g2.dispose();
		}		
		
		public void playMusic(int i) {
			
			sound.setFile(i);
			sound.checkVolume();
			sound.play();
			sound.loop();
		}
		
		public void stopMusic(int i) {
			
			sound.stop();
		}
		
		public void playSE(int i) {
			
			sound.setFile(i);
			sound.checkVolume();
			sound.play();
		}
}	
