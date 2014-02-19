package com.krld.manager.game;

/**
 * Created by Andrey on 2/19/14.
 */
public class TileType {

    private final int id;
    private final String name;
    private final String texture;

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getTexture() {
        return texture;
    }

    public TileType(int id, String name, String texture) {
        this.id = id;
        this.name = name;
        this.texture = texture;
    }

    @Override
    public String toString() {
        return "[id: " + id + ", name: " + name + ", texture: " + texture + " ]";
    }
}
