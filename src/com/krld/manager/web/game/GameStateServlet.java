package com.krld.manager.web.game;

import com.google.gson.Gson;
import com.krld.manager.game.Game;
import com.krld.manager.game.model.AbstractBullet;
import com.krld.manager.game.model.Player;
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

/**
 * Created by Andrey on 2/15/14.
 */
public class GameStateServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Game game = WebServer.getGame();
        // req.getSession().setAttribute("playerId", newPlayer.getId());
        List<Player> players = game.getPlayers();
        Gson gson = new Gson();
        PrintWriter writer = resp.getWriter();
        List<Object> playersList = new ArrayList<Object>();
        for (Player player : players) {
            HashMap<String, Object> playerMap = new HashMap<>();
            playerMap.put("id", player.getId());
            playerMap.put("hp", player.getHp());
            playerMap.put("x", player.getPosition().getX());
            playerMap.put("y", player.getPosition().getY());
            playerMap.put("spriteId", player.getSpriteId());
            playersList.add(playerMap);
        }

        List<Object> bulletsList = new ArrayList<Object>();
        for (AbstractBullet bullet : game.getBullets()) {
            HashMap<String, Object> bulletMap = new HashMap<>();
            bulletMap.put("id", bullet.getId());
            bulletMap.put("x", bullet.getPosition().getX());
            bulletMap.put("y", bullet.getPosition().getY());
            bulletsList.add(bulletMap);
        }

        writer.println(" {  \"players\" : " + gson.toJson(playersList) +
                ", \"bullets\" :" + gson.toJson(bulletsList) + '}');
    }
}
