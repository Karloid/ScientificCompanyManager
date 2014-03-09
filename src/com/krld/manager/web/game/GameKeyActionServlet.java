package com.krld.manager.web.game;

import com.google.gson.Gson;
import com.krld.manager.game.Game;
import com.krld.manager.game.model.characters.Player;
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
 * Created by Andrey on 2/14/14.
 */
public class GameKeyActionServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String inputString = new BufferedReader(new InputStreamReader(req.getInputStream())).readLine();
      //  System.out.println(inputString);
        Game game = WebServer.getGame();
        Integer playerId = (Integer) (req.getSession().getAttribute("playerId"));
        Player player = game.getPlayerById(playerId);
        Map root = new Gson().fromJson(inputString, Map.class);
        player.setMoveUp((boolean) root.get("w"));
        player.setMoveDown((boolean) root.get("s"));
        player.setMoveRight((boolean) root.get("d"));
        player.setMoveLeft((boolean) root.get("a"));

        if ((boolean) root.get("1")) {
            player.changeGunByIndex(0);
        }
        if ((boolean) root.get("2")) {
            player.changeGunByIndex(1);
        }
        if ((boolean) root.get("3")) {
            player.changeGunByIndex(2);
        }
        if ((boolean) root.get("4")) {
            player.changeGunByIndex(3);
        }

    }
}
