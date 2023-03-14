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

public class GamePanel extends JPanel implements Runnable {
//test
	// screen settings
	final int originalTileSize = 16; // 16x16 tile
	final int scale = 3;

	public final int tileSize = originalTileSize * scale; // 48x48 tile
	public final int maxScreenCol = 16;
	public final int maxScreenRow = 12;
	public final int screenWidth = tileSize * maxScreenCol; // 768 pixels
	public final int screenHeight = tileSize * maxScreenRow; // 576 pixels
	
	// FPS
	int FPS = 60;

	// SYSTEM
	TileManager tileM = new TileManager(this);
	KeyHandler keyH = new KeyHandler();
	Sound sound = new Sound();
	Thread gameThread;
	public CollisionChecker cChecker = new CollisionChecker(this);

	// ENTITY / OBJECT
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
	public int RandomTurnTime()
	{
		int min_second_turn = 14;
		int max_second_turn = 17;
		
	    int random_second_per_turn = (int)Math.floor(Math.random() * (max_second_turn - min_second_turn + 1) + min_second_turn);
	    return random_second_per_turn;
	}

	@Override
	public void run() {

		double drawInterval = 1000000000 / FPS;
		double delta = 0;
		long lastTime = System.nanoTime();
		long currentTime;
		long timer = 0;
		
		// Set timer to let the player can run 14->17 second before going into attack mode
		long timer_2;
		long lastTime_timer2 = System.currentTimeMillis();
		
		int time_of_turn = RandomTurnTime();

		
		int drawCount = 0;

		while (gameThread != null) {

			currentTime = System.nanoTime();
			delta += (currentTime - lastTime) / drawInterval;
			timer += (currentTime - lastTime);
			timer_2 = System.currentTimeMillis() - lastTime_timer2;

			lastTime = currentTime;

			if (delta >= 1) {
				update();
				repaint();
				delta--;
				drawCount++;
			}
			if (timer_2 >= 1000 * time_of_turn) {
				player.SwitchMode();
				enemy.SwitchMode();
				System.out.println("Done " + time_of_turn + " second, Changing mode...");
//				Button_Panel button_Panel = new Button_Panel(this);
//				button_Panel.createSmallButton();
				lastTime_timer2 = System.currentTimeMillis();
				timer_2 = 0;
				time_of_turn = RandomTurnTime();
			}
			if (timer >= 1000000000) {
				System.out.println("FPS: " + drawCount);
				drawCount = 0;
				timer = 0;
			}
		}
	}

	public void ApplyMode(Enemy enemy, Player player) {
		enemy.update(player.GetMode());
		player.update(player.GetMode());			
	}

	public void update() {

		this.ApplyMode(enemy, player);
		
	}

	public void paintComponent(Graphics g) {

		super.paintComponent(g);

		Graphics2D g2 = (Graphics2D) g;

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
