package com.example.bomberman.Entities.Character.Enemy;

import com.example.bomberman.Entities.Character.Bomber.Bomber;
import com.example.bomberman.Entities.Character.Bomber.Boom;
import com.example.bomberman.Entities.Character.Enemy.AI.AI;
import com.example.bomberman.Entities.Character.Enemy.AI.AIMedium2;
import com.example.bomberman.Entities.Object;
import com.example.bomberman.GamePanel;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Frog extends Enemies {
    GamePanel gamePanel;

    Bomber bomber;

    Boom boom;

    protected int directionFrog;

    protected AI ai;

    public Frog(int _x, int _y, Bomber bomber, GamePanel gamePanel, Boom boom) {

        this.gamePanel = gamePanel;
        setDefaultValues();
        sprites.getFrogImage();
        ai = new AIMedium2(bomber, this, boom);
        this.bomber = bomber;
        x = _x;
        y = _y;
    }

    @Override
    public void setDefaultValues() {
        speed = 3;
    }

    @Override
    public void update(Object object) {
        collisionOn = false;
        if ((bomber.x - this.x) * (bomber.x - this.x)
                + (bomber.y - this.y) * (bomber.y - this.y) <= 48 * 48 * 5 * 5) {
            speed = 4;
            directionFrog = ai.calculateDirection();
        } else {
            speed = 3;
        }
        if (CheckDie) {
            speed=0;
            die=true;
        }
        gamePanel.checkCollision.checkTile(this);
        for (int i = 0; i < bomber.booms.size(); i++) {
            gamePanel.checkCollision.checkDieEnemy1(this, bomber.booms.get(i));
        }
        if (collisionOn == true) {
            directionFrog = ai.calculateDirection();
        }
        HandlePosition(directionFrog);
        gamePanel.checkCollision.checkDieEnemy(bomber, this);
    }
@Override
    public void render(Graphics2D g2) {
        switch (direction) {
            case "UP" -> {
                if (spriteNum == 1) {
                    image = sprites.FrogUp1;

                }
                if (spriteNum == 2) {
                    image = sprites.FrogUp2;
                }
                if (spriteNum == 3) {
                    image = sprites.FrogUp3;
                }
                break;
            }
            case "DOWN" -> {
                if (spriteNum == 1) {
                    image = sprites.FrogDown1;
                }
                if (spriteNum == 2) {
                    image = sprites.FrogDown2;
                }
                if (spriteNum == 3) {
                    image = sprites.FrogDown3;
                }
                break;
            }
            case "LEFT" -> {
                if (spriteNum == 1) {
                    image = sprites.FrogLeft1;
                }
                if (spriteNum == 2) {
                    image = sprites.FrogLeft2;
                }
                if (spriteNum == 3) {
                    image = sprites.FrogLeft3;
                }
                break;
            }
            case "RIGHT" -> {
                if (spriteNum == 1) {
                    image = sprites.FrogRight1;
                }
                if (spriteNum == 2) {
                    image = sprites.FrogRight2;
                }
                if (spriteNum == 3) {
                    image = sprites.FrogRight3;
                }
                break;
            }
        }
        if(die){
            image=null;
            countTime++;
            if(countTime<=TimeDieLoop){
                image=sprites.FrogDie1;
            }
            else if(countTime<=TimeDieLoop*2){
                image=sprites.FrogDie2;
            }
            if(countTime<=TimeDieLoop*3){
                image=sprites.FrogDie3;
            }
            else if(countTime<=TimeDieLoop*4){
                image=sprites.FrogDie4;
            }
            if(countTime<=TimeDieLoop*5){
                image=sprites.FrogDie5;
            }
            else if(countTime<=TimeDieLoop*6){
                image=sprites.FrogDie6;
            }
            else if(countTime<TimeDieLoop*7){
                image=sprites.FrogDie7;
            }
            if(countTime==TimeDieLoop*7){
                image=null;
            }
        }
        g2.drawImage(image, x, y, GamePanel.SCALED_SIZE, GamePanel.SCALED_SIZE, null);
    }
}


