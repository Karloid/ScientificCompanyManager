package com.krld.manager.game.model;

import com.krld.manager.game.Game;

/**
 * Created by Andrey on 2/16/14.
 */
public class Bullet extends AbstractBullet {
    public Bullet(Player owner, Game context, int x, int y) {
        super(owner,context, x, y);
    }
}
