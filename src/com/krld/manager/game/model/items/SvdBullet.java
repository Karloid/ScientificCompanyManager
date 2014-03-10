package com.krld.manager.game.model.items;

import com.krld.manager.game.Game;
import com.krld.manager.game.model.characters.Player;

/**
 * Created by Andrey on 3/10/14.
 */
public class SvdBullet extends AbstractBullet {
    private static final int DAMAGE = 55;

    public SvdBullet(Player owner, Game context, int x, int y) {
        super(owner,context, x, y);
        setSpeed(AbstractBullet.SPEED * 3);
    }

    @Override
    int getDamage() {
        return DAMAGE;
    }
}
