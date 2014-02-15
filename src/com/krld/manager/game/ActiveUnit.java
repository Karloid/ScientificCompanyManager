package com.krld.manager.game;

/**
 * Created by Andrey on 2/15/14.
 */
public abstract class ActiveUnit extends Unit {
    private double speed;

    public ActiveUnit(int x, int y) {
        super(x, y);
    }

    public void update() {
        double angle = 45;
        getPosition().setX((int) (getPosition().getX() + speed * Math.cos(angle / 180 * Math.PI)));
        getPosition().setY((int) (getPosition().getY() + speed * Math.sin(angle / 180 * Math.PI)));
    }

    public double getSpeed() {
        return speed;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }
}
