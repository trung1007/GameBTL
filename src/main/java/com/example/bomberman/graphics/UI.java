package com.example.bomberman.graphics;

import com.example.bomberman.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class UI {
    GamePanel gamePanel;
    Font arial_20;
    public UI(GamePanel gamePanel){
        this.gamePanel = gamePanel;
        arial_20 = new Font("Arial", Font.PLAIN, 20);
    }
    public void draw(Graphics2D g2){
        g2.setColor(new Color(193,205,205));
        g2.fillRect(0,0,GamePanel.SCREEN_WIDTH,GamePanel.SCALED_SIZE);

        g2.setFont(new Font("Arial", Font.PLAIN, 20));
        g2.setColor(Color.BLACK);
        g2.drawString("Level 1",GamePanel.SCREEN_WIDTH/2-28,20);
        g2.drawString("SizeBomb: " + gamePanel.boom.sizeBoom, 20, 40 );
        g2.drawString("Speed: " + gamePanel.bomber.speed, 150, 40 );
        g2.drawString("MaxOfBomb: "+gamePanel.boom.maxBoom,270,40);
        g2.drawString("TimeGoThrough: "+(int)gamePanel.bomber.TimeThroughBrick/35,430,40);
        g2.drawString("TimeArmor: "+1,630,40);
        g2.drawString("Time: "+gamePanel.Timer,800,40);
        g2.drawString("PlayerLife: "+gamePanel.bomber.NumLife, 920, 40 );
    }
}