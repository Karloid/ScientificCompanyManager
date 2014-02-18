package com.krld.manager.game;

import com.krld.manager.game.model.TileTypes;

/**
 * Created by Andrey on 2/18/14.
 */
public class MapManager {
    public static int[][] getRandomizeTiles(int width, int height) {
        int[][] tiles = new int[width][height];
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                double random = Math.random();
                if (random > 0.75f) {
                    tiles[x][y] = TileTypes.GRASS1;
                } else if (random > 0.5f) {
                    tiles[x][y] = TileTypes.GRASS2;
                } else if (random > 0.25f) {
                    tiles[x][y] = TileTypes.DIRT1;
                }else if (random > 0.0f) {
                    tiles[x][y] = TileTypes.DIRT2;
                }
            }
        }
        return tiles;
    }
}
