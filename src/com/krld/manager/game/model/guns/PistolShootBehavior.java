package com.krld.manager.game.model.guns;

import com.krld.manager.game.Game;
import com.krld.manager.game.model.Player;
import com.krld.manager.game.model.Point;

/**
 * Created by Andrey on 3/8/14.
 */
public class PistolShootBehavior implements ShootBehavior {
    public static final int STANDART_PISTOL_COOLDOWN = 400;
    private long lastTimeShot;
    private long cooldownTime;

    public PistolShootBehavior() {
        lastTimeShot = System.currentTimeMillis();
        cooldownTime = STANDART_PISTOL_COOLDOWN;
    }

    @Override
    public void shoot(Player player) {
        Game context = player.getContext();
        Point point = player.getActionPosition();
        context.getBullets().add(new Bullet(player, context, point.getX(), point.getY()));
    }

    @Override
    public boolean readyToSoot() {
        long currentTime = System.currentTimeMillis();
        if (currentTime - lastTimeShot > getCooldownTime()) {
            lastTimeShot = currentTime;
            return true;
        }
        return false;
    }

    @Override
    public long getCooldownTime() {
        return cooldownTime;
    }

    @Override
    public void setCooldownTime(long cooldownTime) {
        this.cooldownTime = cooldownTime;
    }
}
