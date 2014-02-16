package com.krld.manager.game.model;

import com.krld.manager.game.Game;
import com.krld.manager.game.model.Player;
import com.krld.manager.game.model.Point;
import com.krld.manager.game.model.Unit;

/**
 * Created by Andrey on 2/16/14.
 */
public class AbstractBullet extends Unit {
    private final int targetX;
    private final int targetY;
    private final Player owner;

    public AbstractBullet(Player owner, Game context, int x, int y) {
        super(owner.getPosition().getX(), owner.getPosition().getY(), context);
        targetX = x;
        targetY = y;
        this.owner = owner;
    }

}
