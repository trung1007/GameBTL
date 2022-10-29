package com.example.bomberman.Entities.Character.Enemy;

import com.example.bomberman.Entities.Entity;
import com.example.bomberman.Entities.Object;
import com.example.bomberman.GamePanel;

import java.awt.*;
import java.awt.image.BufferedImage;

public abstract class Enemies extends Entity {
    int spriteCounter = 0;
    int spriteNum = 1;
    BufferedImage image = null;
    public final int TimeDieLoop=15;
    public boolean die=false;

    public abstract void setDefaultValues();
    public abstract void update(Object object);
    public void HandlePosition(int directionEnemy){
        if( directionEnemy == 0 || directionEnemy == 1
                || directionEnemy == 2 || directionEnemy == 3) {
            if (directionEnemy == 0) {
                direction = "UP";
            } else if (directionEnemy == 1) {
                direction = "RIGHT";
            } else if (directionEnemy == 2) {
                direction = "DOWN";
            } else if (directionEnemy == 3) {
                direction = "LEFT";
            }
            if(collisionOn == false) {
                switch (direction){
                    case "UP": {
                        y -= speed;

                        break;
                    }
                    case "DOWN": {
                        y += speed;

                        break;
                    }
                    case "LEFT": {
                        x -= speed;

                        break;
                    }
                    case "RIGHT": {
                        x += speed;

                        break;
                    }
                }
            }

            spriteCounter++;
            if (spriteCounter >= 6) {
                if (spriteNum == 1) {
                    spriteNum = 2;
                } else if (spriteNum == 2) {
                    spriteNum = 3;
                } else if (spriteNum == 3) {
                    spriteNum = 1;
                }
                spriteCounter = 0;
            }
        }
    }


}
