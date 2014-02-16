package com.krld.manager.web.game;

import com.google.gson.Gson;
import com.krld.manager.game.Game;
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
public class GamePlayersServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Game game = WebServer.getGame();
        // req.getSession().setAttribute("playerId", newPlayer.getId());
        List<Player> players = game.getPlayers();
        Gson gson = new Gson();
        PrintWriter writer = resp.getWriter();
        List<Object> res = new ArrayList<Object>();
        for (Player player : players) {
            HashMap<String, Object> map = new HashMap<>();
            map.put("id", player.getId());
            map.put("x", player.getPosition().getX());
            map.put("y", player.getPosition().getY());
            res.add(map);
        }
        writer.println(" {  \"players\" : " + gson.toJson(res) + '}');
    }
}
