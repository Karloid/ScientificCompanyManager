package com.krld.manager.game;

import com.krld.manager.game.model.AbstractBullet;
import com.krld.manager.game.model.ActiveUnit;
import com.krld.manager.game.model.Player;
import com.krld.manager.game.model.TileTypes;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Andrey on 2/14/14.
 */
public class Game {
    public static final int WIDTH = 30;
    public static final int HEIGHT = 20;
    private static final int CELL_SIZE = 32;
    private  int currentId = -1;
    private int[][] tiles;
    private List<Player> players;
    private List<AbstractBullet> bullets;
    private long delay = 100;
    private double speedRatio = delay / 300f;

    public double getSpeedRatio() {
        return speedRatio;
    }

    public Game() {
        initTiles();
        players = new ArrayList<Player>();
        bullets = new ArrayList<AbstractBullet>();

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
        updatePlayers();
    }

    private void updatePlayers() {
        for (ActiveUnit player : players) {
            player.update();
        }
    }

    private void initTiles() {
       tiles = MapManager.getRandomizeTiles(WIDTH, HEIGHT);
    }

    public int[][] getTiles() {
        return tiles;
    }

    public void setTiles(int[][] tiles) {
        this.tiles = tiles;
    }

    public Player createNewPlayer() {
        Player player = new Player((int) (Math.random() * WIDTH * CELL_SIZE),
                (int) (Math.random() * HEIGHT * CELL_SIZE), this);
        players.add(player);
        return player;
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
}
