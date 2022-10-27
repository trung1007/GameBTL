package com.example.bomberman;

import com.example.bomberman.Entities.*;
import com.example.bomberman.Entities.Character.Bomber.Bomber;
import com.example.bomberman.Entities.Character.Bomber.Boom;
import com.example.bomberman.Entities.Character.Enemy.Balloon;
import com.example.bomberman.Entities.Character.Enemy.DuyNgo;
import com.example.bomberman.Entities.Character.Enemy.Frog;
import com.example.bomberman.Entities.Object;
import com.example.bomberman.Menu.MenuUI;
import com.example.bomberman.graphics.UI;
import com.example.bomberman.input.Keyboard;
import com.example.bomberman.input.Mouse;
import static com.example.bomberman.Entities.Character.Bomber.Bomber.booms;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class GamePanel extends JPanel implements Runnable {
    public static final int DEFAULT_SIZE = 16;
    public static final int SCALE = 3;
    public static final int SCALED_SIZE = DEFAULT_SIZE * SCALE;
    public static final int MAX_SCREEN_COL = 22;
    public static final int MAX_SCREEN_ROW = 16;
    public static final int SCREEN_WIDTH = SCALED_SIZE * MAX_SCREEN_COL;
    public static final int SCREEN_HEIGHT = SCALED_SIZE * MAX_SCREEN_ROW;
    double FPS = 60;
    public final double ns = 1000000000.0 / FPS;
    public static int GameState = 1;
    public Thread gameThread;
    Keyboard keyboard = new Keyboard();
    Mouse mouse = new Mouse(this);

    public Bomber bomber = new Bomber(this, keyboard);

    public Boom boom = new Boom(this, bomber);
    public Balloon balloon1 = new Balloon(48 * 10, 48 * 6, bomber, this, boom);
    public Balloon balloon2 = new Balloon(48 * 20, 48 * 9, bomber, this, boom);
    public DuyNgo duyNgo1 = new DuyNgo(48* 10 , 48*12 , bomber, this, boom);
    public DuyNgo duyNgo2 = new DuyNgo(48* 18 , 48*12 , bomber, this, boom);

    public Frog frog1 = new Frog(48* 7 , 48*10 , bomber, this, boom);
    public Frog frog2 = new Frog(48* 13 , 48*8 , bomber, this, boom);

    Object object = new Object(this);
    UI ui = new UI(this);
    MenuUI menuUI = new MenuUI(this, mouse);
    public CheckCollision checkCollision = new CheckCollision(this, boom);

    public GamePanel() {
        this.setPreferredSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT));
        this.setDoubleBuffered(true);
        this.addKeyListener(keyboard);
        this.addMouseListener(mouse);
        this.setFocusable(true);
    }

    public void start() {
        gameThread = new Thread(this);
        gameThread.start();

    }

    @Override
    public void run() {
        long timer = 0;
        long drawCount = 0;
        long lastTime = System.nanoTime();
        double delta = 0;
        long now;
        while (gameThread != null) {
            now = System.nanoTime();
            delta += (now - lastTime) / ns;
            timer += (now - lastTime);
            lastTime = now;
            while (delta >= 0) {
                update();
                repaint();
                delta--;
                drawCount++;
            }
            if (timer >= 1000000000) {
                drawCount = 0;
                timer = 0;
            }
        }
    }

    public void update() {
        bomber.update(object);
        balloon1.update(object);
        balloon2.update(object);

        duyNgo1.update(object);
        duyNgo2.update(object);

        frog1.update(object);
        frog2.update(object);
        for(int i = 0; i < booms.size();i++){
            booms.get(i).update(bomber);
        }

        menuUI.update();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        if (GamePanel.GameState == 0) {
            object.render(g2);
            bomber.render(g2, "Bomber");

            balloon1.render(g2, "Balloon");
            balloon2.render(g2, "Balloon");

            duyNgo1.render(g2, "DuyNgo");
            duyNgo2.render(g2, "DuyNgo");

            frog1.render(g2, "Frog");
            frog2.render(g2, "Frog");


            for (int i = 0; i < booms.size(); i++) {
                booms.get(i).render(g2, "boom",this);
            }
            ui.draw(g2);

        }
        if (GamePanel.GameState == 1) {
            menuUI.render(g2);
        }
        if(GamePanel.GameState==2){
            menuUI.GameOverWindow();
            gameThread=null;
        }
        if (GameState == 3) {
            gameThread = null;
            System.exit(1);
        }
        g2.dispose();
    }
}


