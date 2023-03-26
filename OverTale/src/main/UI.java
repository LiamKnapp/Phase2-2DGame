package main;

import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics2D;
import java.awt.Color;
import java.io.File;
import java.io.IOException;
import java.awt.GraphicsEnvironment;

public class UI {
	GamePanel gp;
	Font roundUI;
	Font healthUI;
	Font enemyHealthUI;
	Font currentTurnUI;
	Font potionsUI;
	
	Font deathUI;
	
	Font customFont;
	
	public int commandNum = 0;
	
	public UI(GamePanel gp) {
		this.gp = gp;
		
        try 
        {
            //File fontFile = new File("/fonts/kongtext.ttf");
			customFont = Font.createFont(Font.TRUETYPE_FONT, getClass().getResourceAsStream("/fonts/kongtext.ttf"));
			
			GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge.registerFont(customFont);
            
    		roundUI = new Font(customFont.getName(), Font.BOLD, 17);
    		healthUI = new Font(customFont.getName(), Font.BOLD, 17);
    		enemyHealthUI = new Font(customFont.getName(), Font.BOLD, 22);
    		
    		currentTurnUI = new Font(customFont.getName(), Font.BOLD, 22);
    		
    		deathUI = new Font(customFont.getName(), Font.BOLD, 60);
    		
    		potionsUI = new Font(customFont.getName(), Font.BOLD, 17);
    		
		} 
        catch (FontFormatException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void draw(Graphics2D g2) {
		
		if (gp.gameState == gp.playState)
		{
			drawPlayState(g2);
		}
		if (gp.gameState == gp.deathState)
		{
			drawDeathScreen(g2);
		}
		if (gp.gameState == gp.titleState)
		{
			drawTitleScreen(g2);
		}
		if (gp.gameState == gp.loadState)
		{
			drawLoadState(g2);
		}
	}

	private void drawLoadState(Graphics2D g2) {
		int y = gp.tileSize*3;
		
		String text = "LOAD SAVE";
		
		g2.setFont(new Font(customFont.getName(), Font.BOLD, 60));
		
		//Shadow
		g2.setColor(Color.gray);
		
		g2.drawString(text, getXforCenteredText(text, g2)+3, y+3);
		
		//Death line
		g2.setColor(Color.white);
		
		g2.drawString(text, getXforCenteredText(text, g2), y);
		
		//OPTIONS
		g2.setFont(new Font(customFont.getName(), Font.PLAIN, 25));
		int x;
		y += gp.tileSize*2;
		
		text = "BACK TO MENU";
		x = getXforCenteredText(text, g2);
		g2.drawString(text, x, y += gp.tileSize);
		
		for (int i = 0; i < gp.careTaker.GetNumberOfMementos(); i++)
		{
			text = "SAVE " + (i + 1);
			x = getXforCenteredText(text, g2);
			g2.drawString(text, x, y += gp.tileSize);
		}
		
		text = "BACK TO MENU";
		x = getXforCenteredText(text, g2);
		y = gp.tileSize*6;
		switch (commandNum)
		{
		case 3:
			g2.drawString(">", x - gp.tileSize, y + gp.tileSize*3);
			break;
		case 2:
			g2.drawString(">", x - gp.tileSize, y + gp.tileSize*2);
			break;
		case 1:
			g2.drawString(">", x - gp.tileSize, y + gp.tileSize);
			break;
		case 0:
			g2.drawString(">", x - gp.tileSize, y);
			break;
		default:
			break;
		}
	}

	private void drawDeathScreen(Graphics2D g2) {
		int y = gp.tileSize*3;
		
		String text = "DEATH";
		
		g2.setFont(new Font(customFont.getName(), Font.BOLD, 60));
		
		//Shadow
		g2.setColor(Color.gray);
		
		g2.drawString(text, getXforCenteredText(text, g2)+3, y+3);
		
		//Death line
		g2.setColor(Color.red);
		
		g2.drawString(text, getXforCenteredText(text, g2), y);
		
		//OPTIONS
		g2.setFont(new Font(customFont.getName(), Font.PLAIN, 25));
		g2.setColor(Color.white);
		
		text = "NEW GAME";
		int x = getXforCenteredText(text, g2);
		
		g2.drawString(text, x, y += gp.tileSize*3);
		if (commandNum == 0)
		{
			g2.drawString(">", x - gp.tileSize, y);
		}
		
		text = "LOAD GAME";

		g2.drawString(text, x, y += gp.tileSize);
		if (commandNum == 1)
		{
			g2.drawString(">", x - gp.tileSize, y);
		}
	}
	
	
	private void drawTitleScreen(Graphics2D g2) 
	{
		int y = gp.tileSize*3;
		g2.setColor(new Color(0, 0, 0));
		g2.fillRect(0, 0, gp.screenWidth, gp.screenHeight);
		
		String text = "Try to beat that";
		String textCont = "slime lol";
		
		g2.setFont(new Font(customFont.getName(), Font.BOLD, 35));
		
		//Shadow
		g2.setColor(Color.gray);
		g2.drawString(text, getXforCenteredText(text, g2)+3, y +3);
		g2.drawString(textCont, getXforCenteredText(textCont, g2)+3, y + gp.tileSize +3);
		
		
		//TITLE
		g2.setColor(Color.white);
		g2.drawString(text, getXforCenteredText(text, g2), y);
		g2.drawString(textCont, getXforCenteredText(textCont, g2), y += gp.tileSize);
		
		
		//OPTIONS
		g2.setFont(new Font(customFont.getName(), Font.PLAIN, 25));
		
		text = "NEW GAME";
		int x = getXforCenteredText(text, g2);
		
		g2.drawString(text, x, y += gp.tileSize*3);
		if (commandNum == 0)
		{
			g2.drawString(">", x - gp.tileSize, y);
		}
		
		text = "LOAD GAME";

		g2.drawString(text, x, y += gp.tileSize);
		if (commandNum == 1)
		{
			g2.drawString(">", x - gp.tileSize, y);
		}
	}

	//Accepts text as an argument and returns 
	private int getXforCenteredText (String text, Graphics2D g2) 
	{
		int length = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
		int x  = gp.screenWidth/2 - length/2;
		return x;
	}
	
	private void drawPlayState(Graphics2D g2)
	{
		gp.tileM.draw(g2);
		gp.enemy.draw(g2);
		gp.player.draw(g2);
		
		//for every projectile in the list draw it
		for (int i = 0; i < gp.projectileList.size(); i++) {
			if (gp.projectileList.get(i) != null) {
				if (gp.projectileList.get(i).health > 0) {
					gp.projectileList.get(i).draw(g2);
				}
			}
		}
		
		//Shadows
		g2.setFont(roundUI);
		g2.setColor(Color.black);
		g2.drawString("Round = " + gp.surviveCounter, 38, 52);
		
		g2.setFont(healthUI);
		g2.drawString("health = " + gp.player.health, 38, 92);
		
		g2.setFont(potionsUI);
		g2.drawString("Potions = " + gp.player.GetPlayerHealItems().size(), 38, 132);
		
		g2.setFont(enemyHealthUI);
		g2.drawString("BOSS: " + gp.enemy.health, 293, 227);
		
		//Lines
		g2.setFont(roundUI);
		g2.setColor(Color.orange);
		g2.drawString("Round = " + gp.surviveCounter, 35, 50);
		
		g2.setFont(healthUI);
		g2.setColor(Color.blue);
		g2.drawString("health = " + gp.player.health, 35, 90);
		
		g2.setFont(potionsUI);
		g2.setColor(Color.green);
		g2.drawString("Potions = " + gp.player.GetPlayerHealItems().size(), 35, 130);
		
		g2.setFont(enemyHealthUI);
		g2.setColor(Color.red);
		g2.drawString("BOSS: " + gp.enemy.health, 290, 225);
		
		if (gp.player.GetMode() == "Defence")
		{
			g2.setFont(currentTurnUI);
			g2.setColor(Color.black);
			g2.drawString("ENEMY TURN", 280, 40);
		}
		else
		{
			//Shadow 
			g2.setFont(currentTurnUI);
			g2.setColor(Color.black);
			
			g2.drawString("YOUR TURN", 293, 42);
			
			//Line
			g2.setColor(Color.white);
			g2.drawString("YOUR TURN", 290, 40);
			

			int x = gp.screenWidth/2;
			int y = gp.screenHeight/2 + gp.tileSize;
			
			g2.setFont(new Font(customFont.getName(), Font.PLAIN, 25));
			
			if (commandNum == 1)
			{
				//BOX SHADOW
				g2.setColor(Color.gray);
				g2.fillRect(x + gp.tileSize+3, y+3, 200, 100);
				
				//OPTION BOXES
				g2.setColor(new Color(215, 199, 150));
				
				g2.fillRect(x + gp.tileSize*1, y, 200, 100);
				
				g2.setColor(new Color(255, 229, 180));
				g2.fillRect(x - gp.tileSize*5-3, y, 200, 100);
				
				//LINE SHADOW
				y = y + gp.tileSize + 10;
				String text = "HEAL";
				x = getXforCenteredText(text, g2) + gp.tileSize;
				g2.setColor(Color.gray);
				g2.drawString(text, x + gp.tileSize*2+3, y+3);
				
				//LINE
				g2.setColor(Color.black);
				g2.drawString(text, x + gp.tileSize*2, y);
				
				text = "ATTACK";
				x = getXforCenteredText(text, g2) + gp.tileSize;
				g2.drawString(text, x - gp.tileSize*4, y);
			}
			
			if (commandNum == 0)
			{
				//BOX SHADOW 
				g2.setColor(Color.gray);
				g2.fillRect(x - gp.tileSize*5, y+3, 200, 100);
				
				//OPTION BOXES
				g2.setColor(new Color(215, 199, 150));
				g2.fillRect(x - gp.tileSize*5-3, y, 200, 100);
				
				g2.setColor(new Color(255, 229, 180));
				g2.fillRect(x + gp.tileSize*1, y, 200, 100);
				
				//LINE SHADOW
				y = y+ gp.tileSize+10;
				String text = "ATTACK";
				x = getXforCenteredText(text, g2) + gp.tileSize;
				
				g2.setColor(Color.gray);
				g2.drawString(text, x - gp.tileSize*4+3, y+3);
				
				//LINE
				g2.setColor(Color.black);
				g2.drawString(text, x - gp.tileSize*4, y);
				
				text = "HEAL";
				x = getXforCenteredText(text, g2) + gp.tileSize;
				g2.drawString(text, x + gp.tileSize*2, y);
			}
		}
	}
}
