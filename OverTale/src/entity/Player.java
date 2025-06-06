package entity;

import java.awt.Color;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.nio.file.StandardWatchEventKinds;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import main.GamePlay2D;
import main.KeyHandler;
import objects.HealItem;

public class Player extends Entity{
//test
	GamePlay2D gp;
	KeyHandler keyH;
	String modeString;
	ArrayList <HealItem> healItemList = new ArrayList<>();
	int itemIndex;

	public Player(GamePlay2D gp, KeyHandler keyH) {
		
		this.gp = gp;
		this.keyH = keyH;
		this.modeString = "Attack";
		
		hitBoxTile = new Rectangle();
		//make the hit box slightly smaller then the tile size so it fits player model
		hitBoxTile.x = 8;
		hitBoxTile.y = 16;
		hitBoxTile.height = 32;
		hitBoxTile.width = 32;
		
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
		gp.attackMode = true;
		
		if (keyH.attackEnemy == true) { // attack the enemy
			gp.enemytakeDMG = true;
		} 
		
		if (keyH.useItem == true) { // use items
			//display list of items that they can use
			gp.playerHeal = true;
		} 
		
	}
	public void DefenceMode()
	{
		if(visible == true) {
		
			hit = false;
			gp.cChecker.checkProjectile(this);
			setHealth();
		
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
				hitBox.x = x + 8;
				hitBox.y = y + 16;
			}
			
			
		}
	}
	public void setDefaultValues() {
		x = gp.tileSize * 7;
		y = gp.tileSize * 8;
		speed = 4;
		health = 12;
		maxHealth = health;
		healItems = 4;
		itemIndex = healItems;
		
		//fill the item list with healing items
		for (int i = 0; i <= healItems; i++) {
		HealItem hpPotion = new HealItem(4, "Small Hp Potion");
		healItemList.add(hpPotion);
		}
		
		direction = "down";
		
		hitBox.x = x + 8;
		hitBox.y = y + 16;
	}
	
	public void setHealth() {
		if (hit == true) {
		health = health - damage;
		System.out.println("Player Hit, health: " + health);
		}
		
		if (keyH.useItem == true) {
			if (healItemList.isEmpty() == false) {
			health = health + healItemList.get(itemIndex).healAmount;
			System.out.println("Player Heal, Item: " + healItemList.get(itemIndex).itemName + ", Heal Amount: " + healItemList.get(itemIndex).healAmount);
			healItemList.remove(itemIndex);
			itemIndex = itemIndex - 1;
			if (health > maxHealth) {
				health = maxHealth;
			}
			System.out.println("Player Total Health: " + health);
			}else {
			System.out.println("No Items left! WATCH OUT!!" + health);
			}
			keyH.useItem = false;
		}
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
		if (health <= 0) 
		{	// if the player died
			visible = false;
			System.out.println("You died!");
		} 
		else 
		{ 	// if the player is still alive
			switch (mode.toLowerCase()) 
			{
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
		
		//hit box for tile mapping
//		g2.setColor(Color.BLACK);
//		g2.fillRect(hitBoxTile.x, hitBoxTile.y, hitBoxTile.width, hitBoxTile.height);
		
		//hit box for projectiles
		//g2.setColor(Color.RED);
		//g2.fillRect(hitBox.x, hitBox.y, hitBox.width, hitBox.height);  
		
		}
	}
	
	public ArrayList<HealItem> GetPlayerHealItems() {
		return this.healItemList;
	}
	public void SetModeString(String playerModeString) {
		this.modeString = playerModeString;
	}
	public void setHealItemList(ArrayList<String> healNameList, ArrayList<Integer> healAmountList) {
		
		HealItem healItem;
		
		for (int i = 0; i < this.healItemList.size(); i++)
		{
			healItem = new HealItem(healAmountList.get(i), healNameList.get(i));
			this.healItemList.set(i, healItem);
		}
	}
}
