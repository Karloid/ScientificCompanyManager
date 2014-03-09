package com.krld.manager.game;

import com.krld.manager.game.model.characters.Spawn;
import com.krld.manager.game.model.items.*;
import com.krld.manager.game.model.characters.ActiveUnit;
import com.krld.manager.game.model.characters.Player;
import com.krld.manager.game.model.characters.Unit;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Andrey on 2/14/14.
 */
public class Game {
    public static final int WIDTH = 30;
    public static final int HEIGHT = 20;
    public static final int CELL_SIZE = 32;
    public static final int PASSABLE_CELL_SIZE = 4;
    private int currentId = -1;
    private int[][] tiles;
    private int[][] passable;
    private int[][] penetrable;
    private List<Player> players;
    private List<AbstractBullet> bullets;
    private long delay = 100;
    private double speedRatio = delay / 300f;
    private List<Spawn> spawns;
    private MapManager mapManager;
    private List<ItemSpawn> itemSpawns;
    private MapAnalyzator mapAnalyzator;
    private List<ItemContainer> itemsContainers;

    public double getSpeedRatio() {
        return speedRatio;
    }

    public Game() {
        players = new ArrayList<Player>();
        bullets = new ArrayList<AbstractBullet>();
        itemsContainers = new ArrayList<ItemContainer>();

        initTiles();


        new Thread(new Runnable() {
            @Override
            public void run() {
                runGameLoop();
            }
        }).start();

    }


    private void runGameLoop() {
        while (true) {
            updateUnits();

            try {
                Thread.sleep(delay);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void updateUnits() {
        updateBullets();
        updatePlayers();
        respawnDeadPlayers();
    }

    private void respawnDeadPlayers() {
        for (Player player : players) {
            if (!player.isAlive()) {
                spawnPlayer(player);
                player.setHp(Unit.MAX_HP);
            }
        }
    }

    private void updateBullets() {
        List<AbstractBullet> bulletsToRemove = null;
        for (AbstractBullet bullet : bullets) {
            bullet.update();
            if (bullet.isDead()) {
                if (bulletsToRemove == null) {
                    bulletsToRemove = new ArrayList<AbstractBullet>();
                }
                bulletsToRemove.add(bullet);
            }
        }
        if (bulletsToRemove != null) {
            bullets.removeAll(bulletsToRemove);
            List<AbstractBullet> bulletsTmp = new ArrayList<>();
            //      Collections.copy(bulletsTmp, bullets);
            //     bulletsTmp.removeAll(bulletsToRemove);
            //   bullets = bulletsTmp;
        }
    }

    private void updatePlayers() {
        for (ActiveUnit player : players) {
            player.update();
        }
    }

    private void initTiles() {
        mapManager = new MapManager();
        //   tiles = mapManager.getRandomizeTiles(WIDTH, HEIGHT);
        //  tiles = mapManager.loadMapFromFile("mapHouses.json");
        // tiles = mapManager.loadMapFromFile("mapHouseDoor.json");
        //tiles = mapManager.loadMapFromFile("water_house_test.json");
        tiles = mapManager.loadMapFromFile("river.json");
        mapAnalyzator = new MapAnalyzator();
        mapAnalyzator.analyzeTiles();


    }

    public int[][] getTiles() {
        return tiles;
    }

    public void setTiles(int[][] tiles) {
        this.tiles = tiles;
    }

    public Player createNewPlayer() {

       /* Player player = new Player((int) (Math.random() * WIDTH * CELL_SIZE),
                (int) (Math.random() * HEIGHT * CELL_SIZE), this);*/
        Player player = new Player(-100, -100, this);
        players.add(player);
        spawnPlayer(player);

        return player;
    }

    private void spawnPlayer(Player player) {
        int randomIndex = (int) (Math.random() * spawns.size());
        Spawn spawn = spawns.get(randomIndex);
        player.getPosition().setX(spawn.getPosition().getX());
        player.getPosition().setY(spawn.getPosition().getY());
    }

    public int getNextId() {
        currentId++;
        return currentId;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public void setPlayers(List<Player> players) {
        this.players = players;
    }

    public Player getPlayerById(int playerId) {
        for (Player player : players) {
            if (player.getId() == playerId) {
                return player;
            }
        }
        return null;

    }

    public List<AbstractBullet> getBullets() {
        return bullets;
    }

    public void setBullets(List<AbstractBullet> bullets) {
        this.bullets = bullets;
    }

    public long getDelay() {
        return delay;
    }

    public MapManager getMapManager() {
        return mapManager;
    }

    public int[][] getPassable() {
        return passable;
    }

    public boolean inPassableFrame(int passableX, int passableY) {
        if (passableX >= 0 && passable.length > passableX
                && passableY >= 0 && passableY < passable[0].length) {
            return true;
        }
        return false;
    }

    public boolean isPassablePlace(int x, int y) {
        int[][] passable = getPassable();
        int passableX = x / Game.PASSABLE_CELL_SIZE;
        int passableY = y / Game.PASSABLE_CELL_SIZE;
        if (!inPassableFrame(passableX, passableY)) {
            return false;
        }
        int value = passable[passableX][passableY];
        if (value == 1) {
            return true;
        }
        return false;
    }

    public int[][] getPenetrable() {
        return penetrable;
    }

    public void setPenetrable(int[][] penetrable) {
        this.penetrable = penetrable;
    }

    public boolean isPenetrablePlace(int x, int y) {
        int[][] passable = getPassable();
        int penetrableX = x / Game.PASSABLE_CELL_SIZE;
        int penetrableY = y / Game.PASSABLE_CELL_SIZE;
        if (!inPassableFrame(penetrableX, penetrableY)) {
            return false;
        }
        int value = penetrable[penetrableX][penetrableY];
        if (value == 1) {
            return true;
        }
        return false;
    }

    public void addNewItemContainer(ItemContainer itemToSpawn) {
        itemsContainers.add(itemToSpawn);
    }

    public List<ItemContainer> getItemsContainer() {
        return itemsContainers;
    }

    private class MapAnalyzator {
        private void analyzeTiles() {
            initSpawns();
            initPassable();
            initPenetrable();
            initWeaponSpawn();
        }

        private void initWeaponSpawn() {
            itemSpawns = new ArrayList<ItemSpawn>();

            for (int x = 0; x < WIDTH; x++) {
                for (int y = 0; y < HEIGHT; y++) {
                    int ak47SpriteId = mapManager.getTileTypeByName("WEAPON_SPAWN_AK47_GRASS1").getId();
                    if (tiles[x][y] == ak47SpriteId) {
                        itemSpawns.add(new ItemSpawn((x * CELL_SIZE) + CELL_SIZE / 2, (y * CELL_SIZE) + CELL_SIZE / 2, Game.this,
                                new ItemContainer(Game.this, new Ak47(), mapManager.getTileTypeByName("AK47").getId(), 0, 0)));
                    }
                }
            }
        }

        private void initPenetrable() {
            int penetrableWidth = (WIDTH * CELL_SIZE) / PASSABLE_CELL_SIZE;
            int penetrableHeight = (HEIGHT * CELL_SIZE) / PASSABLE_CELL_SIZE;
            penetrable = new int[penetrableWidth][penetrableHeight];
            for (int x = 0; x < penetrableWidth; x++) {
                for (int y = 0; y < penetrableHeight; y++) {
                    penetrable[x][y] = 1;
                }
            }

            for (int x = 0; x < WIDTH; x++) {
                for (int y = 0; y < HEIGHT; y++) {
                    if (mapManager.haveTag(tiles[x][y], "IMPENETRABLE")) {
                        for (int xx = 0; xx < CELL_SIZE / PASSABLE_CELL_SIZE; xx++)
                            for (int yy = 0; yy < CELL_SIZE / PASSABLE_CELL_SIZE; yy++)
                                penetrable[(x * CELL_SIZE) / PASSABLE_CELL_SIZE + xx][(y * CELL_SIZE) / PASSABLE_CELL_SIZE + yy] = 0;
                    }
                }
            }
        }

        private void initPassable() {
            int passableWidth = (WIDTH * CELL_SIZE) / PASSABLE_CELL_SIZE;
            int passableHeight = (HEIGHT * CELL_SIZE) / PASSABLE_CELL_SIZE;
            passable = new int[passableWidth][passableHeight];
            for (int x = 0; x < passableWidth; x++) {
                for (int y = 0; y < passableHeight; y++) {
                    passable[x][y] = 1;
                }
            }

            for (int x = 0; x < WIDTH; x++) {
                for (int y = 0; y < HEIGHT; y++) {
                    if (mapManager.haveTag(tiles[x][y], "IMPASSABLE")) {
                        for (int xx = 0; xx < CELL_SIZE / PASSABLE_CELL_SIZE; xx++)
                            for (int yy = 0; yy < CELL_SIZE / PASSABLE_CELL_SIZE; yy++)
                                passable[(x * CELL_SIZE) / PASSABLE_CELL_SIZE + xx][(y * CELL_SIZE) / PASSABLE_CELL_SIZE + yy] = 0;
                    }
                }
            }

        }

        private void initSpawns() {
            spawns = new ArrayList<Spawn>();

            for (int x = 0; x < WIDTH; x++) {
                for (int y = 0; y < HEIGHT; y++) {
                    if (tiles[x][y] == mapManager.getTileTypeByName("SPAWN1").getId()) {
                        spawns.add(new Spawn((x * CELL_SIZE) + CELL_SIZE / 2, (y * CELL_SIZE) + CELL_SIZE / 2, Game.this));
                    }
                }
            }
        }
    }
}
