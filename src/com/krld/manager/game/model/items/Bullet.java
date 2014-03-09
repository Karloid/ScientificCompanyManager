package com.krld.manager.game.model.items;

import com.krld.manager.game.Game;
import com.krld.manager.game.model.characters.Player;

/**
 * Created by Andrey on 2/16/14.
 */
public class Bullet extends AbstractBullet {
    private static final int DAMAGE = 20;

    public Bullet(Player owner, Game context, int x, int y) {
        super(owner,context, x, y);
    }

    @Override
    int getDamage() {
        return DAMAGE;
    }
}
