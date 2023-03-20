package entity;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public class Entity {

	public int x, y;
	public int speed;
	
	public int health;
	public int damage;
	public boolean hit = false;
	//test
	public BufferedImage orc1, orc2, skeleton1, skeleton2, slime1, slime2;
	public BufferedImage up1, up2, down1, down2, left1, left2, right1, right2;
	public String direction;
	public String enemyName;

	public boolean visible = false;
	public boolean enemyVisible = false;
	
	public int spriteCounter = 0;
	public int spriteNum = 1;
	
	public Rectangle hitBoxTile;
	public Rectangle hitBox;
	public boolean collisionOn = false;
}
