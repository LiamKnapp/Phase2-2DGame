package entity;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;
import javax.imageio.ImageIO;

import main.GamePanel;

public class Enemy extends Entity{

	GamePanel gp;
	String modeString;
	int numberOfProjectile;
	int speedOfProjectile;
	int HealthOfProjectile;

	
	public Enemy(GamePanel gp) {
		this.modeString = "Attack";
		this.gp = gp;
		enemyVisible = true;
		getRandomEnemy();
		getEnemyImage();
	}
	
	public String GetMode()
	{
		return this.modeString;
	}
	
	public void SwitchMode()
	{
		if(modeString == "Defence")
		{
			this.modeString = "Attack";
		}else {this.modeString = "Defence";}
		System.out.println("Switch enermy mode to " + this.modeString);
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
		int min = 50; // Minimum value of range
	    int max = 100; // Maximum value of range
		setDefaultValues();
	}
	
	public void setDefaultValues() {
		x = gp.tileSize * 7;
		y = gp.tileSize * 3;
		
		switch (enemyName) {
		case "Orc":
			health = 13;
			damage = 3;
			numberOfProjectile = 5;
			speedOfProjectile = 3;
			HealthOfProjectile = 105;
			break;
		case "Skeleton":
			health = 12;
			damage = 2;
			numberOfProjectile = 7;
			speedOfProjectile = 2;
			HealthOfProjectile = 130;
			break;
		case "Slime":
			health = 7;
			damage = 5;
			numberOfProjectile = 4;
			speedOfProjectile = 5;
			HealthOfProjectile = 80;
			break;
		}
	}
	
	public void getEnemyImage() {
		try {
			orc1 = ImageIO.read(getClass().getResourceAsStream("/enemy/orc_down_1.png"));
			orc2 = ImageIO.read(getClass().getResourceAsStream("/enemy/orc_down_2.png"));
			skeleton1 = ImageIO.read(getClass().getResourceAsStream("/enemy/skeletonlord_phase2_down_1.png"));
			skeleton2 = ImageIO.read(getClass().getResourceAsStream("/enemy/skeletonlord_phase2_down_2.png"));
			slime1 = ImageIO.read(getClass().getResourceAsStream("/enemy/greenslime_down_1.png"));
			slime2 = ImageIO.read(getClass().getResourceAsStream("/enemy/greenslime_down_2.png"));
			
		}catch(IOException e){
			e.printStackTrace();
		}
	}
	//test
	
	public void update(String mode) {
		
		if (health <= 0) {
			enemyVisible = false;
			System.out.println("Enemy died!");
		} else {
		switch (mode.toLowerCase()) {
		case "defence":
    		DefenceMode();
			break;
		case "attack" :
			//cant run
			AttackMode();
			break;
		default:
			throw new IllegalArgumentException("Unexpected value: " + mode);
		}
		}
	}
	
	public void setHealth() {
		Random rn = new Random();
		int answer = rn.nextInt(6) + 1;
		health = health - answer;
		System.out.println("Enemy hit, health: " + health);
	}
	
	private void AttackMode() {
		if(enemyVisible == true)
		{
			
		spriteCounter++;
		if(spriteCounter > 10){
			if (spriteNum == 1) {
				spriteNum = 2;
			}
			else if (spriteNum == 2) {
				spriteNum = 1;
			}
			spriteCounter = 0;
		}
		}
	}

	private void DefenceMode() {
		if(enemyVisible == true) {
		if (gp.projectileList.size() < numberOfProjectile) {
		Projectile p = new Projectile(gp.tileSize, damage, speedOfProjectile, HealthOfProjectile);
        gp.projectileList.add(p);
			}
		
		spriteCounter++;
		if(spriteCounter > 10){
			if (spriteNum == 1) {
				spriteNum = 2;
			}
			else if (spriteNum == 2) {
				spriteNum = 1;
			}
			spriteCounter = 0;
		}		
	}
	}
	
	public void draw(Graphics2D g2) {
		
		if(enemyVisible == true)
		{

		BufferedImage image = null;
		
		switch (enemyName) {
		case "Orc":
			if(spriteNum == 1) {
				image = orc1;
			}
			if (spriteNum == 2) {
				image = orc2;
			}
			break;
		case "Skeleton":
			if(spriteNum == 1) {
				image = skeleton1;
			}
			if (spriteNum == 2) {
				image = skeleton2;
			}
			break;
		case "Slime":
			if(spriteNum == 1) {
				image = slime1;
			}
			if (spriteNum == 2) {
				image = slime2;
			}
			break;
		}
		g2.drawImage(image, x, y, gp.tileSize, gp.tileSize, null);
		}

	}
}
