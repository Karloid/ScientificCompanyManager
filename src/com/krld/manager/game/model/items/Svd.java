package com.krld.manager.game.model.items;

import com.krld.manager.game.model.items.Gun;
import com.krld.manager.game.model.items.MyItem;
import com.krld.manager.game.model.items.PistolGun;
import com.krld.manager.game.model.items.SvdShootBehavior;

/**
 * Created by Andrey on 3/9/14.
 */
public class Svd extends Gun {
    private static final String NAME = "SVD";
    private static final int START_BULLETS_COUNT = 10;

    public Svd() {
        shootBehavior = new SvdShootBehavior();
        addBullets(START_BULLETS_COUNT);
    }

    @Override
    public String getName() {
        return NAME;
    }
}
