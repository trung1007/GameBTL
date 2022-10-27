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
        JFrame frameExit=new JFrame("Exit!");
        try {
            frameExit.setContentPane(new JLabel(new ImageIcon(ImageIO.read(getClass().getResourceAsStream("/sprites/game_over.png")))));
        } catch (IOException e) {
            e.printStackTrace();
        }
        frameExit.setResizable(true);
        frameExit.setLocation(300,100);
        frameExit.pack();
        frameExit.setVisible(true);
        frameExit.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }

    public void update(){

    }
    public void render(Graphics2D g2){
        if(GamePanel.GameState==1){
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
