package com.krld.manager.game.model;

import com.krld.manager.game.Game;

/**
 * Created by Andrey on 2/15/14.
 */
public class Player extends ActiveUnit {

    public static final int SPEED = 10;
    private int spriteType;

    public Player(int x, int y, Game game) {
        super(x, y, game);
        setSpeed(SPEED * game.getSpeedRatio());
        randomizeSpriteType();

    }

    private void randomizeSpriteType() {
        double random = Math.random();
        if (random > 0.75f) {
            spriteType = PlayerSpriteType.SOLDIER1;
        } else if (random > 0.5f) {
            spriteType = PlayerSpriteType.SOLDIER2;
        } else if (random > 0.25f) {
            spriteType = PlayerSpriteType.SOLDIER3;
        } else if (random > 0.0f) {
            spriteType = PlayerSpriteType.SOLDIER4;
        }
    }

    @Override
    public void action(int x, int y) {
        shootTo(x, y);
    }

    private void shootTo(int x, int y) {
        getContext().getBullets().add(new Bullet(this, getContext(), x, y));
    }

    public int getSpriteType() {
        return spriteType;
    }

    public void setSpriteType(int spriteType) {
        this.spriteType = spriteType;
    }
}
