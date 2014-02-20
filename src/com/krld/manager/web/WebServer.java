package com.krld.manager.web;

import com.krld.manager.game.Game;
import com.krld.manager.web.game.GameStateServlet;
import com.krld.manager.web.game.GameMouseActionServlet;
import com.krld.manager.web.game.GameKeyActionServlet;
import com.krld.manager.web.game.GameTilesServlet;
import com.krld.manager.web.general.MyMockupServlet;
import com.krld.manager.web.general.PixiTestServlet;
import com.krld.manager.web.general.SoldierGameServlet;
import com.krld.manager.web.general.TestBootStrap;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.webapp.WebAppContext;

import java.net.URL;

/**
 * Created by Andrey on 2/11/14.
 */
public class WebServer {
    private static Game game;

    public static void main(String[] args) {
        startServer();
    }

    private static void startServer() {
        Server server = new Server(80);
        URL warUrl = TestBootStrap.class.getClassLoader().getResource("");
        String warUrlString = warUrl.toExternalForm() + "com/krld/manager/web/view";
        WebAppContext webapp = new WebAppContext(warUrlString, "/");
        mappingPageServlets(webapp);
        mappingSoldierGame(webapp);
        server.setHandler(webapp);

        try {
            server.start();
            server.join();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void mappingSoldierGame(WebAppContext webapp) {
        webapp.addServlet(GameTilesServlet.class, "/game/tiles");
        webapp.addServlet(GameKeyActionServlet.class, "/game/keyAction");
        webapp.addServlet(GameMouseActionServlet.class, "/game/mouseAction");
        webapp.addServlet(GameStateServlet.class, "/game/state");
    }

    private static void mappingPageServlets(WebAppContext webapp) {
        webapp.addServlet(MyMockupServlet.class, "/soldiers");
        webapp.addServlet(MyMockupServlet.class, "/home");
        webapp.addServlet(MyMockupServlet.class, "/naryadi");
        webapp.addServlet(MyMockupServlet.class, "/dezhurstva");
        webapp.addServlet(PixiTestServlet.class, "/pixiTest");
        webapp.addServlet(SoldierGameServlet.class, "/soldierGame");
    }

    public static void start() {
        startServer();
    }

    public static Game getGame() {
        if (game == null) {
            initGame();
        }
        return game;
    }

    private static void initGame() {
        game = new Game();
    }

    public static void setGame(Game game) {
        WebServer.game = game;
    }
}
