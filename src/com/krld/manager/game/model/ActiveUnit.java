package com.krld.manager.game.model;

import com.krld.manager.game.Game;

/**
 * Created by Andrey on 2/15/14.
 */
public abstract class ActiveUnit extends Unit {
    private double speed;
    private boolean moveUp;
    private boolean moveRight;
    private boolean moveLeft;
    private boolean moveDown;

    public ActiveUnit(int x, int y, Game game) {
        super(x, y, game);
    }

    public void update() {

        updatePosition();
    }

    private void updatePosition() {
        if (!isMoveDown() && !isMoveLeft() && !isMoveRight() && !isMoveUp()) {
            return;
        }
        double angle = 0;
        if (isMoveRight() && isMoveDown()) {
            angle = 45;
        } else if (isMoveLeft() && isMoveDown()) {
            angle = 135;
        }else if (isMoveLeft() && isMoveUp()) {
            angle = 225;
        }else if (isMoveRight() && isMoveUp()) {
            angle = 315;
        }else if (isMoveRight()) {
            angle = 0;
        }else if (isMoveDown()) {
            angle = 90;
        }else if (isMoveLeft()) {
            angle = 180;
        }else if (isMoveUp()) {
            angle = 270;
        }

        getPosition().setX((int) (getPosition().getX() + speed * Math.cos(angle / 180 * Math.PI)));
        getPosition().setY((int) (getPosition().getY() + speed * Math.sin(angle / 180 * Math.PI)));
    }

    public double getSpeed() {
        return speed;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }

    public void setMoveUp(boolean moveUp) {
        this.moveUp = moveUp;
    }

    public boolean isMoveUp() {
        return moveUp;
    }

    public void setMoveRight(boolean moveRight) {
        this.moveRight = moveRight;
    }

    public boolean isMoveRight() {
        return moveRight;
    }

    public void setMoveLeft(boolean moveLeft) {
        this.moveLeft = moveLeft;
    }

    public boolean isMoveLeft() {
        return moveLeft;
    }

    public void setMoveDown(boolean moveDown) {
        this.moveDown = moveDown;
    }

    public boolean isMoveDown() {
        return moveDown;
    }

    public abstract void action(int x, int y);
}
