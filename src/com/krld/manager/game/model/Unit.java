package com.krld.manager.game.model;

import com.krld.manager.game.Game;

/**
 * Created by Andrey on 2/15/14.
 */
public abstract class Unit {

    private static final int MAX_HP = 100;
    private final Game context;
    private Point position;
    private int id;
    private int hp;
    private boolean alive;

    public Unit(int x, int y, Game game) {
        this.context = game;
        id = game.getNextId();
        position = new Point(x, y);
        hp = MAX_HP;
        alive = true;

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

    public abstract void update();

    public void takeDamage(int damage) {
        hp -= damage;
        if (hp < 0) {
            hp = 0;
        }
        if (hp == 0) {
            setAlive(false);
        }
    }

    public void setAlive(boolean alive) {
        this.alive = alive;
    }

    public boolean isAlive() {
        return alive;
    }
}
