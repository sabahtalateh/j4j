package com.sabahtalateh.servlets.users;

import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * BaseServlet.
 */
public class BaseServlet extends HttpServlet {

    /**
     * @param resp response.
     * @param e    exception.
     * @return response with error.
     * @throws IOException exception.
     */
    protected HttpServletResponse appendErrorToResponse(HttpServletResponse resp, Exception e) throws IOException {
        PrintWriter pw = new PrintWriter(resp.getOutputStream());
        Map<String, List<String>> responseText = new HashMap<String, List<String>>() {{
            put("errors", new ArrayList<String>() {{
                add(e.toString());
            }});
        }};
        pw.append(new ObjectMapper().writeValueAsString(responseText)).flush();

        return resp;
    }
}
