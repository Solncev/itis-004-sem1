package com.solncev.net.servlet;

import com.solncev.net.dto.UserDTO;
import com.solncev.net.service.UserService;
import com.solncev.net.service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "usersServlet", urlPatterns = "/users")
public class UserServlet extends HttpServlet {

    private final UserService userService = new UserServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<UserDTO> users = userService.getAll();
        req.setAttribute("users", users);
        req.getRequestDispatcher("users.ftl").forward(req, resp);
    }
}
