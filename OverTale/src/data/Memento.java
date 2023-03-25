package data;

import java.io.Serializable;
import java.util.ArrayList;

import main.GamePanel;

public class Memento implements Serializable {
	
	//Private fields.
	GamePanel gp;
	
	//Player
	int playerHealth;
	String playerModeString;
	
	//Enemy
	int enemyHealth;
	String enemyModeString;
	String enemyName;
	
	//Round counter
	int surviveCounter;
	
	//Player items
	ArrayList <String> healNameList = new ArrayList<>();
	ArrayList <Integer> healAmountList = new ArrayList<>();
	
	public Memento(GamePanel gp)
	{
		//Player
		this.playerHealth = gp.GetPlayerHealth();
		this.playerModeString = gp.GetPlayerMode();
		
		//Enemy
		this.enemyHealth = gp.GetEnemyHealth();
		this.enemyModeString = gp.GetEnemyMode();
		this.enemyName = gp.GetEnemyType();
		
		//Round counter
		this.surviveCounter = gp.GetCurrentRound();
		
		//Player items
		for (int i = 0; i < gp.GetHealItemList().size(); i++)
		{
			this.healNameList.add(gp.GetHealItemList().get(i).itemName);;
			this.healAmountList.add(gp.GetHealItemList().get(i).healAmount);
		}
	}
	
	//Getters
	public int getPlayerHealth() {
	        return playerHealth;
	    }

	public String getPlayerModeString() {
	      	return playerModeString;
	    }

	    public int getEnemyHealth() {
	        return enemyHealth;
	    }

	    public String getEnemyModeString() {
	        return enemyModeString;
	    }

	    public String getEnemyName() {
	        return enemyName;
	    }

	    public int getSurviveCounter() {
	        return surviveCounter;
	    }

	    public ArrayList<String> getHealNameList() {
	        return healNameList;
	    }

	    public ArrayList<Integer> getHealAmountList() {
	        return healAmountList;
	    }
}
