package com.example.bomberman.Entities.Character.Enemy;

import com.example.bomberman.Entities.Character.Bomber.Bomber;
import com.example.bomberman.Entities.Character.Bomber.Boom;
import com.example.bomberman.Entities.Character.Enemy.AI.AI;
import com.example.bomberman.Entities.Character.Enemy.AI.AIMedium;
import com.example.bomberman.Entities.Object;
import com.example.bomberman.GamePanel;

import java.awt.*;

public class Broom extends Enemies {
    GamePanel gamePanel;
    Bomber bomber;
    protected int directionDuyNgo;
    public int lastX, lastY;
    protected AI ai;

    public Broom(int _x, int _y, Bomber bomber, GamePanel gamePanel, Boom boom) {
        this.bomber = bomber;
        this.gamePanel = gamePanel;
        sprites.getBroomImage();
        ai = new AIMedium(bomber, this, boom);
        x = _x;
        y = _y;
        setDefaultValues();
    }

    @Override
    public void setDefaultValues() {
        speed = 3;
        lastX = x;
        lastY = y;

    }


    @Override
    public void update(Object object) {
        if(Math.abs(lastX - x) == 48 ){
            directionDuyNgo = ai.calculateDirection();
            lastX = x;
        }
        if(Math.abs(lastY - y) == 48 ){
            directionDuyNgo = ai.calculateDirection();
            lastY = y;
        }
        if (CheckDie) {
            speed=0;
            die=true;
        }
        collisionOn = false;
        gamePanel.checkCollision.checkTile(this);
        for (int i = 0; i < bomber.booms.size(); i++) {
            gamePanel.checkCollision.checkCollisionBoom(this, bomber.booms.get(i));
        }
        if (collisionOn == true) {
            directionDuyNgo = ai.calculateDirectionRandom();
        }
        HandlePosition(directionDuyNgo);
        gamePanel.checkCollision.checkDieEnemy(bomber, this);
    }
    @Override
    public void render(Graphics2D g2) {
        switch (direction) {
            case "UP" -> {
                if (spriteNum == 1) {
                    image = sprites.BroomUp1;

                }
                if (spriteNum == 2) {
                    image = sprites.BroomUp2;

                }
                if (spriteNum == 3) {
                    image = sprites.BroomUp3;

                }
                break;
            }
            case "DOWN" -> {
                if (spriteNum == 1) {
                    image = sprites.BroomDown1;

                }
                if (spriteNum == 2) {
                    image = sprites.BroomDown2;

                }
                if (spriteNum == 3) {
                    image = sprites.BroomDown3;

                }
                break;
            }
            case "LEFT" -> {
                if (spriteNum == 1) {
                    image = sprites.BroomLeft1;

                }
                if (spriteNum == 2) {
                    image = sprites.BroomLeft2;

                }
                if (spriteNum == 3) {
                    image = sprites.BroomLeft3;

                }
                break;
            }
            case "RIGHT" -> {
                if (spriteNum == 1) {
                    image = sprites.BroomRight1;
                }
                if (spriteNum == 2) {
                    image = sprites.BroomRight2;
                }
                if (spriteNum == 3) {
                    image = sprites.BroomRight3;
                }
                break;
            }
        }
        if(die){
            image=null;
            countTime++;
            if(countTime<=TimeDieLoop){
                image=sprites.BroomDie1;
            }
            else if(countTime<=TimeDieLoop*2){
                image=sprites.BroomDie2;
            }
            if(countTime<=TimeDieLoop*3){
                image=sprites.BroomDie3;
            }
            else if(countTime<=TimeDieLoop*4){
                image=sprites.BroomDie4;
            }
            if(countTime<=TimeDieLoop*5){
                image=sprites.BroomDie5;
            }
            else if(countTime<=TimeDieLoop*6){
                image=sprites.BroomDie6;
            }
            else if(countTime<TimeDieLoop*7){
                image=sprites.BroomDie7;
            }
            if(countTime==TimeDieLoop*7){
                image=null;
                x=0;
                y=0;
                gamePanel.NumOfBoss --;
            }
        }
        g2.drawImage(image, x, y, GamePanel.SCALED_SIZE, GamePanel.SCALED_SIZE, null);
    }
}