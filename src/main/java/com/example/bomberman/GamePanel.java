package com.example.bomberman;

import com.example.bomberman.Entities.Character.Bomber.Bomber;
import com.example.bomberman.Entities.Character.Bomber.Boom;
import com.example.bomberman.Entities.Character.Enemy.Balloon;
import com.example.bomberman.Entities.Character.Enemy.Broom;
import com.example.bomberman.Entities.Character.Enemy.Enemies;
import com.example.bomberman.Entities.Character.Enemy.Frog;
import com.example.bomberman.Entities.Object;
import com.example.bomberman.Menu.MenuUI;
import com.example.bomberman.Sound.Sound;
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
    public static final int MAX_SCREEN_COL = 27;
    public static final int MAX_SCREEN_ROW = 16;
    public static final int SCREEN_WIDTH = SCALED_SIZE * MAX_SCREEN_COL;
    public static final int SCREEN_HEIGHT = SCALED_SIZE * MAX_SCREEN_ROW;
    double FPS = 60;
    public final double ns = 1000000000.0 / FPS;
    public static int GameState = 1;
    public static int Level=1;
    public int NumOfBoss = 6;
    public Thread gameThread;
    public int Timer = 0;
    int TimerPause=0;
    Keyboard keyboard = new Keyboard();
    Mouse mouse = new Mouse(this);

    public Bomber bomber = new Bomber(this, keyboard);

    public ArrayList<Enemies> balloonList = new ArrayList<>();
    public ArrayList<Enemies> broomList = new ArrayList<>();
    public ArrayList<Enemies> frogList = new ArrayList<>();


    public Boom boom = new Boom(this, bomber);

    public Sound sound = new Sound();
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
        balloonList.add(new Balloon(48 * 25, 48 * 6, bomber, this, boom));
        balloonList.add(new Balloon(48 * 25, 48 * 9, bomber, this, boom));
        broomList.add(new Broom(48 * 25, 48 * 12, bomber, this, boom));
        broomList.add(new Broom(48 * 25, 48 * 11, bomber, this, boom));
        frogList.add(new Frog(48 * 25, 48 * 10, bomber, this, boom));
        frogList.add(new Frog(48 * 25, 48 * 8, bomber, this, boom));
    }

    public void start() {
        gameThread = new Thread(this);
        gameThread.start();

    }

    @Override
    public void run() {
        playMusic(0);
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
            while (delta >= 0 && !keyboard.pause) {
                    update();
                    repaint();
                    delta--;
                    drawCount++;
            }
            if (keyboard.pause){
                stopMusic();
            }
            if (timer >= 1000000000 && GameState == 0) {
                if(!keyboard.pause){
                    Timer++;
                }
                TimerPause++;
                if(TimerPause>5){
                    TimerPause=0;
                    playMusic(0);
                    keyboard.pause=false;
                }
                drawCount = 0;
                timer = 0;
            }
        }
    }

    public void update() {

        for (int i = 0; i < balloonList.size(); i++) {
            balloonList.get(i).update(object);
        }

        for (int i = 0; i < broomList.size(); i++) {
            broomList.get(i).update(object);
        }

        for (int i = 0; i < frogList.size(); i++) {
            frogList.get(i).update(object);
        }
        for (int i = 0; i < booms.size(); i++) {
            booms.get(i).update(bomber);
        }
        bomber.update(object);
        menuUI.update();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        if (GamePanel.GameState == 0) {
            object.render(g2);
            bomber.render(g2);
            for (int i = 0; i < balloonList.size(); i++) {
                balloonList.get(i).render(g2);
            }

            for (int i = 0; i < broomList.size(); i++) {
                broomList.get(i).render(g2);
            }

            for (int i = 0; i < frogList.size(); i++) {
                frogList.get(i).render(g2);
            }

            for (int i = 0; i < booms.size(); i++) {
                booms.get(i).render(g2, "boom", this);
            }
            ui.draw(g2);
            object.win();
        }
        if (GameState == 1) {
            menuUI.render(g2);
        }
        if (GameState == 2) {
            gameThread = null;
            menuUI.GameOverWindow();

        }
        if (GameState == 3) {
            gameThread = null;
            System.exit(1);
        }

        g2.dispose();
    }

    public void playMusic(int i) {
        sound.setFile(i);
        sound.play();
        sound.loop();
    }

    public void stopMusic() {
        sound.stop();
    }

    public void playSE(int i) {
        sound.setFile(i);
        sound.play();
    }
}