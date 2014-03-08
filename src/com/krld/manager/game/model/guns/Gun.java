package com.krld.manager.game.model.guns;

import com.krld.manager.game.model.Player;

/**
 * Created by Andrey on 3/8/14.
 */
public abstract class Gun {
    protected ShootBehavior shootBehavior;
    private int bulletsCount;

    public void perfomShoot(Player player) {
        shootBehavior.shoot(player);
    }

    public boolean isEmpty() {
        return bulletsCount == 0;
    }

    public void removeBullet(int count) {
        bulletsCount -= count;
        if (bulletsCount < 0) {
            bulletsCount = 0;
        }
    }


    public void addBullets(int count) {
        bulletsCount += count;
    }
}
