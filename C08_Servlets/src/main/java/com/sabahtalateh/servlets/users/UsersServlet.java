package com.sabahtalateh.servlets.users;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sabahtalateh.servlets.users.model.User;
import com.sabahtalateh.servlets.users.user_store.UserStore;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;

/**
 * UsersServlet.
 */
public class UsersServlet extends HttpServlet {

    private UserStore userStore = UserStore.getInstance();

    /**
     * @param req  req.
     * @param resp resp.
     * @throws ServletException exception.
     * @throws IOException      exception.
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        userStore.add(new User(0, "Ivan", "Ivan", "ivan@gmail.com", LocalDateTime.now()));

        resp.setContentType("application/json");
        PrintWriter pw = new PrintWriter(resp.getOutputStream());
        String json = new ObjectMapper().writeValueAsString(userStore.asList());
        pw.append(json);
        pw.flush();
    }

    /**
     * @param req  req.
     * @param resp resp.
     * @throws ServletException exception.
     * @throws IOException      exception.
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        String login = req.getParameter("login");
        String email = req.getParameter("email");

    }
}
