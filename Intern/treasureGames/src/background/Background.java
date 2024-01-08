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
    Tile[] tiles;
    int[][] map;

    public Background(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
        tiles = new Tile[10];
        map=new int[gamePanel.maxScreenRow][gamePanel.maxScreenCol];
        getTileImage();
        loadMap("/maps/map.txt");
    }
    public void loadMap(String path){
        try {
            InputStream io=getClass().getResourceAsStream(path);
            BufferedReader bufferedReader=new BufferedReader(new InputStreamReader(io));

            int col=0;
            int row=0;

            while (col< gamePanel.maxScreenCol && row< gamePanel.maxScreenRow){
                String line=bufferedReader.readLine();
                System.out.println(line);
                String mapData[]=line.split(" ");
                for (int i=0;i<mapData.length;i++){
                    System.out.println("i am in");
                    map[row][col]=Integer.parseInt(mapData[i]);
                    col++;
                }
                col=0;
                row++;
            }
            bufferedReader.close();
        }catch (Exception e){

        }
    }

    public void getTileImage() {
        try {
            tiles[0] = new Tile();
            tiles[0].image = ImageIO.read(getClass().getResourceAsStream("/tiles/grass01.png"));
            tiles[1] = new Tile();
            tiles[1].image = ImageIO.read(getClass().getResourceAsStream("/tiles/wall.png"));
            tiles[2] = new Tile();
            tiles[2].image = ImageIO.read(getClass().getResourceAsStream("/tiles/water.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void draw(Graphics2D g2) {
        for (int i=0,y=0;i<map.length;i++,y+= gamePanel.titleSize){
            int  x = 0;
            for (int j=0;j<map[0].length;j++) {
                int titleNo=map[i][j];
                g2.drawImage(tiles[titleNo].image, x, y, gamePanel.titleSize, gamePanel.titleSize, null);
                x += gamePanel.titleSize;
            }
        }
    }
}
