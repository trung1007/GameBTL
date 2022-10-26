package com.example.bomberman.Entities.Character.Enemy.AI;


import com.example.bomberman.Entities.Character.Bomber.Bomber;
import com.example.bomberman.Entities.Character.Bomber.Boom;
import com.example.bomberman.Entities.Character.Enemy.Frog;

public class AIMedium2 extends AI {

    Bomber bomber;

    Boom boom;
    Frog _frog;


    public AIMedium2(Bomber bomber, Frog frog, Boom boom) {
        this.bomber = bomber;
        this.boom = boom;
        this._frog = frog;
    }

    public int calculateDirection() {
        if (bomber == null) return random.nextInt(4);
        return findDirection();
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
        if (bomber.x > _frog.x) return 1;
        if (bomber.x < _frog.x) return 3;
        return -1;
    }
    public int dicrectionCol(){
        if (bomber.y < _frog.y) return 0;
        if (bomber.y > _frog.y) return 2;
        return -1;
    }
}
