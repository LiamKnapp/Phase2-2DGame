package main;

import javax.swing.JFrame;

public class mainWindown implements Windown{
	private static volatile mainWindown public_Windown;
	private JFrame jframe;
	private  mainWindown() {
		this.jframe = new JFrame();
		this.jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.jframe.setResizable(false);
		this.jframe.setTitle("OverTale");
	}
	
	public static mainWindown getMainWindown() {
		if (public_Windown == null)
		{
			synchronized (mainWindown.class) {
				if (public_Windown == null)
				{
					public_Windown = new mainWindown();
				}
			}
		}
		return public_Windown;
	}

	@Override
	public void setUpMainWindown() {
		mainWindown.getMainWindown();
		this.jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.jframe.setResizable(false);
		this.jframe.setTitle("OverTale");
	}

	@Override
	public void ProcessingPanelWindown() {
		this.jframe.pack();
		this.jframe.setLocationRelativeTo(null);
		this.jframe.setVisible(true);
		
	}

	@Override
	public void addPanelToWindown(GamePlay2D gamePanel) {
		this.jframe.add(gamePanel);	
	}

}
