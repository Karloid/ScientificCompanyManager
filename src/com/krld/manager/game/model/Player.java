package com.krld.manager.game.model;

import com.krld.manager.game.Game;

/**
 * Created by Andrey on 2/15/14.
 */
public class Player extends ActiveUnit {

    public Player(int x, int y, Game game) {
        super(x,y, game);
        setSpeed(10);

    }

    @Override
    public void action(int x, int y) {
        shootTo(x, y);
    }

    private void shootTo(int x, int y) {
        getContext().getBullets().add(new Bullet(this,getContext(), x,y));
    }
}
