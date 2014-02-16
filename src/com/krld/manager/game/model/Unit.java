package com.krld.manager.game.model;

import com.krld.manager.game.Game;

/**
 * Created by Andrey on 2/15/14.
 */
public abstract class Unit {

    private final Game context;
    private Point position;
    private int id;

    public Unit(int x, int y, Game game) {
        this.context = game;
        id = game.getNextId();
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

    public Game getContext() {
        return context;
    }

}
