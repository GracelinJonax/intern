package Characters;

import Main.GamePanel;
import Main.KeyHandler;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Player extends Character {
    GamePanel gamePanel;
    KeyHandler keyHandler;

    public Player(GamePanel gamePanel, KeyHandler keyHandler) {
        this.gamePanel = gamePanel;
        this.keyHandler = keyHandler;
        setDefaultValues();
        getPlayerImage();
    }

    public void setDefaultValues() {
        x = 100;
        y = 100;
        speed = 4;
        direction = "right";
    }

    public void update() {
        if(keyHandler.up == true||keyHandler.down == true||keyHandler.left == true||keyHandler.right == true){
        if (keyHandler.up == true) {
            direction = "up";
            y -= speed;
        } else if (keyHandler.down == true) {
            direction = "down";
            y += speed;
        } else if (keyHandler.left == true) {
            direction = "left";
            x -= speed;
        } else if (keyHandler.right == true) {
            direction = "right";
            x += speed;
        }
        spriteCounter++;
        if (spriteCounter>12){
            if (spriteNo==1)
                spriteNo=2;
            else if (spriteNo == 2)
                spriteNo=1;

            spriteCounter=0;
        }}
    }

    public void draw(Graphics2D g2) {
//        g2.setColor(Color.WHITE);
//        g2.fillRect(x,y,gamePanel.titleSize,gamePanel.titleSize);
        BufferedImage image = null;
        switch (direction) {
            case "up":
                if (spriteNo == 1)
                    image = up1;
                if (spriteNo == 2)
                    image = up2;
                break;
            case "down":
                if (spriteNo == 1)
                    image = down1;
                if (spriteNo == 2)
                    image = down2;
                break;
            case "left":
                if (spriteNo == 1)
                    image = left1;
                if (spriteNo == 2)
                    image = left2;
                break;
            case "right":
                if (spriteNo == 1)
                    image = right1;
                if (spriteNo == 2)
                    image = right2;
                break;
        }
        g2.drawImage(image, x, y, gamePanel.titleSize, gamePanel.titleSize, null);
    }

    public void getPlayerImage() {
        try {
            up1 = ImageIO.read(getClass().getResource("/player/boy_up_1.png"));
            up2 = ImageIO.read(getClass().getResource("/player/boy_up_2.png"));
            down1 = ImageIO.read(getClass().getResource("/player/boy_down_1.png"));
            down2 = ImageIO.read(getClass().getResource("/player/boy_down_2.png"));
            left1 = ImageIO.read(getClass().getResource("/player/boy_left_1.png"));
            left2 = ImageIO.read(getClass().getResource("/player/boy_left_2.png"));
            right1 = ImageIO.read(getClass().getResource("/player/boy_right_1.png"));
            right2 = ImageIO.read(getClass().getResource("/player/boy_right_2.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
