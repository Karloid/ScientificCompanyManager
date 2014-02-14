package com.krld.manager.web;

import com.krld.manager.game.Game;
import com.krld.manager.web.game.GameTilesServlet;
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
