package com.krld.manager.game.model;

import com.krld.manager.game.Game;

/**
 * Created by Andrey on 2/15/14.
 */
public abstract class Unit {

    public static final int MAX_HP = 100;
    private final Game context;
    private Point position;
    private int id;
    private int hp;

    public Unit(int x, int y, Game game) {
        this.context = game;
        id = game.getNextId();
        position = new Point(x, y);
        hp = MAX_HP;

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
    }


    public boolean isAlive() {
        return hp > 0;
    }

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }
}
