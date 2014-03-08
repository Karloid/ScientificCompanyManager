package com.krld.manager.game.model.guns;

import com.krld.manager.game.model.guns.Gun;
import com.krld.manager.game.model.guns.PistolShootBehavior;

/**
 * Created by Andrey on 3/8/14.
 */
public class Ak47 extends Gun {

    public static final int START_BULLETS_COUNT = 30;

    public Ak47() {
        shootBehavior = new Ak47ShootBehavior();
        addBullets(START_BULLETS_COUNT);

    }



}
