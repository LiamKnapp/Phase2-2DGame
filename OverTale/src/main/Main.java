package main;
public class Main {
	public static void main (String[] args) {
		
	System.out.println("Game is Starting...");
	Windown mainProgramWindown = mainWindown.getMainWindown();
	GamePlayStory GamePlay = new GamePlay2D();
	mainProgramWindown.addPanelToWindown(GamePlay.getGamePlayPanel());
	mainProgramWindown.ProcessingPanelWindown();
	GamePlay.startGamePlay();
	}
}
