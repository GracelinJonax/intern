package background;

import Main.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Background {
    GamePanel gamePanel;
    public Tile[] tiles;
    public int[][] map;

    public Background(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
        tiles = new Tile[10];
        map = new int[gamePanel.maxWorldCol][gamePanel.maxWorldRow];
        getTileImage();
        loadMap("/maps/world01.txt");
    }

    public void loadMap(String path) {
        try {
            InputStream io = getClass().getResourceAsStream(path);
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(io));

            int col = 0;
            int row = 0;

            while (col < gamePanel.maxWorldCol && row < gamePanel.maxWorldRow) {
                String line = bufferedReader.readLine();
                System.out.println(line);
                String[] mapData = line.split(" ");
                for (String mapDatum : mapData) {
                    map[row][col] = Integer.parseInt(mapDatum);
                    col++;
                }
                col = 0;
                row++;
            }
            bufferedReader.close();
            System.out.println(Arrays.deepToString(map));
        } catch (Exception e) {

        }
    }

    public void getTileImage() {
        try {
            tiles[0] = new Tile();
            tiles[0].image = ImageIO.read(getClass().getResourceAsStream("/tiles/grass01.png"));

            tiles[1] = new Tile();
            tiles[1].image = ImageIO.read(getClass().getResourceAsStream("/tiles/wall.png"));
            tiles[1].collision = true;

            tiles[2] = new Tile();
            tiles[2].image = ImageIO.read(getClass().getResourceAsStream("/tiles/water.png"));
            tiles[2].collision = true;

            tiles[3] = new Tile();
            tiles[3].image = ImageIO.read(getClass().getResourceAsStream("/tiles/earth.png"));

            tiles[4] = new Tile();
            tiles[4].image = ImageIO.read(getClass().getResourceAsStream("/tiles/tree.png"));
            tiles[4].collision = true;

            tiles[5] = new Tile();
            tiles[5].image = ImageIO.read(getClass().getResourceAsStream("/tiles/sand.png"));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void draw(Graphics2D g2) {
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[0].length; j++) {
                int worldX = j * gamePanel.titleSize;
                int worldY = i * gamePanel.titleSize;
                int screenX = worldX - gamePanel.player.worldX + gamePanel.player.screenX;
                int screenY = worldY - gamePanel.player.worldY + gamePanel.player.screenY;
                int titleNo = map[i][j];
                if (worldX + gamePanel.titleSize > gamePanel.player.worldX - gamePanel.player.screenX &&
                        worldX - gamePanel.titleSize < gamePanel.player.worldX + gamePanel.player.screenX &&
                        worldY + gamePanel.titleSize > gamePanel.player.worldY - gamePanel.player.screenY &&
                        worldY - gamePanel.titleSize < gamePanel.player.worldY + gamePanel.player.screenY)
                    g2.drawImage(tiles[titleNo].image, screenX, screenY, gamePanel.titleSize, gamePanel.titleSize, null);
            }
        }
    }
}
