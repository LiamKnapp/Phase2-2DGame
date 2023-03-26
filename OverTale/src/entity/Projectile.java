package entity;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;

import main.GamePlay2D;

public class Projectile extends Entity{

	int tileSize;
	
	public Projectile(int size, int dmg, int Speed, int Health) {
		health = Health;
		tileSize = size;
		damage = dmg;
		speed = Speed;
		
		hitBox = new Rectangle();
		//make the hit box slightly smaller then the tile size so it fits player model
		hitBox.x = 8;
		hitBox.y = 16;
		hitBox.height = 28;
		hitBox.width = 28;
		
		getProjectileImage();
		setCords();
	}
	
	public void getProjectileImage() {
		try {
			up1 = ImageIO.read(getClass().getResourceAsStream("/projectile/fireball_up_1.png"));
			up2 = ImageIO.read(getClass().getResourceAsStream("/projectile/fireball_up_2.png"));
			down1 = ImageIO.read(getClass().getResourceAsStream("/projectile/fireball_down_1.png"));
			down2 = ImageIO.read(getClass().getResourceAsStream("/projectile/fireball_down_2.png"));
			left1 = ImageIO.read(getClass().getResourceAsStream("/projectile/fireball_left_1.png"));
			left2 = ImageIO.read(getClass().getResourceAsStream("/projectile/fireball_left_2.png"));
			right1 = ImageIO.read(getClass().getResourceAsStream("/projectile/fireball_right_1.png"));
			right2 = ImageIO.read(getClass().getResourceAsStream("/projectile/fireball_right_2.png"));
			
			
		}catch(IOException e){
			e.printStackTrace();
		}
	}
	
	public void setCords() {
		Random rn = new Random();
		int answerY = 6 + rn.nextInt(9 - 6 + 1);
		int answerX = 4 + rn.nextInt(11 - 4 + 1);
		int answerDirection = rn.nextInt(4) + 1;
		
		switch (answerDirection) {
		case 1:
			direction = "up";
			y = tileSize * 12;
			x = tileSize * answerX;
			break;
		case 2:
			direction = "down";
			y = tileSize * 4;
			x = tileSize * answerX;
			break;
		case 3:
			direction = "left";
			x = tileSize * 14;
			y = tileSize * answerY;
			break;
		case 4:
			direction = "right";
			x = tileSize * 2;	
			y = tileSize * answerY;
			break;
		}
		
		hitBox.x = x;
		hitBox.y = y;
	}
		
	
	public void update(){
		if(health >= 0)
		{
		BufferedImage image = null;
		
		switch (direction) {
		case "up":
			if(spriteNum == 1) {
				image = up1;
			}
			if (spriteNum == 2) {
				image = up2;
			}
			y -= speed;
			break;
		case "down":
			if(spriteNum == 1) {
				image = down1;
			}
			if (spriteNum == 2) {
				image = down2;
			}
			y += speed;
			break;
		case "left":
			if(spriteNum == 1) {
				image = left1;
			}
			if (spriteNum == 2) {
				image = left2;
			}
			x -= speed;
			break;
		case "right":
			if(spriteNum == 1) {
				image = right1;
			}
			if (spriteNum == 2) {
				image = right2;
			}
			x += speed;
			break;
		}
		hitBox.x = x;
		hitBox.y = y;
		
		health--;
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
	
	public void draw(Graphics2D g2) {
		if(health >= 0)
		{
		BufferedImage image = null;
		
		switch (direction) {
		case "up":
			if(spriteNum == 1) {
				image = up1;
			}
			if (spriteNum == 2) {
				image = up2;
			}
			break;
		case "down":
			if(spriteNum == 1) {
				image = down1;
			}
			if (spriteNum == 2) {
				image = down2;
			}
			break;
		case "left":
			if(spriteNum == 1) {
				image = left1;
			}
			if (spriteNum == 2) {
				image = left2;
			}
			break;
		case "right":
			if(spriteNum == 1) {
				image = right1;
			}
			if (spriteNum == 2) {
				image = right2;
			}
			break;
		}
		g2.drawImage(image, x, y, tileSize, tileSize, null);
		
		//hit box for projectiles
		//g2.setColor(Color.BLUE);
		//g2.fillRect(hitBox.x, hitBox.y, hitBox.width, hitBox.height);  
		}
	}
}
