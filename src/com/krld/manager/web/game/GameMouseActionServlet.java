package com.krld.manager.web.game;

import com.google.gson.Gson;
import com.krld.manager.game.Game;
import com.krld.manager.game.model.Player;
import com.krld.manager.web.WebServer;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Map;

/**
 * Created by Andrey on 2/15/14.
 */
public class GameMouseActionServlet extends HttpServlet{
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String inputString = new BufferedReader(new InputStreamReader(req.getInputStream())).readLine();
        System.out.println(inputString);
        Game game = WebServer.getGame();
        Player player = game.getPlayerById((int) (req.getSession().getAttribute("playerId")));
        Map root = new Gson().fromJson(inputString, Map.class);
        player.action((int)Math.round((double)root.get("x")), (int)Math.round((double)root.get("y")));
    }
}

