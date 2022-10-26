package com.example.bomberman.Entities.Character.Enemy.AI;


import com.example.bomberman.GamePanel;

public class AILow extends AI {

    GamePanel gamePanel;

    public AILow() {
    }
    @Override
    public int calculateDirection() {
        int i;
        i = random.nextInt(4);
        return i;
    }


}


