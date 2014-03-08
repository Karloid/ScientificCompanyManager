package com.krld.manager.game.model.guns;

import com.krld.manager.game.Game;
import com.krld.manager.game.model.Player;
import com.krld.manager.game.model.Point;

/**
 * Created by Andrey on 3/8/14.
 */
public class Ak47ShootBehavior extends PistolShootBehavior {
    private static final long STANDART_AK47_COOLDOWN = 100;
    private static final int THRESHOLD = 30;

    public Ak47ShootBehavior() {
        super();
        setCooldownTime(STANDART_AK47_COOLDOWN);
    }

    @Override
    public void shoot(Player player) {
        if (player.gunIsEmpty()) {
            return;
        }
        Game context = player.getContext();
        Point point = player.getActionPosition();

        int correctedX = (int) Math.round(Math.random() * THRESHOLD - THRESHOLD / 2);
        int correctedY = (int) Math.round(Math.random() * THRESHOLD - THRESHOLD / 2);
        context.getBullets().add(new Bullet(player, context, point.getX() + correctedX, point.getY() + correctedY));
        player.getGun().removeBullet(1);
    }
}
