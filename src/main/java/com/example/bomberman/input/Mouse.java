package com.example.bomberman.input;

import com.example.bomberman.GamePanel;
import com.example.bomberman.Menu.MenuOption;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;

public class Mouse implements MouseListener {
    GamePanel gp;

    public Mouse(GamePanel gamePanel) {
        this.gp = gamePanel;
    }
    public void HelpWindow(){
        JFrame frameHelp=new JFrame("Help!");
        try {
            frameHelp.setContentPane(new JLabel(new ImageIcon(ImageIO.read(getClass().getResourceAsStream("/sprites/Menu_Help1.jpg")))));
        } catch (IOException e) {
            e.printStackTrace();
        }
        frameHelp.setLocationRelativeTo(null);
        frameHelp.setLocation(257,70);
        frameHelp.pack();
        frameHelp.setVisible(true);
    }


    @Override
    public void mouseClicked(MouseEvent e) {
        int mx = e.getX();
        int my = e.getY();
        if (GamePanel.GameState == 0) {
            if (mx >= 96 && mx <= 96 + 196) {
                if (my >= 192 && my <= 192 + 48) {
                    GamePanel.GameState = 1;
                }

            }
            if (mx >= 96 && mx <= 96 + 196) {
                if (my >= 192 + 48 && my <= 192 + 48 + 48) {
                    HelpWindow();
                }

            }
            if (mx >= 96 && mx <= 96 + 196) {
                if (my >= 192 + 96 && my <= 192 + 48 + 96) {
                    GamePanel.GameState=3;
                }

            }
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
        int mx = e.getX();
        int my = e.getY();
        if (GamePanel.GameState == 0) {
            if (mx >= 96 && mx <= 96 + 196) {
                if (my >= 192 && my <= 192 + 48) {
                    MenuOption.setColorNum = 2;
                }
            }
            if (mx >= 96 && mx <= 96 + 196) {
                if (my >= 192 + 48 && my <= 192 + 48 + 48) {
                    MenuOption.setColorNum = 2;
                }

            }
            if (mx >= 96 && mx <= 96 + 196) {
                if (my >= 192 + 96 && my <= 192 + 48 + 96) {
                    MenuOption.setColorNum = 2;
                }

            }
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        int mx = e.getX();
        int my = e.getY();
        if (GamePanel.GameState == 0) {
            if (mx < 96 || mx > 96 + 196) {
                if (my < 192 || my > 192 + 48) {
                    MenuOption.setColorNum = 1;
                }
            }
            if (mx < 96 || mx > 96 + 196) {
                if (my < 192 + 48 || my > 192 + 48 + 48) {
                    MenuOption.setColorNum = 1;
                }

            }
            if (mx < 96 || mx > 96 + 196) {
                if (my < 192 + 96 || my > 192 + 48 + 96) {
                    MenuOption.setColorNum = 1;
                }

            }
        }
    }
    @Override
    public void mouseEntered(MouseEvent e) {
    }
    @Override
    public void mouseExited(MouseEvent e) {
    }
}
