package com.krld.manager.game.model.items;

import com.krld.manager.game.Game;
import com.krld.manager.game.model.characters.Player;
import com.krld.manager.game.model.Point;

/**
 * Created by Andrey on 3/8/14.
 */
public class Ak47ShootBehavior extends PistolShootBehavior {
    private static final long STANDART_AK47_COOLDOWN = 100;

    public Ak47ShootBehavior() {
        super();
        setCooldownTime(STANDART_AK47_COOLDOWN);
    }
}
