package com.example.bomberman.Entities.Character.Bomber;

import com.example.bomberman.Entities.Entity;
import com.example.bomberman.Entities.Object;
import com.example.bomberman.GamePanel;
import com.example.bomberman.input.Keyboard;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import static com.example.bomberman.Entities.Character.Bomber.Boom.*;

public class Bomber extends Entity {
    GamePanel gamePanel;
    Keyboard keyboard;
    int spriteCounter = 0;
    int spriteNum = 1;
    public int TimeThroughBrick = 0;
    public boolean GoThroughBrick = false;
    public int TimeArmor = 0;
    public int MaxTimeArmor=100000;
    public int MaxTimeGoThrough=350;
    public int MaxSpeed=8;
    public boolean Armor=false;
    public static ArrayList<Boom> booms = new ArrayList<>();
    public int NumLife = 1;


    public Bomber(GamePanel gamePanel, Keyboard keyboard) {
        this.gamePanel = gamePanel;
        this.keyboard = keyboard;

        setDefaultValues();
        sprites.getPlayerDie();
        sprites.getPlayerImage();
    }

    public void setDefaultValues() {
        x = 48;
        y = 144;
        speed = 4;
    }

    public void update(Object object) {
        keyboard.update();
        collisionOn = false;
        if (Armor) {
            TimeArmor--;
            CheckDie = false;
            if (TimeArmor == 0) {
                Armor = false;
            }
        }
        if (CheckDie) {
            NumLife = 0;
            CheckDie = false;
            speed = 0;
        }
        gamePanel.checkCollision.checkTile(this);
        if (GoThroughBrick) {
            collisionOn = false;
            TimeThroughBrick--;
            if (TimeThroughBrick == 0) {
                GoThroughBrick = false;
                if(Object.mapObjectNum[(y)/48][(x)/48]==2|| Object.mapObjectNum[(y)/48][(x)/48]==1){
                    CheckDie=true;
                }
            }
        }
        if (hasBoom) {
            setBoom(this);
        }
        if (gamePanel.Timer == 300) {
            NumLife = 0;
        }
        if (keyboard.space) {
            hasBoom = true;
        } else {
            hasBoom = false;
        }

        if (keyboard.right || keyboard.up ||
                keyboard.down || keyboard.left) {
            if (keyboard.up) {
                direction = "UP";
            } else if (keyboard.down) {
                direction = "DOWN";
            } else if (keyboard.left) {
                direction = "LEFT";
            } else {
                direction = "RIGHT";
            }
            if (!collisionOn) {
                switch (direction) {
                    case "UP": {
                        y -= speed;
                        if (Object.mapObjectNum[(y) / GamePanel.SCALED_SIZE][(x + GamePanel.SCALED_SIZE / 2) / GamePanel.SCALED_SIZE] == 5) {
                            Object.mapObjectNum[(y) / GamePanel.SCALED_SIZE][(x + gamePanel.SCALED_SIZE / 2) / gamePanel.SCALED_SIZE] = 0;
                            if(sizeBoom<MaxSizeBoom){
                                sizeBoom++;
                            }

                        }
                        if (object.mapObjectNum[y / gamePanel.SCALED_SIZE][(x + gamePanel.SCALED_SIZE / 2) / gamePanel.SCALED_SIZE] == 6) {
                            object.mapObjectNum[y / gamePanel.SCALED_SIZE][(x + gamePanel.SCALED_SIZE / 2) / gamePanel.SCALED_SIZE] = 0;
                            if (NumOfBoom < maxBoom) {
                                NumOfBoom++;
                            }
                        }
                        if (object.mapObjectNum[y / gamePanel.SCALED_SIZE][(x + gamePanel.SCALED_SIZE / 2) / gamePanel.SCALED_SIZE] == 7) {
                            object.mapObjectNum[y / gamePanel.SCALED_SIZE][(x + gamePanel.SCALED_SIZE / 2) / gamePanel.SCALED_SIZE] = 0;
                            if(speed<MaxSpeed){
                                speed++;
                            }
                        }
                        if (object.mapObjectNum[y / gamePanel.SCALED_SIZE][(x + gamePanel.SCALED_SIZE / 2) / gamePanel.SCALED_SIZE] == 8) {
                            object.mapObjectNum[y / gamePanel.SCALED_SIZE][(x + gamePanel.SCALED_SIZE / 2) / gamePanel.SCALED_SIZE] = 0;
                            TimeArmor = MaxTimeArmor;
                            Armor = true;
                        }
                        if (object.mapObjectNum[(y) / GamePanel.SCALED_SIZE][(x + GamePanel.SCALED_SIZE / 2) / GamePanel.SCALED_SIZE] == 9) {
                            object.mapObjectNum[(y) / gamePanel.SCALED_SIZE][(x + gamePanel.SCALED_SIZE / 2) / gamePanel.SCALED_SIZE] = 0;
                            GoThroughBrick = true;
                            TimeThroughBrick = MaxTimeGoThrough;

                        }
                        if (object.mapObjectNum[(y + gamePanel.SCALED_SIZE / 2) / gamePanel.SCALED_SIZE][(x + gamePanel.SCALED_SIZE) / gamePanel.SCALED_SIZE] == 99) {
                            object.nextMap = true;
                            GamePanel.Level++;

                        }
                        break;
                    }
                    case "DOWN": {
                        y += speed;
                        if (object.mapObjectNum[(y + gamePanel.SCALED_SIZE) / gamePanel.SCALED_SIZE][(x + gamePanel.SCALED_SIZE / 2) / gamePanel.SCALED_SIZE] == 5) {
                            object.mapObjectNum[(y + gamePanel.SCALED_SIZE) / gamePanel.SCALED_SIZE][(x + gamePanel.SCALED_SIZE / 2) / gamePanel.SCALED_SIZE] = 0;
                            if(sizeBoom<MaxSizeBoom){
                                sizeBoom++;
                            }

                        }
                        if (object.mapObjectNum[(y + gamePanel.SCALED_SIZE) / gamePanel.SCALED_SIZE][(x + gamePanel.SCALED_SIZE / 2) / gamePanel.SCALED_SIZE] == 6) {
                            object.mapObjectNum[(y + gamePanel.SCALED_SIZE) / gamePanel.SCALED_SIZE][(x + gamePanel.SCALED_SIZE / 2) / gamePanel.SCALED_SIZE] = 0;
                            if (NumOfBoom < maxBoom) {
                                NumOfBoom++;
                            }

                        }
                        if (object.mapObjectNum[(y + gamePanel.SCALED_SIZE) / gamePanel.SCALED_SIZE][(x + gamePanel.SCALED_SIZE / 2) / gamePanel.SCALED_SIZE] == 7) {
                            object.mapObjectNum[(y + gamePanel.SCALED_SIZE) / gamePanel.SCALED_SIZE][(x + gamePanel.SCALED_SIZE / 2) / gamePanel.SCALED_SIZE] = 0;
                            if(speed<MaxSpeed){
                                speed++;
                            }
                        }
                        if (object.mapObjectNum[(y + gamePanel.SCALED_SIZE) / gamePanel.SCALED_SIZE][(x + gamePanel.SCALED_SIZE / 2) / gamePanel.SCALED_SIZE] == 8) {
                            object.mapObjectNum[(y + gamePanel.SCALED_SIZE) / gamePanel.SCALED_SIZE][(x + gamePanel.SCALED_SIZE / 2) / gamePanel.SCALED_SIZE] = 0;
                            TimeArmor = MaxTimeArmor;
                            Armor = true;
                        }
                        if (object.mapObjectNum[(y + gamePanel.SCALED_SIZE) / gamePanel.SCALED_SIZE][(x + gamePanel.SCALED_SIZE / 2) / gamePanel.SCALED_SIZE] == 9) {
                            object.mapObjectNum[(y + gamePanel.SCALED_SIZE) / gamePanel.SCALED_SIZE][(x + gamePanel.SCALED_SIZE / 2) / gamePanel.SCALED_SIZE] = 0;
                            GoThroughBrick = true;
                            TimeThroughBrick = MaxTimeGoThrough;

                        }
                        if (object.mapObjectNum[(y + gamePanel.SCALED_SIZE / 2) / gamePanel.SCALED_SIZE][(x + gamePanel.SCALED_SIZE) / gamePanel.SCALED_SIZE] == 99) {

                            object.nextMap = true;
                            GamePanel.Level++;
                        }
                        break;
                    }
                    case "LEFT": {
                        x -= speed;
                        if (object.mapObjectNum[(y + gamePanel.SCALED_SIZE / 2) / gamePanel.SCALED_SIZE][(x) / gamePanel.SCALED_SIZE] == 5) {
                            object.mapObjectNum[(y + gamePanel.SCALED_SIZE / 2) / gamePanel.SCALED_SIZE][(x) / gamePanel.SCALED_SIZE] = 0;
                            if(sizeBoom<MaxSizeBoom){
                                sizeBoom++;
                            }
                        }
                        if (object.mapObjectNum[(y + gamePanel.SCALED_SIZE / 2) / gamePanel.SCALED_SIZE][(x) / gamePanel.SCALED_SIZE] == 6) {
                            object.mapObjectNum[(y + gamePanel.SCALED_SIZE / 2) / gamePanel.SCALED_SIZE][(x) / gamePanel.SCALED_SIZE] = 0;
                            if (NumOfBoom < maxBoom) {
                                NumOfBoom++;
                            }

                        }
                        if (object.mapObjectNum[(y + gamePanel.SCALED_SIZE / 2) / gamePanel.SCALED_SIZE][(x) / gamePanel.SCALED_SIZE] == 7) {
                            object.mapObjectNum[(y + gamePanel.SCALED_SIZE / 2) / gamePanel.SCALED_SIZE][(x) / gamePanel.SCALED_SIZE] = 0;
                            if(speed<MaxSpeed){
                                speed++;
                            }
                        }
                        if (object.mapObjectNum[(y + gamePanel.SCALED_SIZE / 2) / gamePanel.SCALED_SIZE][(x) / gamePanel.SCALED_SIZE] == 8) {
                            object.mapObjectNum[(y + gamePanel.SCALED_SIZE / 2) / gamePanel.SCALED_SIZE][(x) / gamePanel.SCALED_SIZE] = 0;
                            TimeArmor = MaxTimeArmor;
                            Armor = true;
                        }
                        if (object.mapObjectNum[(y + gamePanel.SCALED_SIZE / 2) / gamePanel.SCALED_SIZE][(x) / gamePanel.SCALED_SIZE] == 9) {
                            object.mapObjectNum[(y + gamePanel.SCALED_SIZE / 2) / gamePanel.SCALED_SIZE][(x) / gamePanel.SCALED_SIZE] = 0;
                            GoThroughBrick = true;
                            TimeThroughBrick = MaxTimeGoThrough;
                        }
                        if (object.mapObjectNum[(y + gamePanel.SCALED_SIZE / 2) / gamePanel.SCALED_SIZE][(x + gamePanel.SCALED_SIZE) / gamePanel.SCALED_SIZE] == 99) {

                            object.nextMap = true;
                            GamePanel.Level++;
                        }
                        break;
                    }
                    case "RIGHT": {
                        x += speed;
                        if (object.mapObjectNum[(y + gamePanel.SCALED_SIZE / 2) / gamePanel.SCALED_SIZE][(x + gamePanel.SCALED_SIZE) / gamePanel.SCALED_SIZE] == 5) {
                            object.mapObjectNum[(y + gamePanel.SCALED_SIZE / 2) / gamePanel.SCALED_SIZE][(x + gamePanel.SCALED_SIZE) / gamePanel.SCALED_SIZE] = 0;
                            if(sizeBoom<MaxSizeBoom){
                                sizeBoom++;
                            }
                        }
                        if (object.mapObjectNum[(y + gamePanel.SCALED_SIZE / 2) / gamePanel.SCALED_SIZE][(x + gamePanel.SCALED_SIZE) / gamePanel.SCALED_SIZE] == 6) {
                            object.mapObjectNum[(y + gamePanel.SCALED_SIZE / 2) / gamePanel.SCALED_SIZE][(x + gamePanel.SCALED_SIZE) / gamePanel.SCALED_SIZE] = 0;
                            if (NumOfBoom < maxBoom) {
                                NumOfBoom++;
                            }
                        }
                        if (object.mapObjectNum[(y + gamePanel.SCALED_SIZE / 2) / gamePanel.SCALED_SIZE][(x + gamePanel.SCALED_SIZE) / gamePanel.SCALED_SIZE] == 7) {
                            object.mapObjectNum[(y + gamePanel.SCALED_SIZE / 2) / gamePanel.SCALED_SIZE][(x + gamePanel.SCALED_SIZE) / gamePanel.SCALED_SIZE] = 0;
                            if(speed<MaxSpeed){
                                speed++;
                            }
                        }
                        if (object.mapObjectNum[(y + gamePanel.SCALED_SIZE / 2) / gamePanel.SCALED_SIZE][(x + gamePanel.SCALED_SIZE) / gamePanel.SCALED_SIZE] == 8) {
                            object.mapObjectNum[(y + gamePanel.SCALED_SIZE / 2) / gamePanel.SCALED_SIZE][(x + gamePanel.SCALED_SIZE) / gamePanel.SCALED_SIZE] = 0;
                            TimeArmor = MaxTimeArmor;
                            Armor = true;
                        }
                        if (object.mapObjectNum[(y + gamePanel.SCALED_SIZE / 2) / gamePanel.SCALED_SIZE][(x + gamePanel.SCALED_SIZE) / gamePanel.SCALED_SIZE] == 9) {
                            object.mapObjectNum[(y + gamePanel.SCALED_SIZE / 2) / gamePanel.SCALED_SIZE][(x + gamePanel.SCALED_SIZE) / gamePanel.SCALED_SIZE] = 0;
                            GoThroughBrick = true;
                            TimeThroughBrick = MaxTimeGoThrough;
                        }
                        if (object.mapObjectNum[(y + gamePanel.SCALED_SIZE / 2) / gamePanel.SCALED_SIZE][(x + gamePanel.SCALED_SIZE) / gamePanel.SCALED_SIZE] == 99) {
                            object.nextMap = true;
                            GamePanel.Level++;
                        }
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

    @Override
    public void render(Graphics2D g2) {
        BufferedImage image = null;
        switch (direction) {
            case "UP" -> {
                if (spriteNum == 1) {
                    image = sprites.PlayerUp1;
                }
                if (spriteNum == 2) {
                    image = sprites.PlayerUp2;
                }
                if (spriteNum == 3) {
                    image = sprites.PlayerUp3;
                }
                break;
            }
            case "DOWN" -> {
                if (spriteNum == 1) {
                    image = sprites.PlayerDown1;
                }
                if (spriteNum == 2) {
                    image = sprites.PlayerDown2;
                }
                if (spriteNum == 3) {
                    image = sprites.PlayerDown3;
                }
                break;
            }
            case "LEFT" -> {
                if (spriteNum == 1) {
                    image = sprites.PlayerLeft1;
                }
                if (spriteNum == 2) {
                    image = sprites.PlayerLeft2;
                }
                if (spriteNum == 3) {
                    image = sprites.PlayerLeft3;
                }
                break;
            }
            case "RIGHT" -> {
                if (spriteNum == 1) {
                    image = sprites.PlayerRight1;
                }
                if (spriteNum == 2) {
                    image = sprites.PlayerRight2;
                }
                if (spriteNum == 3) {
                    image = sprites.PlayerRight3;
                }
                break;
            }
        }
        if (NumLife == 0) {
            image = null;
            BufferedImage imageDie = null;
            countTime++;
            if (countTime <= 15) {
                imageDie = sprites.PlayerDie1;
            } else if (countTime <= 30) {
                imageDie = sprites.PlayerDie2;
            } else if (countTime <= 45) {
                imageDie = sprites.PlayerDie3;
            } else if (countTime <= 60) {
                imageDie = sprites.PlayerDie4;
            } else if (countTime <= 75) {
                imageDie = sprites.PlayerDie5;
            } else if (countTime <= 90) {
                imageDie = sprites.PlayerDie6;
            } else if (countTime < 105) {
                imageDie = sprites.PlayerDie7;
            }
            if (countTime == 105) {
                GamePanel.GameState = 2;
            }
            g2.drawImage(imageDie, x, y, GamePanel.SCALED_SIZE, GamePanel.SCALED_SIZE, null);
        }
        g2.drawImage(image, x, y, GamePanel.SCALED_SIZE, GamePanel.SCALED_SIZE, null);
    }


}