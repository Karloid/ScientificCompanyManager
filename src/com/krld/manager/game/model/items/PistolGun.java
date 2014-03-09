package com.krld.manager.game.model.items;

/**
 * Created by Andrey on 3/8/14.
 */
public class PistolGun extends Gun {
    private static final String NAME = "PISTOL_GUN";
    private static final int START_BULLETS_COUNT = 500;

    public PistolGun() {
        shootBehavior = new PistolShootBehavior();
        addBullets(START_BULLETS_COUNT);
    }

    @Override
    public String getName() {
        return NAME;
    }
}
