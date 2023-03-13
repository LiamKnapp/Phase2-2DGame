package entity;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;
import javax.imageio.ImageIO;
import main.GamePanel;

public class Enemy extends Entity{

	GamePanel gp;
	
	public Enemy(GamePanel gp) {
		
		this.gp = gp;
		
		getRandomEnemy();
		getEnemyImage();
	}
	
	
	public void getRandomEnemy() {
		enemyName = "Orc";
		Random rn = new Random();
		int answer = rn.nextInt(3) + 1;
		
		switch (answer) {
		case 1:
			enemyName = "Orc";
			break;
		case 2:
			enemyName = "Skeleton";
			break;
		case 3:
			enemyName = "Slime";
			break;
		}
		setDefaultValues();
	}
	
	public void setDefaultValues() {
		x = gp.tileSize * 7;
		y = gp.tileSize * 3;
		
		switch (enemyName) {
		case "Orc":
			this.health = 10;
			this.damage = 3;
			break;
		case "Skeleton":
			this.health = 7;
			this.damage = 2;
			break;
		case "Slime":
			this.health = 4;
			this.damage = 5;
			break;
		}
	}
	
	public void getEnemyImage() {
		try {
			
			enemy1 = ImageIO.read(getClass().getResourceAsStream("/enemy/orc_down_2.png"));
			enemy2 = ImageIO.read(getClass().getResourceAsStream("/enemy/skeletonlord_phase2_down_1.png"));
			enemy3 = ImageIO.read(getClass().getResourceAsStream("/enemy/greenslime_down_1.png"));
			
		}catch(IOException e){
			e.printStackTrace();
		}
	}
	
	
	public void update() {
		//TODO
	}
	
	public void draw(Graphics2D g2) {
		
		BufferedImage image = null;
		
		switch (enemyName) {
		case "Orc":
			image = enemy1;
			break;
		case "Skeleton":
			image = enemy2;
			break;
		case "Slime":
			image = enemy3;
			break;
		}
		g2.drawImage(image, x, y, gp.tileSize, gp.tileSize, null);
	}
}
