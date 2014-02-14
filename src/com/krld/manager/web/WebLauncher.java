package com.krld.manager.web;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.webapp.WebAppContext;

import java.net.URL;

/**
 * Created by Andrey on 2/11/14.
 */
public class WebLauncher {
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


     /*   ServletHandler handler = new ServletHandler();
        handler.addServletWithMapping(HelloServlet.class, "/helloWorldServlet");
        handler.addServletWithMapping(HelloBootstrapMinimal.class, "/HelloBootstrapMinimal"); // fail
        handler.addServletWithMapping(HelloServletJsp.class, "/HelloServletHsp"); // fail */
        server.setHandler(webapp);
        //  server.setHandler(handler);

        try {
            server.start();
            server.join();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void mappingSoldierGame(WebAppContext webapp) {

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
}
