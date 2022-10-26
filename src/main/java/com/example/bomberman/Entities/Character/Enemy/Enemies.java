package com.example.bomberman.Entities.Character.Enemy;

import com.example.bomberman.Entities.Character.Character;
import com.example.bomberman.graphics.Sprites;
import com.example.bomberman.Entities.Object;
import com.example.bomberman.GamePanel;

import java.awt.*;
import java.awt.image.BufferedImage;

public abstract class Enemies extends Character {
    int spriteCounter = 0;
    int spriteNum = 1;
    BufferedImage image = null;

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

    public void render(Graphics2D g2,String name){
        switch (direction) {
            case "UP" -> {
                if (spriteNum == 1) {
                    if(name.equals("Balloon")) {
                        image = sprites.BalloonUp1;
                    }
                    if(name.equals("Frog")) {
                        image=sprites.FrogUp1;
                    }
                    if(name.equals("DuyNgo")) {
                        image=sprites.BroomUp1;
                    }
                }
                if (spriteNum == 2) {
                    if(name.equals("Balloon")) {
                        image = sprites.BalloonUp2;
                    }
                    if(name.equals("Frog")) {
                        image=sprites.FrogUp2;
                    }
                    if(name.equals("DuyNgo")) {
                        image=sprites.BroomUp2;
                    }
                }
                if (spriteNum == 3) {
                    if(name.equals("Balloon")) {
                        image = sprites.BalloonUp3;
                    }
                    if(name.equals("Frog")) {
                        image=sprites.FrogUp3;
                    }
                    if(name.equals("DuyNgo")) {
                        image=sprites.BroomUp3;
                    }
                }
                break;
            }
            case "DOWN" -> {
                if (spriteNum == 1) {
                    if(name.equals("Balloon")) {
                        image = sprites.BalloonDown1;
                    }
                    if(name.equals("Frog")) {
                        image=sprites.FrogDown1;
                    }
                    if(name.equals("DuyNgo")) {
                        image=sprites.BroomDown1;
                    }
                }
                if (spriteNum == 2) {
                    if(name.equals("Balloon")) {
                        image = sprites.BalloonDown2;
                    }
                    if(name.equals("Frog")) {
                        image=sprites.FrogDown2;
                    }
                    if(name.equals("DuyNgo")) {
                        image=sprites.BroomDown2;
                    }
                }
                if (spriteNum == 3) {
                    if(name.equals("Balloon")) {
                        image = sprites.BalloonDown3;
                    }
                    if(name.equals("Frog")) {
                        image=sprites.FrogDown3;
                    }
                    if(name.equals("DuyNgo")) {
                        image=sprites.BroomDown3;
                    }
                }
                break;
            }
            case "LEFT" -> {
                if (spriteNum == 1) {
                    if(name.equals("Balloon")) {
                        image = sprites.BalloonLeft1;
                    }
                    if(name.equals("Frog")) {
                        image=sprites.FrogLeft1;
                    }
                    if(name.equals("DuyNgo")) {
                        image=sprites.BroomLeft1;
                    }
                }
                if (spriteNum == 2) {
                    if(name.equals("Balloon")) {
                        image = sprites.BalloonLeft2;
                    }
                    if(name.equals("Frog")) {
                        image=sprites.FrogLeft2;
                    }
                    if(name.equals("DuyNgo")) {
                        image=sprites.BroomLeft2;
                    }
                }
                if (spriteNum == 3) {
                    if(name.equals("Balloon")) {
                        image = sprites.BalloonLeft3;
                    }
                    if(name.equals("Frog")) {
                        image=sprites.FrogLeft3;
                    }
                    if(name.equals("DuyNgo")) {
                        image=sprites.BroomLeft3;
                    }
                }
                break;
            }
            case "RIGHT" -> {
                if (spriteNum == 1) {
                    if(name.equals("Balloon")) {
                        image = sprites.BalloonRight1;
                    }
                    if(name.equals("Frog")) {
                        image=sprites.FrogRight1;
                    }
                    if(name.equals("DuyNgo")) {
                        image=sprites.BroomRight1;
                    }
                }
                if (spriteNum == 2) {
                    if(name.equals("Balloon")) {
                        image = sprites.BalloonRight2;
                    }
                    if(name.equals("Frog")) {
                        image=sprites.FrogRight2;
                    }
                    if(name.equals("DuyNgo")) {
                        image=sprites.BroomRight2;
                    }
                }
                if (spriteNum == 3) {
                    if(name.equals("Balloon")) {
                        image = sprites.BalloonRight3;
                    }
                    if(name.equals("Frog")) {
                        image=sprites.FrogRight3;
                    }
                    if(name.equals("DuyNgo")) {
                        image=sprites.BroomRight3;
                    }
                }
                break;
            }
        }
        g2.drawImage(image, x, y, GamePanel.SCALED_SIZE, GamePanel.SCALED_SIZE, null);
    }

}
