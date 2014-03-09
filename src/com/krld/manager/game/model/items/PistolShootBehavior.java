package com.krld.manager.game.model.items;

import com.krld.manager.game.Game;
import com.krld.manager.game.model.characters.Player;
import com.krld.manager.game.model.Point;

/**
 * Created by Andrey on 3/8/14.
 */
public class PistolShootBehavior implements ShootBehavior {
    public static final int STANDART_PISTOL_COOLDOWN = 600;
    private static final int THRESHOLD = 16;
    private long lastTimeShot;
    private long cooldownTime;

    public PistolShootBehavior() {
        lastTimeShot = System.currentTimeMillis();
        cooldownTime = STANDART_PISTOL_COOLDOWN;
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

        context.getBullets().add(new Bullet(player, context, point.getX() + correctedX, point.getY() + correctedY));
        player.getGun().removeBullet(1);
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

    @Override
    public int getThreshold() {
        return THRESHOLD;
    }
}
