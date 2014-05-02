package com.krld.manager.web.general;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Andrey on 2/13/14.
 */
public class TestBootStrap extends HttpServlet{
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<String> listNames = new ArrayList<>();
        listNames.add("Вася");
        listNames.add("Ваня");
        listNames.add("Диман");
        listNames.add("Данила Паршин");
        listNames.add("Санечка");
        req.setAttribute("listNames", listNames);
       // req.getRequestDispatcher("TestBootstrap.jsp").forward(req, resp);
        req.getRequestDispatcher("TestBootstrap.jsp").forward(req, resp);
    }
}
