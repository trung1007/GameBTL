package com.example.bomberman.Menu;

import com.example.bomberman.GamePanel;
import com.example.bomberman.input.Mouse;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class MenuUI {
    GamePanel gamePanel;
    Mouse mouse;
    public MenuUI(GamePanel gamePanel,Mouse mouse){
        this.gamePanel=gamePanel;
        this.mouse=mouse;
    }
    MenuOption menuOption1=new MenuOption("START",1,Color.GREEN);
    MenuOption menuOption2=new MenuOption("HELP",2,Color.YELLOW);
    MenuOption menuOption3=new MenuOption("EXIT",3,Color.RED);
    MenuBackground menuBackground=new MenuBackground();
    public void GameOverWindow(){
        JFrame frameExit=new JFrame("GameOver!");
        try {
            frameExit.setContentPane(new JLabel(new ImageIcon(ImageIO.read(getClass().getResourceAsStream("/sprites/Menu_Lose.jpg")))));
        } catch (IOException e) {
            e.printStackTrace();
        }
        frameExit.setResizable(true);
        frameExit.setLocation(480,100);
        frameExit.pack();
        frameExit.setVisible(true);
        frameExit.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }

    public void GameWinnerWindow(){
        JFrame frameExit=new JFrame("Winning!");
        try {
            frameExit.setContentPane(new JLabel(new ImageIcon(ImageIO.read(getClass().getResourceAsStream("/sprites/Winner.jpg")))));

        }catch (IOException e){
            e.printStackTrace();
        }
        frameExit.setResizable(true);
        frameExit.setLocation(48*12-30,48*4);
        frameExit.pack();
        frameExit.setVisible(true);
        frameExit.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }

    public void update(){

    }
    public void render(Graphics2D g2){
        if(GamePanel.GameState==0){
            menuBackground.renderMenuBackGround(g2);
            menuOption1.render(g2);
            menuOption2.render(g2);
            menuOption3.render(g2);
        }
        if(GamePanel.GameState==2){
            GameOverWindow();
        }
    }



}
