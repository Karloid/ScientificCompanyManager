package com.krld.manager.game;

/**
 * Created by Andrey on 2/15/14.
 */
public abstract class Unit {

    private Point position;
    private int id;

    public Unit(int x, int y) {
        id = Game.getNextId();
        position = new Point(x, y);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Point getPosition() {
        return position;
    }

    public void setPosition(Point position) {
        this.position = position;
    }
}
