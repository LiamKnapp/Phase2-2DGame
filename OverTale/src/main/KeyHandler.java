package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import entity.Projectile;

public class KeyHandler implements KeyListener{

	public boolean upPressed, downPressed, leftPressed, rightPressed, shotPressed, attackEnemy, useItem;
	//test
	
	GamePanel gp;
	
	public KeyHandler(GamePanel gp) {
		this.gp = gp;
	}
	
	@Override
	public void keyTyped(KeyEvent e) {	
	}

	@Override
	public void keyPressed(KeyEvent e) {
		 int key = e.getKeyCode();

		    if (key == KeyEvent.VK_LEFT) {
		    	leftPressed = true;
		    }

		    if (key == KeyEvent.VK_RIGHT) {
		        rightPressed = true;
		    }

		    if (key == KeyEvent.VK_UP) {
		        upPressed = true;
		    }

		    if (key == KeyEvent.VK_DOWN) {
		        downPressed = true;
		    }
//		    if (key == KeyEvent.VK_F) {
//		        shotPressed = true;
//		        Projectile p = new Projectile(gp.tileSize, 2);
//		        
//		        gp.projectileList.add(p);
//		    }
		    
		    if (gp.attackMode == true) {
		    //if is the attack turn
		    if (key == KeyEvent.VK_ENTER) {
		        attackEnemy = true;
		    }
		    
		    //if is the attack turn
		    if (key == KeyEvent.VK_V) {
		        useItem = true;
		    }
		    }
	}
	
	@Override
	public void keyReleased(KeyEvent e) {
		int key = e.getKeyCode();

	    if (key == KeyEvent.VK_LEFT) {
	    	leftPressed = false;
	    }

	    if (key == KeyEvent.VK_RIGHT) {
	    	rightPressed = false;
	    }

	    if (key == KeyEvent.VK_UP) {
	    	upPressed = false;
	    }

	    if (key == KeyEvent.VK_DOWN) {
	    	downPressed = false;
	    }
//	    if (key == KeyEvent.VK_F) {
//	        shotPressed = false;
//	    }
	    
	    if (gp.attackMode == true) {
	    //if is the attack turn
	    if (key == KeyEvent.VK_ENTER) {
	        attackEnemy = false;
	    }
	    
	    //if is the attack turn
	    if (key == KeyEvent.VK_V) {
	        useItem = false;
	    }
	    }
		
	}

}
