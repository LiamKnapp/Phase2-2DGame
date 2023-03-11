package main;

import entity.Entity;

public class CollisionChecker {

	GamePanel gp;
	
	public CollisionChecker(GamePanel gp) {
		this.gp = gp;
	}
	
	public void checkTile(Entity entity) {
		
		int entityLeftX = entity.x + entity.hitBox.x;
		int entityRightX = entity.x + entity.hitBox.x + entity.hitBox.width;
		int entityTopY = entity.y + entity.hitBox.y;
		int entityBottomY = entity.y + entity.hitBox.y + entity.hitBox.height;
		
		
		int entityLeftCol = entityLeftX/gp.tileSize;
		int entityRightCol = entityRightX/gp.tileSize;
		int entityTopRow = entityTopY/gp.tileSize;
		int entityBottomRow = entityBottomY/gp.tileSize;
		
		int tileNum1, tileNum2;
		
		
		switch(entity.direction) {
		
		case "up":
			entityTopRow = (entityTopY - entity.speed) /gp.tileSize;
			tileNum1 = gp.tileM.mapTileNum[entityLeftCol][entityTopRow];
			tileNum2 = gp.tileM.mapTileNum[entityRightCol][entityTopRow];
			
			if(gp.tileM.tile[tileNum1].Collision == true || gp.tileM.tile[tileNum2].Collision == true) {
				entity.collisionOn = true;
			}
			
			break;
		case "down":
			entityBottomRow = (entityBottomY + entity.speed) /gp.tileSize;
			tileNum1 = gp.tileM.mapTileNum[entityLeftCol][entityBottomRow];
			tileNum2 = gp.tileM.mapTileNum[entityRightCol][entityBottomRow];
			
			if(gp.tileM.tile[tileNum1].Collision == true || gp.tileM.tile[tileNum2].Collision == true) {
				entity.collisionOn = true;
			}
			
			break;
		case "left":
			entityLeftCol = (entityLeftX - entity.speed) /gp.tileSize;
			tileNum1 = gp.tileM.mapTileNum[entityLeftCol][entityTopRow];
			tileNum2 = gp.tileM.mapTileNum[entityLeftCol][entityBottomRow];
			
			if(gp.tileM.tile[tileNum1].Collision == true || gp.tileM.tile[tileNum2].Collision == true) {
				entity.collisionOn = true;
			}
			
			break;
		case "right":
			entityRightCol = (entityRightX + entity.speed) /gp.tileSize;
			tileNum1 = gp.tileM.mapTileNum[entityRightCol][entityTopRow];
			tileNum2 = gp.tileM.mapTileNum[entityRightCol][entityBottomRow];
			
			if(gp.tileM.tile[tileNum1].Collision == true || gp.tileM.tile[tileNum2].Collision == true) {
				entity.collisionOn = true;
			}
			
			break;
		
		}
	}
}
