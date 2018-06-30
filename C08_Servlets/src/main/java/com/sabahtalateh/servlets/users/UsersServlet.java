package com.sabahtalateh.servlets.users;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sabahtalateh.servlets.users.model.User;
import com.sabahtalateh.servlets.users.service.UserService;
import com.sabahtalateh.servlets.users.service.UserWithSameIdExistsException;
import org.json.JSONException;
import org.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.util.stream.Collectors;

/**
 * UsersServlet.
 */
public class UsersServlet extends BaseServlet {

    private UserService userService = UserService.getInstance();

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
        String json = new ObjectMapper().writeValueAsString(userService.getUsersCollection());
        pw.append(json).flush();
    }

    /**
     * @param req  req.
     * @param resp resp.
     * @throws ServletException exception.
     * @throws IOException      exception.
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json");

        if (!req.getContentType().equals("application/json")) {
            resp.setStatus(HttpServletResponse.SC_UNSUPPORTED_MEDIA_TYPE);
            return;
        }

        String jsonString = new BufferedReader(req.getReader()).lines().collect(Collectors.joining());
        JSONObject jsonObject = new JSONObject(jsonString);

        String name;
        String login;
        String email;

        try {
            name = jsonObject.getString("name");
            login = jsonObject.getString("login");
            email = jsonObject.getString("email");
        } catch (JSONException e) {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            this.appendErrorToResponse(resp, e);
            return;
        }

        User inserted = userService.addUser(name, login, email, LocalDateTime.now());
        PrintWriter pw = new PrintWriter(resp.getOutputStream());
        String json = new ObjectMapper().writeValueAsString(inserted);
        pw.append(json).flush();
    }
}
