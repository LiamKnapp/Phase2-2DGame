package objects;

import java.io.IOException;

import javax.imageio.ImageIO;

public class HealItem{

	public int healAmount;
	public String itemName;
	
	public HealItem(int HealAmount, String ItemName) {
		healAmount = HealAmount;
		itemName = ItemName;
		
		//getHealItemImage();
	}
	
	
//	public void getHealItemImage() {
//		try {
//			
//			
//		}catch(IOException e){
//			e.printStackTrace();
//		}
//	}
}
