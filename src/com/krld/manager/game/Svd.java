package com.krld.manager.game;

import com.krld.manager.game.model.items.MyItem;
import com.krld.manager.game.model.items.PistolGun;

/**
 * Created by Andrey on 3/9/14.
 */
public class Svd extends PistolGun {
    private static final String NAME = "SVD";

    @Override
    public String getName() {
        return NAME;
    }
}
