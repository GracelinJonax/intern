package Main;

import Objects.Chest;
import Objects.Door;
import Objects.Key;

public class AssetSetter {
    GamePanel gamePanel;
    public AssetSetter(GamePanel gamePanel){
        this.gamePanel=gamePanel;
    }
    public void setObject(){
        gamePanel.object[0]=new Key();
        gamePanel.object[0].worldX=23*gamePanel.titleSize;
        gamePanel.object[0].worldY=7*gamePanel.titleSize;

        gamePanel.object[1]=new Key();
        gamePanel.object[1].worldX=23*gamePanel.titleSize;
        gamePanel.object[1].worldY=40*gamePanel.titleSize;

        gamePanel.object[2]=new Key();
        gamePanel.object[2].worldX=38*gamePanel.titleSize;
        gamePanel.object[2].worldY=8*gamePanel.titleSize;

        gamePanel.object[3]=new Door();
        gamePanel.object[3].worldX=10*gamePanel.titleSize;
        gamePanel.object[3].worldY=11*gamePanel.titleSize;

        gamePanel.object[4]=new Door();
        gamePanel.object[4].worldX=8*gamePanel.titleSize;
        gamePanel.object[4].worldY=28*gamePanel.titleSize;

        gamePanel.object[5]=new Door();
        gamePanel.object[5].worldX=12*gamePanel.titleSize;
        gamePanel.object[5].worldY=22*gamePanel.titleSize;

        gamePanel.object[6]=new Chest();
        gamePanel.object[6].worldX=10*gamePanel.titleSize;
        gamePanel.object[6].worldY=8*gamePanel.titleSize;
    }
}
