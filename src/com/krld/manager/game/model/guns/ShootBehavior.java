package com.krld.manager.game.model.guns;

import com.krld.manager.game.Game;
import com.krld.manager.game.model.Player;

/**
 * Created by Andrey on 3/8/14.
 */
public interface ShootBehavior {
    void shoot(Player player);

    boolean readyToSoot();

    long getCooldownTime();
    void setCooldownTime(long cooldownTime);
}
