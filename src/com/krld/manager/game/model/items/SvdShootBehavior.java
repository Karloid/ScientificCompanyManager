package com.krld.manager.game.model.items;

import com.krld.manager.game.Game;
import com.krld.manager.game.model.Point;
import com.krld.manager.game.model.characters.Player;
import com.sun.jndi.url.corbaname.corbanameURLContextFactory;

/**
 * Created by Andrey on 3/10/14.
 */
public class SvdShootBehavior extends PistolShootBehavior {
    private static final long STANDART_SVD_COOLDOWN = 1000;
    private static final int THRESHOLD = 10;
    private long cooldownTime;

    public SvdShootBehavior() {
        setCooldownTime(STANDART_SVD_COOLDOWN);
    }

    @Override
    public void shoot(Player player) {
        if (!readyToSoot()) {
            return;
        }
        if (player.gunIsEmpty()) {
            return;
        }
        Game context = player.getContext();
        Point point = player.getActionPosition();

        int correctedX = (int) Math.round(Math.random() * getThreshold() - getThreshold() / 2);
        int correctedY = (int) Math.round(Math.random() * getThreshold() - getThreshold() / 2);

        context.getBullets().add(new SvdBullet(player, context, point.getX() + correctedX, point.getY() + correctedY));
        player.getGun().removeBullet(1);
    }


    @Override
    public long getCooldownTime() {
        return cooldownTime;
    }

    @Override
    public void setCooldownTime(long cooldownTime) {
        this.cooldownTime = cooldownTime;
    }

    @Override
    public int getThreshold() {
        return THRESHOLD;
    }
}
