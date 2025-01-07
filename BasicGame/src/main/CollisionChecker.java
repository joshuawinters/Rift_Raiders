package main;

import entity.Entity;

public class CollisionChecker {

    RiftRaiders game;

    public CollisionChecker(RiftRaiders game) {
        this.game = game;
    }

    public void CheckTile(Entity entity){

        // let op entity.solidwidth is nodig hier niet entity.width
        int entityLeftx = entity.x + entity.solidArea.x;
        int entityRightx = entity.x + entity.solidArea.x + entity.solidArea.width;
        int entityTopy = entity.y + entity.solidArea.y;
        int entityBottomy = entity.y + entity.solidArea.y + entity.solidArea.height;

        int entityLeftCol = entity.x/game.tileWidth;
        int entityRightCol = entity.x/game.tileWidth;
        int entityTopRow = entity.y/game.tileHeight;
        int entityBottomyRow = entity.y/game.tileHeight;

        int tileNum1, tileNum2;
        switch (entity.direction){
            case "Up":
                entityTopRow = (entityTopy - entity.speed)/game.tileWidth;
                tileNum1 = game.tileM.mapTileNumber[entityLeftCol][entityTopRow];
                tileNum2 = game.tileM.mapTileNumber[entityRightCol][entityTopRow];
                if (game.tileM.tile[tileNum1].collision == true || game.tileM.tile[tileNum2].collision == true) {
                    entity.collisionOn = true;
                    System.out.println("collision above");
                }
                break;
            case "Down":
                entityBottomyRow = (entityBottomy + entity.speed)/game.tileWidth;
                tileNum1 = game.tileM.mapTileNumber[entityLeftCol][entityBottomyRow];
                tileNum2 = game.tileM.mapTileNumber[entityRightCol][entityBottomyRow];
                if (game.tileM.tile[tileNum1].collision == true || game.tileM.tile[tileNum2].collision == true) {
                    entity.collisionOn = true;
                    System.out.println("collision down");
                }
                break;
            case "Left":
                entityLeftCol = (entityLeftx - entity.speed)/game.tileWidth;
                tileNum1 = game.tileM.mapTileNumber[entityLeftCol][entityTopRow];
                tileNum2 = game.tileM.mapTileNumber[entityLeftCol][entityBottomyRow];
                if (game.tileM.tile[tileNum1].collision == true || game.tileM.tile[tileNum2].collision == true) {
                    entity.collisionOn = true;
                    System.out.println("collision left");
                }
                break;
            case "Right":
                entityRightCol = (entityRightx + entity.speed)/game.tileWidth;
                tileNum1 = game.tileM.mapTileNumber[entityRightCol][entityTopRow];
                tileNum2 = game.tileM.mapTileNumber[entityRightCol][entityBottomyRow];
                if (game.tileM.tile[tileNum1].collision == true || game.tileM.tile[tileNum2].collision == true) {
                    entity.collisionOn = true;
                    System.out.println("collision right");
                }
                break;
        }
    }
}
