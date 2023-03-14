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

	
	public Enemy(GamePanel gp) {
		this.modeString = "Attack";
		this.gp = gp;
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
			grass = ImageIO.read(getClass().getResourceAsStream("/tiles/grass.png"));
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
		
		switch (mode.toLowerCase()) {
		case "defence":
    		DefenceMode();
			break;
		case "attack" :
			// Cant run
			AttackMode();
			break;
		default:
			throw new IllegalArgumentException("Unexpected value: " + mode);
		}
		
		
		spriteCounter++;
		if(spriteCounter > 20){
			if (spriteNum == 1) {
				spriteNum = 2;
			}
			else if (spriteNum == 2) {
				spriteNum = 1;
			}
			spriteCounter = 0;
		}
	}
	
	private void AttackMode() {
		// enermy can attack
		// TODO Auto-generated method stub
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

	private void DefenceMode() {
		// enemy cannot attack
		// TODO Auto-generated method stub
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
	public int RandomTurnTime()
	{
		int min_second_turn = 4;
		int max_second_turn = 5;
		
	    int random_second_per_turn = (int)Math.floor(Math.random() * (max_second_turn - min_second_turn + 1) + min_second_turn);
	    return random_second_per_turn;
	}
	
	public void draw(Graphics2D g2) {
		

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
