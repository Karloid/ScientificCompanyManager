package com.krld.manager.game.model;

import com.krld.manager.game.Game;
import com.krld.manager.game.Utils;

/**
 * Created by Andrey on 2/16/14.
 */
public abstract class AbstractBullet extends Unit {
    private final int targetX;
    private final int targetY;
    private final Player owner;
    public static final int SPEED = 90;
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
        this.angle = Utils.getAngleTo(getPosition().getX(), getPosition().getY(), targetX, targetY);

    }

    @Override
    public void update() {
        updatePosition();
        if (collideWithPlayers()) {
            return;
        }
        if (collideWithPlayers()) {
            return;
        }
        if (
                checkCollideWithObjects()) {
            return;
        }
        checkIsInTarget();
    }

    private boolean checkCollideWithObjects() {
        if (!getContext().isPenetrablePlace(getPosition().getX(), getPosition().getY())) {
            setDead(true);
            return true;
        }
        return false;
    }

    private boolean collideWithPlayers() {
        int x = getPosition().getX();
        int y = getPosition().getY();
        int delta = Game.CELL_SIZE / 2;
        for (Player player : getContext().getPlayers()) {
            if (player != owner) {
                if (Math.abs(player.getPosition().getX() - x) <= delta &&
                        Math.abs(player.getPosition().getY() - y) <= delta) {
                    player.takeDamage(getDamage());
                    setDead(true);
                    return true;
                }
            }
        }
        return false;
    }

    abstract int getDamage();

    private void checkIsInTarget() {
      /*  if (Utils.getDistanceTo(getPosition().getX(), getPosition().getY(), targetX, targetY) <= DEAD_DISTANCE) {
            System.out.println("bullet is dead!");
            setDead(true);
        }*/
        if (getPosition().getX() == targetX && getPosition().getY() == targetY) {
            setDead(true);
        }
    }

    private void updatePosition() {
        if (Utils.getDistanceTo(getPosition().getX(), getPosition().getY(), targetX, targetY) < speed) {
            getPosition().setX(targetX);
            getPosition().setY(targetY);
        } else {
            this.angle = Utils.getAngleTo(getPosition().getX(), getPosition().getY(), targetX, targetY);
            getPosition().setX((int) (getPosition().getX() + speed * Math.cos(angle)));
            getPosition().setY((int) (getPosition().getY() + speed * Math.sin(angle)));
        }
    }

    public void setDead(boolean dead) {
        this.dead = dead;
    }

    public boolean isDead() {
        return dead;
    }
}
