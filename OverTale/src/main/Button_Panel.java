package main;

import java.awt.Color;

import javax.swing.JPanel;

public class Button_Panel extends GamePanel{
	
	GamePanel gamePanel;
	private static final int RADIUS = 100;
	private static final int GAP = 20;
	private static final int SLICES = 10;
	private static final int SIDE = 4;
	
	public  Button_Panel(GamePanel gamePane2) {
		this.gamePanel = gamePane2;
		
	}
	public void createSmallButton()
	{
		for (int i = 0; i < SLICES; i++) {
	        double phi = (i * Math.PI * 2) / SLICES; 
	        JPanel smallPanel = new JPanel();
	        smallPanel.setBackground(Color.red);
	        int x = (int) (RADIUS * Math.sin(phi) + RADIUS - SIDE / 2) + GAP;
	        int y = (int) (RADIUS * Math.cos(phi) + RADIUS - SIDE / 2) + GAP;
	        smallPanel.setBounds(x, y, SIDE, SIDE);
	        smallPanel.setVisible(true);
	        gamePanel.add(smallPanel);
	     }
	}
}
