package com.example.bomberman.Entities.Character.Enemy;

import com.example.bomberman.Entities.Character.Bomber.Bomber;
import com.example.bomberman.Entities.Character.Bomber.Boom;
import com.example.bomberman.Entities.Character.Enemy.AI.AI;
import com.example.bomberman.Entities.Character.Enemy.AI.AIMedium;
import com.example.bomberman.Entities.Object;
import com.example.bomberman.GamePanel;

public class DuyNgo extends Enemies {
    GamePanel gamePanel;
    Bomber bomber;
    protected int directionDuyNgo;
    protected AI ai;

    public DuyNgo(int _x, int _y, Bomber bomber, GamePanel gamePanel, Boom boom) {
        this.bomber=bomber;
        this.gamePanel = gamePanel;
        setDefaultValues();
        sprites.getDuyNgoImage();
        ai = new AIMedium(bomber, this, boom);
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
            directionDuyNgo = ai.calculateDirection();
        }
        HandlePosition(directionDuyNgo);
        gamePanel.checkCollision.checkDieEnemy(bomber,this);
    }
}
