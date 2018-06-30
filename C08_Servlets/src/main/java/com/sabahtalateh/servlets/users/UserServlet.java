package com.sabahtalateh.servlets.users;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.sabahtalateh.servlets.users.model.User;
import com.sabahtalateh.servlets.users.service.UserService;
import com.sabahtalateh.servlets.users.service.UserDoesNotExistsException;
import org.json.JSONObject;

import javax.servlet.ServletException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * UsersServlet.
 */
public class UserServlet extends BaseServlet {

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

        if (!checkUrlPattern(req, resp)) {
            return;
        }

        Long id;

        try {
            String[] urlParts = req.getPathInfo().split("/");
            id = new Long(urlParts[1]);
            Optional<User> user = userService.findUserById(id);
            PrintWriter pw = new PrintWriter(resp.getOutputStream());
            String json = null;
            if (user.isPresent()) {
                json = new ObjectMapper().writeValueAsString(user.get());
            }
            pw.append(json).flush();
        } catch (RuntimeException e) {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            this.appendErrorToResponse(resp, e);
            return;
        }
    }

    /**
     * @param req  req.
     * @param resp resp.
     * @throws ServletException exception.
     * @throws IOException      exception.
     */
    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json");

        if (!checkUrlPattern(req, resp)) {
            return;
        }

        if (!req.getContentType().equals("application/json")) {
            resp.setStatus(HttpServletResponse.SC_UNSUPPORTED_MEDIA_TYPE);
            return;
        }

        String jsonString = new BufferedReader(req.getReader()).lines().collect(Collectors.joining());
        JSONObject jsonObject = new JSONObject(jsonString);

        Long id;
        String name;
        String login;
        String email;

        try {
            String[] urlParts = req.getPathInfo().split("/");
            id = new Long(urlParts[1]);
            name = jsonObject.getString("name");
            login = jsonObject.getString("login");
            email = jsonObject.getString("email");
        } catch (RuntimeException e) {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            this.appendErrorToResponse(resp, e);
            return;
        }

        try {
            User user = userService.replaceUser(id, name, login, email, LocalDateTime.now());
            PrintWriter pw = new PrintWriter(resp.getOutputStream());
            String json = new ObjectMapper().writeValueAsString(user);
            pw.append(json).flush();
        } catch (UserDoesNotExistsException e) {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            this.appendErrorToResponse(resp, e);
        }
    }

    /**
     * @param req  req.
     * @param resp resp.
     * @throws ServletException exception.
     * @throws IOException      exception.
     */
    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json");


    }

    /**
     * @param req  req.
     * @param resp resp.
     * @return result.
     * @throws IOException exception.
     */
    private boolean checkUrlPattern(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        boolean isMatch = true;
        String[] urlParts = req.getPathInfo().split("/");

        if (urlParts.length < 2) {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            this.appendErrorToResponse(resp, new Exception("User id does not provided."));
            isMatch = false;
        }
        return isMatch;
    }


}
