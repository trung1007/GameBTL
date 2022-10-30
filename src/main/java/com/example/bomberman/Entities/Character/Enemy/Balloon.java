package com.example.bomberman.Entities.Character.Enemy;

import com.example.bomberman.Entities.Character.Bomber.Bomber;
import com.example.bomberman.Entities.Character.Bomber.Boom;
import com.example.bomberman.Entities.Object;
import com.example.bomberman.Entities.Character.Enemy.AI.AI;
import com.example.bomberman.Entities.Character.Enemy.AI.AILow;
import com.example.bomberman.GamePanel;

import java.awt.*;

public class Balloon extends Enemies {
    GamePanel gamePanel;
    Bomber bomber;
    protected int directionBalloon;
    protected AI ai;

    public Balloon(int _x, int _y, Bomber bomber, GamePanel gamePanel, Boom boom) {
        this.bomber = bomber;
        this.gamePanel = gamePanel;
        setDefaultValues();
        sprites.getBalloonImage();
        ai = new AILow();
        x = _x;
        y = _y;
    }

    @Override
    public void setDefaultValues() {
        speed = 3;
    }

    @Override
    public void update(Object object) {
        if (CheckDie) {
            speed=0;
            die=true;
        }
        collisionOn = false;
        gamePanel.checkCollision.checkTile(this);
        for (int i = 0; i < bomber.booms.size(); i++) {
            gamePanel.checkCollision.checkCollisionBoom(this, bomber.booms.get(i));
        }
        if (collisionOn) {
            directionBalloon = ai.calculateDirection();
        }
        HandlePosition(directionBalloon);
        gamePanel.checkCollision.checkDieEnemy(bomber,this);
    }
    @Override
    public void render(Graphics2D g2) {
        switch (direction) {
            case "UP" -> {
                if (spriteNum == 1) {
                    image = sprites.BalloonUp1;
                }
                if (spriteNum == 2) {
                    image = sprites.BalloonUp2;
                }
                if (spriteNum == 3) {
                    image = sprites.BalloonUp3;
                }
                break;
            }
            case "DOWN" -> {
                if (spriteNum == 1) {
                    image = sprites.BalloonDown1;
                }
                if (spriteNum == 2) {
                    image = sprites.BalloonDown2;
                }
                if (spriteNum == 3) {
                    image = sprites.BalloonDown3;
                }
                break;
            }
            case "LEFT" -> {
                if (spriteNum == 1) {
                    image = sprites.BalloonLeft1;
                }
                if (spriteNum == 2) {
                    image = sprites.BalloonLeft2;
                }
                if (spriteNum == 3) {
                    image = sprites.BalloonLeft3;
                }
                break;
            }
            case "RIGHT" -> {
                if (spriteNum == 1) {
                    image = sprites.BalloonRight1;
                }
                if (spriteNum == 2) {
                    image = sprites.BalloonRight2;
                }
                if (spriteNum == 3) {
                    image = sprites.BalloonRight3;
                }
                break;
            }
        }
        if(die){
            image=null;
            countTime++;
            if(countTime<=TimeDieLoop){
                image=sprites.BalloonDie1;
            }
            else if(countTime<=TimeDieLoop*2){
                image=sprites.BalloonDie2;
            }
            if(countTime<=TimeDieLoop*3){
                image=sprites.BalloonDie3;
            }
            else if(countTime<=TimeDieLoop*4){
                image=sprites.BalloonDie4;
            }
            if(countTime<=TimeDieLoop*5){
                image=sprites.BalloonDie5;
            }
            if(countTime==TimeDieLoop*5){
                image=null;
                x=0;
                y=0;
                gamePanel.NumOfBoss --;
            }
        }
        g2.drawImage(image, x, y, GamePanel.SCALED_SIZE, GamePanel.SCALED_SIZE, null);
    }
}