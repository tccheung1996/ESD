/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ict.servlet;

import ict.bean.*;
import ict.db.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author admin
 */
@WebServlet(name = "MainPage", urlPatterns = {"/MainPage"})

public class MainPage extends HttpServlet {

    private ProjectDB db;

    public void init() {

        String dbUser = this.getServletContext().getInitParameter("dbUser");
        String dbPassword = this.getServletContext().getInitParameter("dbPassword");
        String dbUrl = this.getServletContext().getInitParameter("dbUrl");
        db = new ProjectDB(dbUrl, dbUser, dbPassword);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    protected void processRequest(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession(false);
        if (session.getAttribute("role_id") == null ) {
            RequestDispatcher rd = getServletContext().getRequestDispatcher("/pages/login.jsp");
            rd.forward(request, response);
        }
        if ((int) session.getAttribute("role_id") == 3) {

            RequestDispatcher rd;
            rd = getServletContext().getRequestDispatcher("/pages/admin_main.jsp");
            rd.forward(request, response);
        } else if ( (int) session.getAttribute("role_id") == 2) {

            RequestDispatcher rd;
            rd = getServletContext().getRequestDispatcher("/pages/student_main.jsp");
            rd.forward(request, response);
        } else if ((int) session.getAttribute("role_id") == 1) {

            RequestDispatcher rd;
            rd = getServletContext().getRequestDispatcher("/pages/teacher_main.jsp");
            rd.forward(request, response);
        }
    }
}
