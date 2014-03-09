package com.krld.manager.game.model.items;

import com.krld.manager.game.Game;
import com.krld.manager.game.model.Point;

/**
 * Created by Andrey on 3/9/14.
 */
public class ItemContainer {
    private final MyItem item;
    private final int spriteId;
    private final int id;
    private Point position;

    public ItemContainer(Game game, MyItem item, int spriteId, int x, int y) {
        this.item = item;
        this.spriteId = spriteId;
        position = new Point(x, y);
        id = game.getNextId();
    }

    public Point getPosition() {
        return position;
    }

    public void setPosition(Point position) {
        this.position = position;
    }

    public MyItem getItem() {
        return item;
    }

    public int getSpriteId() {
        return spriteId;
    }

    public int getId() {
        return id;
    }
}
