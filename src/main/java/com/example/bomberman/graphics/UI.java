package com.example.bomberman.graphics;

import com.example.bomberman.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class UI {
    GamePanel gamePanel;
    Graphics2D g2;
    Font arial_20;
    BufferedImage winning;
    public UI(GamePanel gamePanel){
        this.gamePanel = gamePanel;
        arial_20 = new Font("Arial", Font.PLAIN, 20);
    }
    public void drawPauseScreen(){
        String text = "PAUSED";
        int x;
        int length = (int)g2.getFontMetrics().getStringBounds(text,g2).getWidth();
        int width=(int)g2.getFontMetrics().getStringBounds(text,g2).getHeight();
        x = GamePanel.SCREEN_WIDTH/2 - length/2;
        int y = GamePanel.SCREEN_HEIGHT/2;
        Rectangle rectangle = new Rectangle(x-length/2-8,y-48-width/2,144,96);
        g2.setColor(new Color(210,105,30));
        g2.fill(rectangle);
        g2.setFont(arial_20);
        g2.setColor(Color.WHITE);
        g2.drawString(text,x,y);
    }
    public void draw(Graphics2D g2){
        this.g2=g2;
        if(GamePanel.GameState == 4){
            drawPauseScreen();
        }
        g2.setColor(new Color(193,205,205));
        g2.fillRect(0,0,GamePanel.SCREEN_WIDTH,GamePanel.SCALED_SIZE);
        g2.setFont(new Font("Arial", Font.PLAIN, 20));
        g2.setColor(Color.BLACK);
        g2.drawString("Level: "+GamePanel.Level,GamePanel.SCREEN_WIDTH/2-28,20);
        g2.drawString("SizeBomb: " + gamePanel.boom.sizeBoom, 48, 40 );
        g2.drawString("Speed: " + gamePanel.bomber.speed, 48*4, 40 );
        g2.drawString("TimeGoThrough: "+gamePanel.bomber.TimeThroughBrick/60,48*7,40);
        g2.drawString("MaxOfBomb: "+gamePanel.boom.maxBoom,48*12,40);
        g2.drawString("TimeArmor: "+gamePanel.bomber.TimeArmor/60,48*16,40);
        g2.drawString("PlayerLife: "+gamePanel.bomber.NumLife, 48*20, 40 );
        g2.drawString("Time: "+gamePanel.Timer,48*24,40);
    }
}