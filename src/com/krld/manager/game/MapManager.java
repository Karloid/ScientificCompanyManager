package com.krld.manager.game;

import com.google.gson.Gson;

import java.util.*;
import java.util.stream.Collectors;


/**
 * Created by Andrey on 2/18/14.
 */
public class MapManager {
    private boolean inited = false;
    private ArrayList<TileType> tileTypes;

    public int[][] getRandomizeTiles(int width, int height) {
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

        createRandomSpawns(width, height, tiles, 5);

        addHorizontalWall(width, height, tiles, 0, 0, 4);

        return tiles;
    }

    private void addHorizontalWall(int width, int height, int[][] tiles, int x, int y, int count) {
        for (int i = 0; i <= count; i++) {
            tiles[x + i][y] = getTileTypeByName("WALL_GRASS_HOR1").getId();
        }
    }

    private void createRandomSpawns(int width, int height, int[][] tiles, int countSpawns) {
        int countCreatedSpawn = 0;
        int x;
        int y;
        while (countCreatedSpawn < countSpawns) {
            x = (int) (Math.random() * width);
            y = (int) (Math.random() * height);
            if (tiles[x][y] == getTileTypeByName("GRASS1").getId() ||
                    tiles[x][y] == getTileTypeByName("GRASS2").getId()) {
                tiles[x][y] = getTileTypeByName("SPAWN1").getId();
                countCreatedSpawn++;
            }
        }
    }

    private void init() {
        loadTiles();
        inited = true;
    }

    private void loadTiles() {
        tileTypes = new ArrayList<>();
        String jsonString = Utils.readFile("tileTypes.json");
        Map root = new Gson().fromJson(jsonString, Map.class);
        List<Map<String, Object>> tiles = (List<Map<String, Object>>) root.get("tiles");
        tileTypes.addAll(tiles.stream().map(tile -> new TileType((int) Math.round((Double) tile.get("id")),
                (String) tile.get("name"), (String) tile.get("texture"), (Collection<String>) tile.get("tags"))).collect(Collectors.toList()));
        // System.out.println("tile types: " + tileTypes);

    }

    @Deprecated
    public void addDirtBlock(int x, int y, int[][] tiles) {
        tiles[x][y] = getTileTypeByName("GRASS_TO_DIRT_CORNER_LT1").getId();
        tiles[x + 1][y] = getTileTypeByName("GRASS_TO_DIRT_HOR1").getId();
        tiles[x + 2][y] = getTileTypeByName("GRASS_TO_DIRT_HOR1").getId();
        tiles[x + 3][y] = getTileTypeByName("GRASS_TO_DIRT_CORNER_RT1").getId();
        tiles[x + 3][y + 1] = getTileTypeByName("DIRT_TO_GRASS_VER1").getId();
        tiles[x + 3][y + 2] = getTileTypeByName("DIRT_TO_GRASS_VER1").getId();
        tiles[x + 3][y + 3] = getTileTypeByName("GRASS_TO_DIRT_CORNER_RB1").getId();
        tiles[x + 2][y + 3] = getTileTypeByName("DIRT_TO_GRASS_HOR1").getId();
        tiles[x + 1][y + 3] = getTileTypeByName("DIRT_TO_GRASS_HOR1").getId();
        tiles[x][y + 3] = getTileTypeByName("GRASS_TO_DIRT_CORNER_LB1").getId();
        tiles[x][y + 2] = getTileTypeByName("GRASS_TO_DIRT_VER1").getId();
        tiles[x][y + 1] = getTileTypeByName("GRASS_TO_DIRT_VER1").getId();
        tiles[x + 1][y + 1] = getTileTypeByName("DIRT1").getId();
        ;
        tiles[x + 2][y + 1] = getTileTypeByName("DIRT1").getId();
        ;
        tiles[x + 1][y + 2] = getTileTypeByName("DIRT2").getId();
        ;
        tiles[x + 2][y + 2] = getTileTypeByName("DIRT2").getId();
        ;
    }

    public TileType getTileTypeByName(String name) {
        for (TileType tileType : tileTypes) {
            if (tileType.getName().equals(name)) {
                return tileType;
            }
        }
        return null;
    }

    public boolean haveTag(int id, String tag) {
        TileType tileType = getTileTypeById(id);
        for (String str : tileType.getTags()) {
            if (str.equals(tag)) {
                return true;
            }
        }
        return false;
    }

    private TileType getTileTypeById(int id) {
        for (TileType tileType : tileTypes) {
            if (tileType.getId() == id) {
                return tileType;
            }
        }
        return null;
    }

    public int[][] loadMapFromFile(String fileName) {
        if (!inited) {
            init();
        }
        String jsonMapString = Utils.readFile("maps/" + fileName);
        Map root = new Gson().fromJson(jsonMapString, Map.class);
        System.out.println("DEBUG INFO: " + root.get("tiles").getClass());
        int width = (int) Math.round((double) root.get("width"));
        int height = (int) Math.round((double) root.get("height"));
        ArrayList<ArrayList<Double>> tilesArrayList = (ArrayList<ArrayList<Double>>) root.get("tiles");
        int[][] tiles = new int[width][height];
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                tiles[x][y] = (int)Math.round(tilesArrayList.get(x).get(y));
                System.out.print(" " + tiles[x][y]);
            }
            System.out.println();
        }
        return tiles;
    }
}
