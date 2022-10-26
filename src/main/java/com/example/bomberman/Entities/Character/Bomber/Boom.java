package com.example.bomberman.Entities.Character.Bomber;

import com.example.bomberman.Entities.Character.Character;
import com.example.bomberman.GamePanel;
import com.example.bomberman.Entities.Object;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Boom extends Character {
    GamePanel gamePanel;
    static Object object;
    Bomber bomber;
    public int boomCount = 0;

    public int SIZE = GamePanel.SCALED_SIZE;
    public static boolean explored = true; // kiem tra xem da no hay chua
    public boolean isExploring = false; // kiem tra xem co dang no hay khong
    public  int frameUp;
    public  int frameDown;
    public  int frameLeft;
    public  int frameRight;
//    public static int x, y;
    private int maxBoom = 4;

    boolean checkCollisionBrickUp;
    public static int sizeBoom=1;


    public int NumOfBoom = 1;
    public boolean NumIncrease;
//    public int xB, yB;

    public static boolean hasBoom;


    public Boom(GamePanel gamePanel, Bomber bomber) {
        this.gamePanel = gamePanel;
        this.bomber = bomber;
        sprites.getBombImage();
    }
    public Boom(int x, int y){
        this.x = x;
        this.y = y;
        sprites.getBombImage();
    }
    public static void setBoom(Bomber bomber){
        int row = (bomber.y + GamePanel.SCALED_SIZE / 2) / GamePanel.SCALED_SIZE * GamePanel.SCALED_SIZE;
        int col = (bomber.x + GamePanel.SCALED_SIZE / 2) / GamePanel.SCALED_SIZE * GamePanel.SCALED_SIZE;
        for(int i = 0; i < bomber.booms.size();i++){
            if(col == bomber.booms.get(i).x && row == bomber.booms.get(i).y){
                return;
            }
        }
        if(bomber.booms.size()<=2){
            bomber.booms.add(new Boom(col,row));
        }


        System.out.println("x: " + bomber.x);
        System.out.println("y: " + bomber.y);

    }


    public int FrameUp() {
        int frame = 0;
        for (int i = 1; i <= sizeBoom; i++) {
            if (object.mapObjectNum[(y - i * SIZE) / SIZE][x / SIZE] == 1
                    ||object.mapObjectNum[(y - i * SIZE) / SIZE][x / SIZE] == 2
                    ||object.mapObjectNum[(y - i * SIZE) / SIZE][x / SIZE] == 3
                    ||object.mapObjectNum[(y - i * SIZE) / SIZE][x / SIZE] == 4) {
                break;
            } else {
                frame++;
            }
        }
        frameUp = frame;
        return frameUp;
    }

    public   int FrameDown() {
        int frame = 0;
        for (int i = 1; i <= sizeBoom; i++) {
            if (object.mapObjectNum[(y + i * SIZE) / SIZE][x / SIZE] == 1
                    || object.mapObjectNum[(y + i * SIZE) / SIZE][x / SIZE] == 2
                    ||object.mapObjectNum[(y + i * SIZE) / SIZE][x / SIZE] == 3
                    ||object.mapObjectNum[(y + i * SIZE) / SIZE][x / SIZE] == 4) {

                break;
            } else {
                frame++;
            }
        }
        frameDown = frame;
        return frameDown;
    }

    public    int FrameLeft() {
        int frame = 0;
        for (int i = 1; i <= sizeBoom; i++) {
            if (object.mapObjectNum[y / SIZE][(x - i * SIZE) / SIZE] == 1
                    || object.mapObjectNum[y / SIZE][(x - i * SIZE) / SIZE] == 2
                    ||object.mapObjectNum[y / SIZE][(x - i * SIZE) / SIZE] == 3
                    ||object.mapObjectNum[y / SIZE][(x - i * SIZE) / SIZE] == 4) {
                break;
            } else {
                frame++;
            }
        }
        frameLeft = frame;
        return frameLeft;
    }

    public   int  FrameRight() {
        int frame = 0;
        for (int i = 1; i <= sizeBoom; i++) {
            if (object.mapObjectNum[y / SIZE][(x + i * SIZE) / SIZE] == 1
                    || object.mapObjectNum[y / SIZE][(x + i * SIZE) / SIZE] == 2
                    ||object.mapObjectNum[y / SIZE][(x + i * SIZE) / SIZE] == 3
                    ||object.mapObjectNum[y / SIZE][(x + i * SIZE) / SIZE] == 4) {
                break;
            } else {
                frame++;
            }
        }
        frameRight = frame;
        return frameRight;
    }


    public void drawFrame(Graphics2D g2, BufferedImage image1, BufferedImage image2, int x, int y, int frame, String direction) {
        for (int i = 1; i <= frame; i++) {
            if (i != frame) {
                switch (direction) {
                    case "Up":
                        g2.drawImage(image1, x, y - i * SIZE, SIZE, SIZE, null);
                        break;
                    case "Down":
                        g2.drawImage(image1, x, y + i * SIZE, SIZE, SIZE, null);
                        break;
                    case "Left":
                        g2.drawImage(image1, x - i * SIZE, y, SIZE, SIZE, null);
                        break;
                    case "Right":
                        g2.drawImage(image1, x + i * SIZE, y, SIZE, SIZE, null);
                        break;
                }
            } else {
                switch (direction) {
                    case "Up":
                        g2.drawImage(image2, x, y - i * SIZE, SIZE, SIZE, null);
                        break;
                    case "Down":
                        g2.drawImage(image2, x, y + i * SIZE, SIZE, SIZE, null);
                        break;
                    case "Left":
                        g2.drawImage(image2, x - i * SIZE, y, SIZE, SIZE, null);
                        break;
                    case "Right":
                        g2.drawImage(image2, x + i * SIZE, y, SIZE, SIZE, null);
                        break;
                }
            }
        }
    }

    public void update(Bomber bomber) {
        explored = false;
        FrameUp();
        FrameDown();
        FrameLeft();
        FrameRight();
    }
    public void render(Graphics2D g2, String name, GamePanel gamePanel) {
        if (!explored) {
            BufferedImage image = null;
            if (!isExploring) {
                if (countTime <= intervalToExplored) {
                    image = sprites.Bomb1;
                } else if (countTime <= intervalToExplored * 2) {
                    image = sprites.Bomb2;
                } else if (countTime < intervalToExplored * 3) {
                    image = sprites.Bomb3;
                }
                if (countTime == intervalToExplored * 3) {
                    isExploring = true;
                    image = null;
                    countTime = 0;
                }
            }
            g2.drawImage(image, x, y, SIZE, SIZE, null);
            if (isExploring) {
                if (countTime <= timeExploring) {
                    gamePanel.checkCollision.checkFlameBomb(this, countTime, timeExploring);
                    gamePanel.checkCollision.checkDie(gamePanel.bomber, this);
                    gamePanel.checkCollision.checkDie(gamePanel.balloon1, this);
                    gamePanel.checkCollision.checkDie(gamePanel.balloon2, this);
                    gamePanel.checkCollision.checkDie(gamePanel.duyNgo1, this);
                    gamePanel.checkCollision.checkDie(gamePanel.duyNgo2, this);
                    g2.drawImage(sprites.BombCenter1, x, y, SIZE, SIZE, null);
                    drawFrame(g2, sprites.BombVer1, sprites.BombUp1, x, y, frameUp, "Up");
                    drawFrame(g2, sprites.BombVer1, sprites.BombDown1, x, y, frameDown, "Down");
                    drawFrame(g2, sprites.BombHor1, sprites.BombLeft1, x, y, frameLeft, "Left");
                    drawFrame(g2, sprites.BombHor1, sprites.BombRight1, x, y, frameRight, "Right");

                } else if (countTime <= timeExploring * 2) {
                    gamePanel.checkCollision.checkFlameBomb(this, countTime, timeExploring);
                    gamePanel.checkCollision.checkDie(gamePanel.bomber, this);
                    gamePanel.checkCollision.checkDie(gamePanel.balloon1, this);
                    gamePanel.checkCollision.checkDie(gamePanel.balloon2, this);
                    gamePanel.checkCollision.checkDie(gamePanel.duyNgo1, this);
                    gamePanel.checkCollision.checkDie(gamePanel.duyNgo2, this);
                    g2.drawImage(sprites.BombCenter2, x, y, SIZE, SIZE, null);
                    drawFrame(g2, sprites.BombVer2, sprites.BombUp2, x, y, frameUp, "Up");
                    drawFrame(g2, sprites.BombVer2, sprites.BombDown2, x, y, frameDown, "Down");
                    drawFrame(g2, sprites.BombHor2, sprites.BombLeft2, x, y, frameLeft, "Left");
                    drawFrame(g2, sprites.BombHor2, sprites.BombRight2, x, y, frameRight, "Right");
                } else if (countTime <= timeExploring * 3) {
                    gamePanel.checkCollision.checkFlameBomb(this, countTime, timeExploring);
                    gamePanel.checkCollision.checkDie(gamePanel.bomber, this);
                    gamePanel.checkCollision.checkDie(gamePanel.balloon1, this);
                    gamePanel.checkCollision.checkDie(gamePanel.balloon2, this);
                    gamePanel.checkCollision.checkDie(gamePanel.duyNgo1, this);
                    gamePanel.checkCollision.checkDie(gamePanel.duyNgo2, this);
                    g2.drawImage(sprites.BombCenter3, x, y, SIZE, SIZE, null);
                    drawFrame(g2, sprites.BombVer3, sprites.BombUp3, x, y, frameUp, "Up");
                    drawFrame(g2, sprites.BombVer3, sprites.BombDown3, x, y, frameDown, "Down");
                    drawFrame(g2, sprites.BombHor3, sprites.BombLeft3, x, y, frameLeft, "Left");
                    drawFrame(g2, sprites.BombHor3, sprites.BombRight3, x, y, frameRight, "Right");
                }
                if (countTime == timeExploring * 3) {
                    explored = true;
                    isExploring = false;
                    countTime = 0;
                    boomCount = 0;
                    bomber.booms.remove(this);
                }
            }
            countTime++;
        }
    }
}