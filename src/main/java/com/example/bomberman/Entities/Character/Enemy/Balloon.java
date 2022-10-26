package com.example.bomberman.Entities.Character.Enemy;

import com.example.bomberman.Entities.Character.Bomber.Bomber;
import com.example.bomberman.Entities.Character.Bomber.Boom;
import com.example.bomberman.Entities.Object;
import com.example.bomberman.Entities.Character.Enemy.AI.AI;
import com.example.bomberman.Entities.Character.Enemy.AI.AILow;
import com.example.bomberman.GamePanel;

public class Balloon extends Enemies {
    GamePanel gamePanel;
    Bomber bomber;
    protected int directionBalloon;
    protected AI ai;

    public Balloon(int _x, int _y, Bomber bomber, GamePanel gamePanel, Boom boom) {
        this.bomber=bomber;
        this.gamePanel = gamePanel;
        setDefaultValues();
        sprites.getBalloonImage();
        ai = new AILow();
        x = _x;
        y = _y;
    }

    @Override
    public void setDefaultValues() {
        speed = 3;
    }

    @Override
    public void update(Object object) {
        if(CheckDie){
            x=-1;
            y=-1;
        }
        collisionOn = false;
        gamePanel.checkCollision.checkTile(this);
        if (collisionOn == true) {
            directionBalloon = ai.calculateDirection();
        }
        HandlePosition(directionBalloon);
        gamePanel.checkCollision.checkDieEnemy(bomber,this);
    }
}
