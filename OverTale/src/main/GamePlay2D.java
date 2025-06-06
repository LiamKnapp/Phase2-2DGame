package main;


import java.awt.Color;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JPanel;

import entity.Enemy;
import entity.Player;
import entity.Projectile;
import objects.HealItem;
import tile.TileManager;
import data.CareTaker;
import data.Memento;

public class GamePlay2D extends JPanel implements GamePlayStory {
	// screen settings
	final int originalTileSize = 16; // 16x16 tile
	final int scale = 3;
	public final int tileSize = originalTileSize * scale; // 48x48 tile
	public final int maxScreenCol = 16;
	public final int maxScreenRow = 12;
	public final int screenWidth = tileSize * maxScreenCol; // 768 pixels
	public final int screenHeight = tileSize * maxScreenRow; // 576 pixels
	int FPS = 60;

	// SYSTEM
	TileManager tileM = new TileManager(this);
	KeyHandler keyH = new KeyHandler(this);
	Sound sound = new Sound();
	Thread gameThread;
	public CollisionChecker cChecker = new CollisionChecker(this);
	public boolean attackMode = false;
	public boolean gameOver = false;
	int surviveCounter = 0;
	public UI ui = new UI(this);
	private GamePlay2D state;
	public CareTaker careTaker = new CareTaker();

	// ENTITY / OBJECT
	public ArrayList <Projectile> projectileList = new ArrayList<Projectile>();
	Enemy enemy = new Enemy(this);
	private Player player = new Player(this, keyH);
	public boolean enemytakeDMG = false;
	public boolean playerHeal = false;
	
	// GAME STATE 
	public int gameState;
	public final int titleState = 0;
	public final int playState = 1;
	public final int deathState = 2;
	public final int loadState = 3;
	boolean turnSwitch = false;
	
	public GamePlay2D() {

		this.setPreferredSize(new Dimension(screenWidth, screenHeight));
		this.setBackground(Color.black);
		this.setDoubleBuffered(true);
		this.addKeyListener(keyH);
		this.setFocusable(true);
		gameSetup();
	}
	@Override
	public GamePlay2D getGamePlayPanel() {
		return this;		
	}

	public void gameSetup() {
		System.out.println("Round: " + surviveCounter);
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
		
		gameState = titleState;
	}

	@Override
	public void startGamePlay() {
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
			
			/*if (gameState != playState)
			{
				continue;
			}*/
			
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
			
			if (attackMode == true) {
				
				if (enemytakeDMG == true) { // if the user selects to attack the enemy on there turn
					getPlayer().SwitchMode();
					enemy.SwitchMode();
					System.out.println("Done " + time_of_turn + " second, Changing mode...");
					lastTime_timer2 = System.currentTimeMillis();
					timer_2 = 0;
					time_of_turn = RandomTurnTime();
					enemy.setHealth();
					enemytakeDMG = false;
				}
				
				if (playerHeal == true) { // if the user selects to attack the enemy on there turn
					getPlayer().SwitchMode();
					enemy.SwitchMode();
					System.out.println("Done " + time_of_turn + " second, Changing mode...");
					lastTime_timer2 = System.currentTimeMillis();
					timer_2 = 0;
					time_of_turn = RandomTurnTime();
					getPlayer().setHealth();
					playerHeal = false;
				}
				
				//System.out.println("Users turn: select fight or item!");
			} else {
			
				//if (turnSwitch == true)
				//{
					if (timer_2 >= 1000 * time_of_turn) {
						ui.commandNum = 0;
						getPlayer().SwitchMode();
						enemy.SwitchMode();
						System.out.println("Done " + time_of_turn + " second, Changing mode...");
						lastTime_timer2 = System.currentTimeMillis();
						timer_2 = 0;
						time_of_turn = RandomTurnTime();
					}
				//}
				//System.out.println("Timer2: " + timer_2);
			}
			
			if (timer >= 1000000000) {
				//System.out.println("FPS: " + drawCount);
				getCurrentMode();
				drawCount = 0;
				timer = 0;
			}
		}
	}

	public void ApplyMode(Enemy enemy, Player player) {
		enemy.update(player.GetMode());
		player.update(player.GetMode());			
	}
	
	public void getCurrentMode() {
		String modeString = getPlayer().GetMode();
		if(modeString == "Defence")
		{
			attackMode = false;
			enemytakeDMG = false; // for extra security for bug fixing
			playerHeal = false;
			//System.out.println("Current Mode: Defence");
		} else {
			//System.out.println("Current Mode: Attack");
			attackMode = true;
		}
	}

	public void update() {
		
		if (gameState == playState) {
			this.ApplyMode(enemy, getPlayer());
			
			if (attackMode == true) {
				//update the hp of the projectile to draw it for a certain distance
				for (int i = 0; i < projectileList.size(); i++) {
					if (projectileList.get(i) != null) {
						if(projectileList.get(i).health <= 0) {
							projectileList.remove(i);
						}
					}
				}
			}
			//update the hp of the projectile to draw it for a certain distance
			for (int i = 0; i < projectileList.size(); i++) {
				if (projectileList.get(i) != null) {
					if (projectileList.get(i).health > 0) {
						projectileList.get(i).update();
					}
					if(projectileList.get(i).health <= 0) {
						projectileList.remove(i);
					}
				}
			}
			
			if (enemy.health <= 0) {
				enemy = new Enemy(this);
				surviveCounter = surviveCounter + 1;
				
				Memento memento = new Memento(this);
				careTaker.addMemento(memento);
				
				System.out.println("Round Complete!");
				System.out.println("Survived Rounds: " + surviveCounter);
				System.out.println("New Enemy Appeared!!!");
			}
			
			if (getPlayer().health <= 0) {
				System.out.println("Round Failed!");
				System.out.println("Survived Rounds: " + surviveCounter);
				turnSwitch = false;
				gameState = deathState;
				//call player class deconstructor 
				//call enemy class deconstructor
				//stop game loop
				//memento return to previous state or close game
			}
		}
	}

	public void paintComponent(Graphics g) {

		super.paintComponent(g);

		Graphics2D g2 = (Graphics2D) g;
		
		ui.draw(g2);
		
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
	
	public void resetGame() {
		projectileList = new ArrayList<Projectile>();
		enemy = new Enemy(this);
		setPlayer(new Player(this, keyH));
		
		this.surviveCounter = 0;
	}
	
	public int GetPlayerHealth() {
		return getPlayer().health;
	}
	
	public String GetPlayerMode() {
		return getPlayer().GetMode();
	}
	
	public int GetEnemyHealth() {
		return enemy.health;
	}
	
	public String GetEnemyMode() {
		return enemy.GetMode();
	}
	
	public String GetEnemyType() {
		return enemy.enemyName;
	}
	
	public int GetCurrentRound() {
		return this.surviveCounter;
	}
	
	public ArrayList<HealItem> GetHealItemList () {
		return getPlayer().GetPlayerHealItems();
	}
	
	public void setMemento(Memento memento) {
		//Player
		getPlayer().health = memento.getPlayerHealth();
		getPlayer().SetModeString(memento.getPlayerModeString());
		
		//Enemy
		enemy.health = memento.getEnemyHealth();
		enemy.SetModeString(memento.getEnemyModeString());;
		enemy.enemyName = memento.getEnemyName();
		
		//Round counter
		surviveCounter = memento.getSurviveCounter();
		
		//Player items
		getPlayer().setHealItemList(memento.getHealNameList(), memento.getHealAmountList());
	}
	public Player getPlayer() {
		return player;
	}
	public void setPlayer(Player player) {
		this.player = player;
	}




	
}