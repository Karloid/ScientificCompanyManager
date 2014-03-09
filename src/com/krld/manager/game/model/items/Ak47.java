package com.krld.manager.game.model.items;

/**
 * Created by Andrey on 3/8/14.
 */
public class Ak47 extends Gun {

    public static final int START_BULLETS_COUNT = 30;
    private static final String NAME = "AK47";

    public Ak47() {
        shootBehavior = new Ak47ShootBehavior();
        addBullets(START_BULLETS_COUNT);
    }

    @Override
    public String getName() {
        return NAME;
    }
}
