package entity;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import main.GamePanel;
import main.KeyHandler;

public class Player extends Entity{
//test
	GamePanel gp;
	KeyHandler keyH;
	String modeString;
	public Player(GamePanel gp, KeyHandler keyH) {
		
		this.gp = gp;
		this.keyH = keyH;
		this.modeString = "Defence";
		hitBox = new Rectangle();
		//make the hit box slightly smaller then the tile size so it fits player model
		hitBox.x = 8;
		hitBox.y = 16;
		hitBox.height = 32;
		hitBox.width = 32;
		
		setDefaultValues();
		getPlayerImage();
		
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
		}else {this.modeString = "Defence"; visible = true;}
		System.out.println("Switch player mode to " + this.modeString);
	}
	public void AttackMode()
	{
		if(visible == true) {
		speed = 0;
		if(keyH.upPressed == true || keyH.downPressed == true || keyH.leftPressed == true || keyH.rightPressed == true) {
			
			
			collisionOn = false;
			gp.cChecker.checkTile(this);
			
			
			if (keyH.upPressed == true || keyH.downPressed == true || keyH.leftPressed == true
					|| keyH.rightPressed == true) {

				collisionOn = false;
				gp.cChecker.checkTile(this);

				if (keyH.upPressed == true) {
					direction = "up";
					if (collisionOn == false) {
						y -= speed;
					}
				}
				if (keyH.downPressed == true) {
					direction = "down";
					if (collisionOn == false) {
						y += speed;
					}
				}
				if (keyH.leftPressed == true) {
					direction = "left";
					if (collisionOn == false) {
						x -= speed;
					}
				}
				if (keyH.rightPressed == true) {
					direction = "right";
					if (collisionOn == false) {
						x += speed;
					}
				}
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
	}
	public void DefenceMode()
	{
		if(visible == true) {
		speed = 4;
		if(keyH.upPressed == true || keyH.downPressed == true || keyH.leftPressed == true || keyH.rightPressed == true) {
			
			
			collisionOn = false;
			gp.cChecker.checkTile(this);
			
			
			if (keyH.upPressed == true || keyH.downPressed == true || keyH.leftPressed == true
					|| keyH.rightPressed == true) {

				collisionOn = false;
				gp.cChecker.checkTile(this);

				if (keyH.upPressed == true) {
					direction = "up";
					if (collisionOn == false) {
						y -= speed;
					}
				}
				if (keyH.downPressed == true) {
					direction = "down";
					if (collisionOn == false) {
						y += speed;
					}
				}
				if (keyH.leftPressed == true) {
					direction = "left";
					if (collisionOn == false) {
						x -= speed;
					}
				}
				if (keyH.rightPressed == true) {
					direction = "right";
					if (collisionOn == false) {
						x += speed;
					}
				}
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
	}
	public void setDefaultValues() {
		x = gp.tileSize * 7;
		y = gp.tileSize * 8;
		speed = 4;
		direction = "down";
	}
	
	public void getPlayerImage() {
		
		try {
			
			up1 = ImageIO.read(getClass().getResourceAsStream("/player/boy_up_1.png"));
			up2 = ImageIO.read(getClass().getResourceAsStream("/player/boy_up_2.png"));
			down1 = ImageIO.read(getClass().getResourceAsStream("/player/boy_down_1.png"));
			down2 = ImageIO.read(getClass().getResourceAsStream("/player/boy_down_2.png"));
			left1 = ImageIO.read(getClass().getResourceAsStream("/player/boy_left_1.png"));
			left2 = ImageIO.read(getClass().getResourceAsStream("/player/boy_left_2.png"));
			right1 = ImageIO.read(getClass().getResourceAsStream("/player/boy_right_1.png"));
			right2 = ImageIO.read(getClass().getResourceAsStream("/player/boy_right_2.png"));
			
			
		}catch(IOException e){
			e.printStackTrace();
		}
	}
	
	public void update(String mode) {

		switch (mode.toLowerCase()) {
		case "defence":
			visible = true;
    		DefenceMode();
			break;
		case "attack" :
			visible = false;
			// Cant run
			AttackMode();
			break;
		default:
			throw new IllegalArgumentException("Unexpected value: " + mode);
		}
	}
	
	public void draw(Graphics2D g2) {
		
		if(visible == true)
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
		g2.drawImage(image, x, y, gp.tileSize, gp.tileSize, null);
	}
	}
}
