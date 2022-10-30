package com.example.bomberman.Entities.Character.Enemy.AI;

import com.example.bomberman.Entities.Character.Bomber.Bomber;
import com.example.bomberman.Entities.Character.Bomber.Boom;
import com.example.bomberman.Entities.Character.Enemy.Broom;

public class AIMedium extends AI {

    Bomber bomber;

    Boom boom;
    Broom _broom;


    public AIMedium(Bomber bomber, Broom broom, Boom boom) {
        this.bomber = bomber;
        this.boom = boom;
        this._broom = broom;
    }

    public int calculateDirection() {
        if (bomber == null) return random.nextInt(4);
        return findDirection();
    }

    public int calculateDirectionRandom() {
        int i;
        i = random.nextInt(4);
        return i;
    }

    public int findDirection() {
        int way = random.nextInt(10);
        if (way % 2 == 0) {
            int _dir = dicrectionRow();
            if (_dir != - 1) {
                return _dir;
            }
            return dicrectionCol();
        } else {
            int _dir = dicrectionCol();
            if (_dir != - 1) {
                return _dir;
            }
            return dicrectionRow();
        }
    }

    public int dicrectionRow() {
        if (bomber.x > _broom.x) return 1;
        if (bomber.x < _broom.x) return 3;
        return -1;
    }
    public int dicrectionCol(){
        if (bomber.y < _broom.y) return 0;
        if (bomber.y > _broom.y) return 2;
        return -1;
    }
}