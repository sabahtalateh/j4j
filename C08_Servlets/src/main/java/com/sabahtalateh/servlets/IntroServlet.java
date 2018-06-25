package com.sabahtalateh.servlets;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * IntroServlet.
 */
public class IntroServlet extends HttpServlet {

    private List<String> users = new CopyOnWriteArrayList<>();

    /**
     * @param req  request.
     * @param resp response.
     * @throws ServletException exception.
     * @throws IOException      exception.
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        String login = req.getParameter("login");
        PrintWriter printWriter = new PrintWriter(resp.getOutputStream());
        printWriter.append(users.toString());
        printWriter.flush();
    }

    /**
     * @param req  request.
     * @param resp response.
     * @throws ServletException exception.
     * @throws IOException      exception.
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        users.add(req.getParameter("name"));
        resp.setContentType("text/html");
        resp.getOutputStream().println("OK");
    }
}
