package com.krld.manager.game;

/**
 * Created by Andrey on 2/14/14.
 */
public class Game {
    private static final int WIDTH = 10;
    private static final int HEIGHT = 10;
    private int[][] tiles;

    public Game() {
        tiles = new int[WIDTH][HEIGHT];
        for (int x = 0; x < WIDTH; x++) {
            for (int y = 0; y < HEIGHT; y++) {

                if (Math.random() > 0.5f) {
                    tiles[x][y] = TileTypes.GRASS1;
                } else
                    tiles[x][y] = TileTypes.GRASS2;
            }
        }
    }

    public int[][] getTiles() {
        return tiles;
    }

    public void setTiles(int[][] tiles) {
        this.tiles = tiles;
    }
}
