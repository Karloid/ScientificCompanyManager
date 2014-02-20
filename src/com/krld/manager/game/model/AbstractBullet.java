package com.krld.manager.game.model;

import com.krld.manager.game.Game;
import com.krld.manager.game.Utils;
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
    public static final int SPEED = 30;
    private static final Double DEAD_DISTANCE = Double.valueOf(SPEED);
    private final double speed;
    private double angle;
    private boolean dead;

    public AbstractBullet(Player owner, Game context, int x, int y) {
        super(owner.getPosition().getX(), owner.getPosition().getY(), context);
        dead = false;
        targetX = x;
        targetY = y;
        this.owner = owner;
        this.speed = SPEED * context.getSpeedRatio();
        this.angle = Utils.getAngleTo(owner.getPosition().getX(), owner.getPosition().getY(), x, y);

    }

    @Override
    public void update() {
        updatePosition();
        checkIsDead();
    }

    private void checkIsDead() {
        if (Utils.getDistanceTo(getPosition().getX(), getPosition().getY(), targetX, targetY) <= DEAD_DISTANCE) {
            System.out.println("bullet is dead!");
            setDead(true);
        }
    }

    private void updatePosition() {
        getPosition().setX((int) (getPosition().getX() + speed * Math.cos(angle / 180 * Math.PI)));
        getPosition().setY((int) (getPosition().getY() + speed * Math.sin(angle / 180 * Math.PI)));
    }

    public void setDead(boolean dead) {
        this.dead = dead;
    }

    public boolean isDead() {
        return dead;
    }
}
