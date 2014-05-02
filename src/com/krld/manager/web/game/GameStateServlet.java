package com.krld.manager.web.game;

import com.google.gson.Gson;
import com.krld.manager.game.Game;
import com.krld.manager.game.model.items.AbstractBullet;
import com.krld.manager.game.model.characters.Player;
import com.krld.manager.game.model.items.Gun;
import com.krld.manager.game.model.items.ItemContainer;
import com.krld.manager.web.WebServer;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Andrey on 2/15/14.
 */
public class GameStateServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Game game = WebServer.getGame();
        // req.getSession().setAttribute("playerId", newPlayer.getId());
        Integer playerId = (Integer) req.getSession().getAttribute("playerId");

        Player currentPlayer = game.getPlayerById(playerId);

        List<Player> players = game.getPlayers();
        Gson gson = new Gson();
        PrintWriter writer = resp.getWriter();
        List<Object> playersList = new ArrayList<>();
        for (Player player : players) {
            HashMap<String, Object> playerMap = new HashMap<>();
            playerMap.put("id", player.getId());
            playerMap.put("hp", player.getHp());
            playerMap.put("x", player.getPosition().getX());
            playerMap.put("y", player.getPosition().getY());
            playerMap.put("spriteId", player.getSpriteId());
            playerMap.put("name", player.getName());
            playerMap.put("k", player.getKillCount());
            playerMap.put("d", player.getDeathCount());
            playersList.add(playerMap);
        }

        List<Object> bulletsList = new ArrayList<>();
        for (AbstractBullet bullet : game.getBullets()) {
            HashMap<String, Object> bulletMap = new HashMap<>();
            bulletMap.put("id", bullet.getId());
            bulletMap.put("x", bullet.getPosition().getX());
            bulletMap.put("y", bullet.getPosition().getY());
            bulletsList.add(bulletMap);
        }

        List<Object> itemsContainerList = new ArrayList<>();
        for (ItemContainer itemContainer : game.getItemsContainer()) {
            HashMap<String, Object> itemsContainersMap = new HashMap<>();
            itemsContainersMap.put("id", itemContainer.getId());
            itemsContainersMap.put("x", itemContainer.getPosition().getX());
            itemsContainersMap.put("y", itemContainer.getPosition().getY());
            itemsContainersMap.put("spriteId", itemContainer.getSpriteId());
            itemsContainerList.add(itemsContainersMap);
        }

        List<String> playerGunsList = currentPlayer.getGuns().stream().map(Gun::getName).collect(Collectors.toList());
        writer.println(" {  \"players\" : " + gson.toJson(playersList) +
                ", \"bullets\" :" + gson.toJson(bulletsList) +
                ", \"itemsContainers\" :" + gson.toJson(itemsContainerList) +
                ", \"playerGuns\" :" + gson.toJson(playerGunsList) +
                ", \"playerBullets\" :" + gson.toJson(currentPlayer.getGun().getBulletsCount()) +
                ", \"playerGun\" :" + gson.toJson(currentPlayer.getGun().getName()) +
                '}');
    }
}
