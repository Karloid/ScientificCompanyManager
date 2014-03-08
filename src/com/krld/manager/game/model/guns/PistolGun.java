package com.krld.manager.game.model.guns;

/**
 * Created by Andrey on 3/8/14.
 */
public class PistolGun extends Gun {
    public PistolGun() {
        shootBehavior = new PistolShootBehavior();
    }
}
