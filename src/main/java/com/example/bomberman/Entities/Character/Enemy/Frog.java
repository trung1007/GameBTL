package com.example.bomberman.Entities.Character.Enemy;

import com.example.bomberman.Entities.Character.Bomber.Bomber;
import com.example.bomberman.Entities.Character.Bomber.Boom;
import com.example.bomberman.Entities.Character.Enemy.AI.AI;
import com.example.bomberman.Entities.Character.Enemy.AI.AIMedium2;
import com.example.bomberman.Entities.Object;
import com.example.bomberman.GamePanel;

public class Frog extends Enemies{
    GamePanel gamePanel;

    Bomber bomber;

    Boom boom;

    protected int directionFrog;

    protected AI ai;

    public Frog(int _x, int _y, Bomber bomber, GamePanel gamePanel, Boom boom){

        this.gamePanel = gamePanel;
        setDefaultValues();
        sprites.getFrogImage();
        ai = new AIMedium2(bomber, this , boom);
        this.bomber = bomber;
        x = _x;
        y = _y;
    }

    @Override
    public void setDefaultValues(){
        speed = 3;
    }
    @Override
    public void update(Object object){
        if(CheckDie){

        }
        if((bomber.x-this.x)*(bomber.x-this.x)
                + (bomber.y-this.y)*(bomber.y-this.y) <= 48*48*5*5) {
            speed = 5;
            directionFrog = ai.calculateDirection();
        } else {
            speed = 3;
        }
        if(collisionOn == true) {
            directionFrog = ai.calculateDirection();
        }
        collisionOn = false;
        gamePanel.checkCollision.checkTile(this);
        HandlePosition(directionFrog);
        gamePanel.checkCollision.checkDieEnemy(bomber,this);
    }

}


