package com.krld.manager.game;

/**
 * Created by Andrey on 2/15/14.
 */
public class Player {
    private int id;

    public Player() {
        id = Game.getNextId();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
