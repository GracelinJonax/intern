package Characters;

import Main.GamePanel;
import Main.KeyHandler;

import java.awt.*;
import java.io.IOException;

public class Player extends Character{
    GamePanel gamePanel;
    KeyHandler keyHandler;
    public Player(GamePanel gamePanel,KeyHandler keyHandler){
    this.gamePanel=gamePanel;
    this.keyHandler=keyHandler;
    setDefaultValues();
    }
    public void setDefaultValues(){
        x=100;
        y=100;
        speed=4;
    }
    public void update(){
        if(keyHandler.up==true)
            y-=speed;
        else if(keyHandler.down==true)
            y+=speed;
        else if(keyHandler.left==true)
            x-=speed;
        else if (keyHandler.right==true)
            x+=speed;
    }

    public void draw(Graphics2D g2){
        g2.setColor(Color.WHITE);
        g2.fillRect(x,y,gamePanel.titleSize,gamePanel.titleSize);
    }
    public void getPlayerImage(){
        try {

        }
        catch (IOException e){

        }
    }
}
