package com.krld.manager.game.model.characters;

import com.krld.manager.game.Game;
import com.krld.manager.game.Utils;
import com.krld.manager.game.model.Point;
import com.krld.manager.game.model.items.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Andrey on 2/15/14.
 */
public class Player extends ActiveUnit {

    public static final int SPEED = 10;
    private static final long STANDART_COOLDOWN = 400;
    private static final int PICK_UP_DISTANCE = 32;
    private final List<Gun> guns;
    private int spriteId;
    private long lastTimeShot;
    private ActionType action;
    private Point actionPosition;
    private Gun gun;


    public Player(int x, int y, Game game) {
        super(x, y, game);
        setSpeed(SPEED * game.getSpeedRatio());
        randomizeSpriteType();
        lastTimeShot = System.currentTimeMillis();
        guns = new ArrayList<Gun>();
        guns.add(new PistolGun());
        guns.add(new Ak47());
        gun = guns.get(1);

    }

    @Override
    void pickUpNearby() {
        List<ItemContainer> itemsContainer = getContext().getItemsContainer();
        ArrayList<ItemContainer> itemsContainersToRemove = null;
        for (ItemContainer itemContainer : itemsContainer) {
            if (Utils.getDistanceTo(getPosition().getX(), getPosition().getY(), itemContainer.getPosition().getX(),
                    itemContainer.getPosition().getY()) < PICK_UP_DISTANCE) {
                MyItem item = itemContainer.getItem();
                if (item instanceof Gun) {
                    Gun tmpGun = (Gun) item;
                    if (gunAlreadyStored(tmpGun)) {
                        getGunByName(tmpGun.getName()).addBullets(tmpGun.getBulletsCount());
                    } else {
                        guns.add(tmpGun);
                    }
                }
                if (itemsContainersToRemove == null) {
                    itemsContainersToRemove = new ArrayList<ItemContainer>();
                }
                itemsContainersToRemove.add(itemContainer);
            }
        }
        if (itemsContainersToRemove != null) {
            itemsContainer.removeAll(itemsContainersToRemove);
        }
    }

    private Gun getGunByName(String name) {
        for (Gun gun : guns) {
            if (gun.getName().equals(name)) {
                return gun;
            }
        }
        return null;
    }

    private boolean gunAlreadyStored(Gun gunToFind) {
        for (Gun gun : guns) {
            if (gun.getName().equals(gunToFind.getName())) {
                return true;
            }
        }
        return false;
    }

    @Override
    protected void applyAction() {
        if (action == ActionType.SHOOT) {
            applyShoot();
        } else {
            return;
        }
        setAction(null);
        setActionPosition(null);
    }

    private void applyShoot() {
        gun.perfomShoot(this);
    }

    private void randomizeSpriteType() {
        double random = Math.random();
        if (random > 0.75f) {
            spriteId = getContext().getMapManager().getTileTypeByName("SOLDIER1").getId();
        } else if (random > 0.5f) {
            spriteId = getContext().getMapManager().getTileTypeByName("SOLDIER2").getId();
        } else if (random > 0.25f) {
            spriteId = getContext().getMapManager().getTileTypeByName("SOLDIER3").getId();
        } else if (random > 0.0f) {
            spriteId = getContext().getMapManager().getTileTypeByName("SOLDIER4").getId();
        }
    }

    @Override
    public void action(int x, int y) {
        if (isAlive()) {
            shootTo(x, y);
        }
    }

    private void shootTo(int x, int y) {
        setAction(ActionType.SHOOT);
        setActionPosition(new Point(x, y));
    }

    private boolean readyToSoot() {
        long currentTime = System.currentTimeMillis();
        if (currentTime - lastTimeShot > getCooldownTime()) {
            lastTimeShot = currentTime;
            return true;
        }
        return false;
    }

    private long getCooldownTime() {
        return STANDART_COOLDOWN;
    }

    public int getSpriteId() {
        return spriteId;
    }

    public void setSpriteId(int spriteId) {
        this.spriteId = spriteId;
    }

    public void setAction(ActionType action) {
        this.action = action;
    }

    public ActionType getAction() {
        return action;
    }

    public void setActionPosition(Point actionPosition) {
        this.actionPosition = actionPosition;
    }

    public Point getActionPosition() {
        return actionPosition;
    }

    public boolean gunIsEmpty() {
        return gun.isEmpty();
    }

    public Gun getGun() {
        return gun;
    }

    public List<Gun> getGuns() {
        return guns;
    }

    public void changeGunByIndex(int i) {
        if (guns.get(i) != null) {
            gun = guns.get(i);
        }
    }
}
