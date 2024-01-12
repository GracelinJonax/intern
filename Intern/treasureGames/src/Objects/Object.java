package Objects;

import Main.GamePanel;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Object {
    public BufferedImage image;
    public String name;
    public boolean collision=false;
    public int worldX,worldY;

    public void draw(Graphics2D graphics2D, GamePanel gamePanel){
        int screenX = worldX - gamePanel.player.worldX + gamePanel.player.screenX;
        int screenY = worldY - gamePanel.player.worldY + gamePanel.player.screenY;
        if (worldX + gamePanel.titleSize > gamePanel.player.worldX - gamePanel.player.screenX &&
                worldX - gamePanel.titleSize < gamePanel.player.worldX + gamePanel.player.screenX &&
                worldY + gamePanel.titleSize > gamePanel.player.worldY - gamePanel.player.screenY &&
                worldY - gamePanel.titleSize < gamePanel.player.worldY + gamePanel.player.screenY)
            graphics2D.drawImage(image, screenX, screenY, gamePanel.titleSize, gamePanel.titleSize, null);

    }
}
