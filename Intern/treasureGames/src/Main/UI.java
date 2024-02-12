package Main;

import Objects.Key;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.text.DecimalFormat;

public class UI {
    GamePanel gamePanel;
    Font font;
    BufferedImage key;
    int messageCounter = 0;
    public boolean messageOn = false;
    public String message = "";
    public boolean gameFinished = false;
    double playTime;
    DecimalFormat decimalFormat=new DecimalFormat("#0.00");

    public UI(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
        font = new Font("Arial", Font.BOLD, 25);
        Key keys = new Key();
        key = keys.image;
    }

    public void draw(Graphics2D g2) {
        if (gameFinished) {
            String text ;
            int x;
            int y;

            text="got the treasure!!";
            x = gamePanel.screenWidth / 3;
            y = gamePanel.screenHeight / 2 - (gamePanel.titleSize * 3);
            g2.setFont(font);
            g2.setColor(Color.WHITE);
            g2.drawString(text, x, y);

            text="Your Time is:  "+decimalFormat.format(playTime)+"!";
            x = gamePanel.screenWidth / 3;
            y = gamePanel.screenHeight / 2 + (gamePanel.titleSize * 4);
            g2.setFont(font);
            g2.setColor(Color.WHITE);
            g2.drawString(text, x, y);
            g2.setFont(font.deriveFont(80F));
            g2.setColor(Color.PINK);
            text = "Congratulation!!!";
            x = gamePanel.screenWidth / 6;
            y = gamePanel.screenHeight / 2 ;
            g2.drawString(text, x, y);

            gamePanel.gameThread=null;
        } else {
            g2.setFont(font);
            g2.setColor(Color.WHITE);
            g2.drawImage(key, gamePanel.titleSize / 2, gamePanel.titleSize / 2, gamePanel.titleSize, gamePanel.titleSize, null);
            g2.drawString("x  " + gamePanel.player.hasKeys, 74, 65);
            playTime+= (double) 1 /60;
            g2.drawString("Time:   "+decimalFormat.format(playTime),gamePanel.titleSize*11,65);
            if (messageOn == true) {
                g2.setFont(g2.getFont().deriveFont(30F));
                g2.drawString(message, gamePanel.titleSize / 2, (gamePanel.titleSize * 3) - 30);

                messageCounter++;
                if (messageCounter > 120) {
                    messageCounter = 0;
                    messageOn = false;
                }
            }
        }
    }

    public void showMessage(String text) {
        message = text;
        messageOn = true;
    }
}
