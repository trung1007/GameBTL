package com.example.bomberman.Entities;

import com.example.bomberman.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import static com.example.bomberman.Entities.Character.Bomber.Boom.NumOfBoom;
import static com.example.bomberman.Entities.Character.Bomber.Boom.sizeBoom;

public class Object{
    GamePanel gamePanel;
    Graphics2D g2;
    public BufferedImage[] object = new BufferedImage[100];
    public  boolean[] collision = new boolean[100];
    public static int[][] mapObjectNum;
    public boolean nextMap=false;



    public Object(GamePanel gamePanel){
        this.gamePanel = gamePanel;
        mapObjectNum = new int[GamePanel.MAX_SCREEN_ROW][GamePanel.MAX_SCREEN_COL];
        getObjectImage();
        loadMap("/levels/map.txt");
    }
    public void getObjectImage(){
        try{
            //get tile image

            object[0] = ImageIO.read(getClass().getResourceAsStream("/sprites/grass@2.png"));// grass
            collision[0] = false;
            object[1] = ImageIO.read(getClass().getResourceAsStream("/sprites/wall.png")); // wall
            collision[1] = true;
            object[2] = ImageIO.read(getClass().getResourceAsStream("/sprites/brick@1.png")); // brick
            collision[2] = true;
            object[3] = ImageIO.read(getClass().getResourceAsStream("/sprites/brick_explosion@2.png")); // brick
            collision[3] = true;
            object[4] = ImageIO.read(getClass().getResourceAsStream("/sprites/brick_explosion@3.png")); // brick
            collision[4] = true;
            //get item image

            object[5] = ImageIO.read(getClass().getResourceAsStream("/sprites/powerup_bombpass.png")); //size bomb
            collision[5] = false;
            object[6] = ImageIO.read(getClass().getResourceAsStream("/sprites/powerup_bombs.png"));//bombs
            collision[6] = false;
            object[7] = ImageIO.read(getClass().getResourceAsStream("/sprites/powerup_speed.png"));//speed
            collision[7] = false;
            object[8] = ImageIO.read(getClass().getResourceAsStream("/sprites/armor@1.png"));//speed
            collision[8] = false;
            object[9]=ImageIO.read(getClass().getResourceAsStream("/sprites/go_through_brick@1.png"));
            collision[9]=false;

            object[99] = ImageIO.read(getClass().getResourceAsStream("/sprites/portal@2.png"));


        }catch (IOException e){
            e.printStackTrace();
        }
    }


    public void win(){
        if(GamePanel.NumOfBoss ==0&&!nextMap){
            mapObjectNum[2][4] = 99;
        }

    }
    public void loadMap(String LevelMap){

        try{
            InputStream is = getClass().getResourceAsStream(LevelMap);
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(is));
            for(int i = 0; i < GamePanel.MAX_SCREEN_ROW; i++){
                String line = bufferedReader.readLine();
                for(int j = 0; j < GamePanel.MAX_SCREEN_COL; j++){
                    String numbers[] = line.split(" ");
                    int num = Integer.parseInt(numbers[j]);
                    mapObjectNum[i][j] = num;
                }
            }
            bufferedReader.close();
        }catch (IOException e){
            e.printStackTrace();
        }
    }
    public void update(){
        if(nextMap){
            if(GamePanel.Level==2){
                loadMap("/levels/map2.txt");
            }
            if(GamePanel.Level==3){
                loadMap("/levels/map3.txt");
            }
            nextMap = false;
        }
        if(GamePanel.Level == 2){
            GamePanel.NumOfBoss = 2;
        }
        if(GamePanel.Level==3){
            GamePanel.NumOfBoss =6;
        }
    }

    public void render(Graphics2D g2){
        int x = 0;// vi tri x
        int y = 0; // vi tri y
        for(int i = 0; i < GamePanel.MAX_SCREEN_ROW; i++){
            for(int j = 0; j < GamePanel.MAX_SCREEN_COL; j++){
                int tileNum = mapObjectNum[i][j];
                g2.drawImage(object[tileNum], x, y, GamePanel.SCALED_SIZE, GamePanel.SCALED_SIZE, null);
                x += GamePanel.SCALED_SIZE;
            }
            x = 0;
            y += GamePanel.SCALED_SIZE;
        }
    }



}
