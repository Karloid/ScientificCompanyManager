package com.krld.manager.game.model.items;

import com.krld.manager.game.model.characters.Player;

/**
 * Created by Andrey on 3/8/14.
 */
public abstract class Gun extends MyItem {
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

    public int getBulletsCount() {
        return bulletsCount;
    }

    public abstract String getName();
}
