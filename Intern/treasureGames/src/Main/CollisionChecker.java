package Main;

import Characters.Character;

public class CollisionChecker {
    GamePanel gamePanel;
    public CollisionChecker(GamePanel gamePanel){
        this.gamePanel=gamePanel;
    }
    public void checkTile(Character character){
        int worldLeftX=character.worldX+character.solidArea.x;
        int worldRightX=character.worldX+character.solidArea.x+character.solidArea.width;
        int worldTopY=character.worldY+character.solidArea.y;
        int worldBottomY=character.worldY+character.solidArea.y+character.solidArea.height;

        int charLeftX=worldLeftX/gamePanel.titleSize;
        int charRightX=worldRightX/gamePanel.titleSize;
        int charTopY=worldTopY/gamePanel.titleSize;
        int charBottomY=worldBottomY/gamePanel.titleSize;

        int tile1,tile2;
        switch (character.direction){
            case "up":
                charTopY=(worldTopY-character.speed)/gamePanel.titleSize;
                tile1=gamePanel.background.map[charTopY][charLeftX];
                tile2=gamePanel.background.map[charTopY][charRightX];
                System.out.println(tile1+" "+tile2);
                if (gamePanel.background.tiles[tile1].collision==true||gamePanel.background.tiles[tile2].collision==true)
                    character.collisionOn=true;
                break;
            case "down":
                charBottomY=(worldBottomY+character.speed)/gamePanel.titleSize;
                tile1=gamePanel.background.map[charBottomY][charLeftX];
                tile2=gamePanel.background.map[charBottomY][charRightX];
                System.out.println(tile1+" "+tile2);
                if (gamePanel.background.tiles[tile1].collision==true||gamePanel.background.tiles[tile2].collision==true)
                    character.collisionOn=true;
                break;
            case "left":
                charLeftX=(worldLeftX-character.speed)/gamePanel.titleSize;
                tile1=gamePanel.background.map[charTopY][charLeftX];
                tile2=gamePanel.background.map[charBottomY][charLeftX];
                System.out.println(tile1+" "+tile2);
                if (gamePanel.background.tiles[tile1].collision==true||gamePanel.background.tiles[tile2].collision==true)
                    character.collisionOn=true;
                break;
            case "right":
                charRightX=(worldRightX-character.speed)/gamePanel.titleSize;
                tile1=gamePanel.background.map[charTopY][charRightX];
                tile2=gamePanel.background.map[charBottomY][charRightX];
                System.out.println(tile1+" "+tile2);
                if (gamePanel.background.tiles[tile1].collision==true||gamePanel.background.tiles[tile2].collision==true)
                    character.collisionOn=true;
                break;
        }
    }
    public int checkObject(Character character,boolean player){
        int index=0;
        for (int i=0;i<gamePanel.object.length;i++){
            character.solidArea.x=0;

        }
        return index;
    }
}
