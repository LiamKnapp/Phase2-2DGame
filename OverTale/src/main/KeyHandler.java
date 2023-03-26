package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.concurrent.TimeUnit;

import data.Memento;
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
		 
		 
		 //LOAD SAVE MENU STATE
		 if(gp.gameState == gp.loadState)
		 {
			 switch (gp.careTaker.GetNumberOfMementos())
			 {
			 case 0:
				 gp.ui.commandNum = 0;
				 if (key == KeyEvent.VK_ENTER) {
					 gp.ui.commandNum = -1;
					 //gp.turnSwitch = true;
			    	 gp.gameState = gp.titleState;
				 }
				 break;
			 case 1:
				 if (key == KeyEvent.VK_UP) {
					 gp.ui.commandNum++;
				     if (gp.ui.commandNum > 1)
				    	 gp.ui.commandNum = 0;
				 }
	
				 if (key == KeyEvent.VK_DOWN) {
				     gp.ui.commandNum--;
				     if (gp.ui.commandNum < 0)
				        gp.ui.commandNum = 1;
				 }
				 
				 if (key == KeyEvent.VK_ENTER) {
					 switch(gp.ui.commandNum)
				     {
				     case 0:
						 gp.ui.commandNum = -1;
					     gp.gameState = gp.titleState;
				    	 break;
				     case 1:
				    	 gp.setMemento(gp.careTaker.getMemento(0));
				    	 gp.gameState = gp.playState;
				    	 break;
				     }
				 }
				 break;
			 case 2:
				 if (key == KeyEvent.VK_UP) {
					 gp.ui.commandNum++;
				     if (gp.ui.commandNum > 2)
				    	 gp.ui.commandNum = 0;
				 }
	
				 if (key == KeyEvent.VK_DOWN) {
				     gp.ui.commandNum--;
				     if (gp.ui.commandNum < 0)
				        gp.ui.commandNum = 2;
				 }
				 
				 if (key == KeyEvent.VK_ENTER) {
					 switch(gp.ui.commandNum)
				     {
				     case 0:
						 gp.ui.commandNum = -1;
					     gp.gameState = gp.titleState;
				    	 break;
				     case 1:
				    	 gp.setMemento(gp.careTaker.getMemento(0));
				    	 gp.gameState = gp.playState;
				    	 break;
				     case 2:
				    	 gp.setMemento(gp.careTaker.getMemento(1));
				    	 gp.gameState = gp.playState;
				    	 break;
				     }
				 }
				 break;
			 case 3:
				 if (key == KeyEvent.VK_UP) {
					 gp.ui.commandNum++;
				     if (gp.ui.commandNum > 3)
				    	 gp.ui.commandNum = 0;
				 }
	
				 if (key == KeyEvent.VK_DOWN) {
				     gp.ui.commandNum--;
				     if (gp.ui.commandNum < 0)
				        gp.ui.commandNum = 3;
				 }
				 
				 if (key == KeyEvent.VK_ENTER) {
					 switch(gp.ui.commandNum)
				     {
				     case 0:
						 gp.ui.commandNum = -1;
					     gp.gameState = gp.titleState;
				    	 break;
				     case 1:
				    	 gp.setMemento(gp.careTaker.getMemento(0));
				    	 gp.gameState = gp.playState;
				    	 break;
				     case 2:
				    	 gp.setMemento(gp.careTaker.getMemento(1));
				    	 gp.gameState = gp.playState;
				    	 break;
				     case 3:
				    	 gp.setMemento(gp.careTaker.getMemento(2));
				    	 gp.gameState = gp.playState;
				    	 break;
				     }
				 }
				 break;
			 }
		 }
		
		 //TITLE STATE
		 if(gp.gameState == gp.titleState)
		 {
		    if (key == KeyEvent.VK_UP) {
		        gp.ui.commandNum++;
		        if (gp.ui.commandNum > 1)
		        	gp.ui.commandNum = 0;
		    }

		    if (key == KeyEvent.VK_DOWN) {
		        gp.ui.commandNum--;
		        if (gp.ui.commandNum < 0)
		        	gp.ui.commandNum = 1;
		    }
		    if (key == KeyEvent.VK_ENTER) {
		    	switch(gp.ui.commandNum)
		    	{
		    	case 0:
					gp.ui.commandNum = 1;
		    		gp.gameState = gp.playState;
		    		break;
		    	case 1:
		    		gp.ui.commandNum = 0;
		    		gp.gameState = gp.loadState;
		    		break;
		    	default:
		    		break;
		    	}
		    }
		 }
		 //test
		 //DEATH STATE
		 if (gp.gameState == gp.deathState)
		 {
		    if (key == KeyEvent.VK_UP) {
		        gp.ui.commandNum++;
		        if (gp.ui.commandNum > 1)
		        	gp.ui.commandNum = 0;
		    }

		    if (key == KeyEvent.VK_DOWN) {
		        gp.ui.commandNum--;
		        if (gp.ui.commandNum < 0)
		        	gp.ui.commandNum = 1;
		    }
		    if (key == KeyEvent.VK_ENTER) {
		    	switch(gp.ui.commandNum)
		    	{
		    	case 0:
					 gp.ui.commandNum = 1;
		    		gp.resetGame();
		    		gp.gameState = gp.playState;
		    		break;
		    	case 1:
		    		gp.ui.commandNum = 0;
		    		gp.gameState = gp.loadState;
		    		break;
		    	default:
		    		break;
		    	}
		    }
		 }
		 
		 //PLAY STATE
		 if (gp.gameState == gp.playState)
		 {
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
		    	
			    if (key == KeyEvent.VK_RIGHT || key == KeyEvent.VK_UP) {
			        gp.ui.commandNum++;
			        if (gp.ui.commandNum > 1)
			        	gp.ui.commandNum = 0;
			    }
	
			    if (key == KeyEvent.VK_LEFT || key == KeyEvent.VK_DOWN) {
			        gp.ui.commandNum--;
			        if (gp.ui.commandNum < 0)
			        	gp.ui.commandNum = 1;
			    }
			    
		    	//if is the attack turn
		    	if (key == KeyEvent.VK_ENTER) {
			    	switch(gp.ui.commandNum)
			    	{
			    	case 0:
			    		attackEnemy = true;
			    		break;
			    	case 1:
			    		useItem = true;
			    		break;
			    	default:
			    		break;
			    	}
			    }
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
	    
    	if (key == KeyEvent.VK_ENTER) {
    		if(gp.ui.commandNum == 0)
    			attackEnemy = false;
    		else
    			useItem = false;
    	}
	}
}