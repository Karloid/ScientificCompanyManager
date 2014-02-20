package com.krld.manager.game;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


/**
 * Created by Andrey on 2/18/14.
 */
public class MapManager {
    private  boolean inited = false;
    private  ArrayList<TileType> tileTypes;

    public  int[][] getRandomizeTiles(int width, int height) {
        if (!inited) {
            init();
        }
        int[][] tiles = new int[width][height];
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                double random = Math.random();
                if (random > 0.5f) {
                    tiles[x][y] = getTileTypeByName("GRASS1").getId();
                } else if (random > 0.0f) {
                    tiles[x][y] = getTileTypeByName("GRASS2").getId();
                }
            }
        }
        addDirtBlock(3, 6, tiles);
        addDirtBlock(12, 10, tiles);
        addDirtBlock(7, 1, tiles);
        addDirtBlock(25, 13, tiles);
        return tiles;
    }

    private  void init() {
        loadTiles();
        inited = true;
    }

    private  void loadTiles() {
        tileTypes = new ArrayList<TileType>();
        String jsonString = Utils.readFile("tileTypes.json");
        Map root = new Gson().fromJson(jsonString, Map.class);
        List<Map<String, Object>> tiles = (List<Map<String, Object>>) root.get("tiles");
        for (Map<String, Object> tile : tiles) {
            tileTypes.add(new TileType((int) Math.round((Double) tile.get("id")), (String) tile.get("name"), (String) tile.get("texture")));
        }
       // System.out.println("tile types: " + tileTypes);

    }



    public void addDirtBlock(int x, int y, int[][] tiles) {
        tiles[x][y] = getTileTypeByName("GRASS_TO_DIRT_CORNER_LT1").getId();
        tiles[x + 1][y] = getTileTypeByName("GRASS_TO_DIRT_HOR1").getId();
        tiles[x + 2][y] = getTileTypeByName("GRASS_TO_DIRT_HOR1").getId();
        tiles[x + 3][y] = getTileTypeByName("GRASS_TO_DIRT_CORNER_RT1").getId();
        tiles[x + 3][y + 1] =getTileTypeByName("DIRT_TO_GRASS_HOR1").getId();
        tiles[x + 3][y + 2] = getTileTypeByName("DIRT_TO_GRASS_HOR1").getId();
        tiles[x + 3][y + 3] =  getTileTypeByName("GRASS_TO_DIRT_CORNER_RB1").getId();
        tiles[x + 2][y + 3] = getTileTypeByName("DIRT_TO_GRASS_VERT1").getId();
        tiles[x + 1][y + 3] = getTileTypeByName("DIRT_TO_GRASS_VERT1").getId();
        tiles[x][y + 3] = getTileTypeByName("GRASS_TO_DIRT_CORNER_LB1").getId();
        tiles[x][y + 2] = getTileTypeByName("GRASS_TO_DIRT_VER1").getId();
        tiles[x][y + 1] = getTileTypeByName("GRASS_TO_DIRT_VER1").getId();
        tiles[x + 1][y + 1] = getTileTypeByName("DIRT1").getId(); ;
        tiles[x + 2][y + 1] = getTileTypeByName("DIRT1").getId(); ;
        tiles[x + 1][y + 2] = getTileTypeByName("DIRT2").getId(); ;
        tiles[x + 2][y + 2] = getTileTypeByName("DIRT2").getId(); ;
    }

    private TileType getTileTypeByName(String name) {
        for (TileType tileType : tileTypes) {
            if (tileType.getName().equals(name)) {
                return tileType;
            }
        }
        return null;
    }

}
