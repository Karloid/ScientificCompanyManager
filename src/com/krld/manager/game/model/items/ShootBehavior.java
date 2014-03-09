package com.krld.manager.game.model.items;

import com.krld.manager.game.model.characters.Player;

/**
 * Created by Andrey on 3/8/14.
 */
public interface ShootBehavior {
    void shoot(Player player);

    boolean readyToSoot();

    long getCooldownTime();
    void setCooldownTime(long cooldownTime);
    int getThreshold();
}
