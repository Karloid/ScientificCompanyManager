package com.krld.manager.game.model.items;

import com.krld.manager.game.Game;
import com.krld.manager.game.model.characters.Unit;

/**
 * Created by Andrey on 3/8/14.
 */
public class ItemSpawn extends Unit {
    private final ItemContainer itemToSpawn;

    public ItemSpawn(int x, int y, Game game, ItemContainer itemContainer) {
        super(x, y, game);
        itemToSpawn = itemContainer;
    //    itemToSpawn.getPosition().setX(x);
   //     itemToSpawn.getPosition().setY(y);
        spawnItemContainer();
    }

    private void spawnItemContainer() {

        try {
            getContext().addNewItemContainer(new ItemContainer(getContext(), itemToSpawn.getItem().getClass().newInstance(),
                    itemToSpawn.getSpriteId(), getPosition().getX(), getPosition().getY()));
        } catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update() {

    }

    //TODO realize item drop/pick
}
