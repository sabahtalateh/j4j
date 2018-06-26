package com.sabahtalateh.servlets.users;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sabahtalateh.servlets.users.model.User;
import com.sabahtalateh.servlets.users.repo.UserRepo;
import com.sabahtalateh.servlets.users.user_store.UserStore;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.ValidationException;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

        UserRepo userRepo = UserRepo.getInstance();
        try {
            userRepo.addUser(new User(name, login, email, LocalDateTime.now()));
        } catch (ValidationException e) {
            resp.setStatus(400);
            PrintWriter pw = new PrintWriter(resp.getOutputStream());
            Map<String, List<String>> responseText = new HashMap<String, List<String>>() {{
                put("errors", new ArrayList<String>() {{
                    add(e.toString());
                }});
            }};
            pw.append(new ObjectMapper().writeValueAsString(responseText));
            pw.flush();
        }
    }
}
