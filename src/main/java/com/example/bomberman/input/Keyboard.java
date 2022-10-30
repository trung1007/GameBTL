package com.example.bomberman.input;

import com.example.bomberman.GamePanel;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * Tiếp nhận và xử lý các sự kiện nhập từ bàn phím
 */
public class Keyboard implements KeyListener {
    GamePanel gamePanel;
    private boolean[] keys = new boolean[120]; //120 is enough to this game
    public boolean up, down, left, right, space,pause,xpause;
    public Keyboard(GamePanel gamePanel){
        this.gamePanel = gamePanel;
    }
    public void update() {
        up = keys[KeyEvent.VK_UP] || keys[KeyEvent.VK_W];
        down = keys[KeyEvent.VK_DOWN] || keys[KeyEvent.VK_S];
        left = keys[KeyEvent.VK_LEFT] || keys[KeyEvent.VK_A];
        right = keys[KeyEvent.VK_RIGHT] || keys[KeyEvent.VK_D];
        space = keys[KeyEvent.VK_SPACE] || keys[KeyEvent.VK_X];
        pause=keys[KeyEvent.VK_P];
        xpause=keys[KeyEvent.VK_X];
    }

    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyPressed(KeyEvent e) {
        keys[e.getKeyCode()] = true;
        if(e.getKeyCode() == KeyEvent.VK_P){
            if(GamePanel.GameState == 1){
                GamePanel.GameState = 4;
            } else if(GamePanel.GameState == 4){
                GamePanel.GameState = 1;
            }
        }

    }
    @Override
    public void keyReleased(KeyEvent e) {
        keys[e.getKeyCode()] = false;

    }

}