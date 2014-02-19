package com.krld.manager.web.game;

import com.google.gson.Gson;
import com.krld.manager.game.Game;
import com.krld.manager.game.Utils;
import com.krld.manager.game.model.Player;
import com.krld.manager.web.WebServer;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by Andrey on 2/14/14.
 */
public class GameTilesServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Game game = WebServer.getGame();
        Player newPlayer;
        Integer playerId = (Integer) (req.getSession().getAttribute("playerId"));
        if (playerId == null) {
            newPlayer = game.createNewPlayer();
            req.getSession().setAttribute("playerId", newPlayer.getId());
        } else {
            newPlayer = game.getPlayerById(playerId);
        }
        int[][] tiles = game.getTiles();
        Gson gson = new Gson();
        PrintWriter writer = resp.getWriter();
        writer.println(" {  \"width\" : " + Game.WIDTH +
                ",\"height\" : " + Game.HEIGHT +
                ",\"playerId\" : " + newPlayer.getId() +
                ",\"delay\" : " + game.getDelay() +
                ",\"tilesTypes\" : " + Utils.readFile("tileTypes.json") +
                ",\"tiles\" : " + gson.toJson(tiles) + '}');
        System.out.println("GameTilesServlet triggered for player id: " + playerId);
    }
}
