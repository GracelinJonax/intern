package Main;

import Characters.Player;
import background.Background;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel implements Runnable{
    final int originalTileSize=16;
    final int scale=3;
    public final int titleSize=originalTileSize*scale;
    public final int maxScreenCol=16;
    public final int maxScreenRow=12;
    public final int screenWidth=titleSize*maxScreenCol;
    public final int screenHeight=titleSize*maxScreenRow;

    int FPS=60;
    KeyHandler kh=new KeyHandler();
    Thread gameThread;
    Player player=new Player(this,kh);
    Background background=new Background(this);

    public GamePanel(){
        this.setPreferredSize(new Dimension(screenWidth,screenHeight));
        this.setBackground(Color.BLACK);
        this.setDoubleBuffered(true);
        this.addKeyListener(kh);
        this.setFocusable(true);
    }
    public void startGameThread(){
        gameThread=new Thread(this);
        gameThread.start();
    }

    @Override
//    public void run() {
//        double drawInterval=1000000000/FPS;
//        double nextDrawTime=System.nanoTime()+drawInterval;
//        while (gameThread!=null ){
//            update();
//            repaint();
//
//            try {
//                double remainingTime=   nextDrawTime-System.nanoTime();
//                remainingTime/=1000000;
//                if(remainingTime<0)
//                    remainingTime=0;
//                Thread.sleep((long) remainingTime);
//                nextDrawTime+=drawInterval;
//            } catch (InterruptedException e) {
//                throw new RuntimeException(e);
//            }
//        }
//    }

    public void run() {
        double drawInterval=1000000000/FPS;
        double delta=0;
        long lastTime=System.nanoTime();
        long currentTime;
        long timer=0;
        int drawCount=0;
        while (gameThread!=null ){
            currentTime=System.nanoTime();
            delta+=(currentTime-lastTime)/drawInterval;
            timer+=currentTime-lastTime;
            lastTime=currentTime;
            if(delta>=1){
            update();
            repaint();
            delta--;
            drawCount++;
            }
        if (timer >= 1000000000){
            System.out.println("FPS: "+drawCount);
        drawCount=0;
         timer=0;
    }}}
    public void update(){
        player.update();
    }
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2=(Graphics2D) g;
        background.draw(g2);
        player.draw(g2);
        g2.dispose();
    }
}
