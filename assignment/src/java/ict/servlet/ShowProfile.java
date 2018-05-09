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
@WebServlet(name = "ShowProfile", urlPatterns = {"/showProfile"})

public class ShowProfile extends HttpServlet {

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
        if (session.getAttribute("role_id") == null) {
            RequestDispatcher rd = getServletContext().getRequestDispatcher("/pages/login.jsp");
            rd.forward(request, response);
        }
        int role = (int) session.getAttribute("role_id");
        if (role == 3) { //admin
            RequestDispatcher rd;
            rd = getServletContext().getRequestDispatcher("/pages/admin_main.jsp");
            rd.forward(request, response);
        } else if (role == 2) { //student
            Student s = db.getStudentByID((int) session.getAttribute("stu_id"));
            Account a = db.getAccountByID((int) session.getAttribute("account_id"));
            request.setAttribute("s", s);
            request.setAttribute("a", a);
            RequestDispatcher rd;
            rd = getServletContext().getRequestDispatcher("/pages/student_profile.jsp");
            rd.forward(request, response);
        } else if (role == 1) { //teacher
            Teacher t = db.getTeacherByID((int) session.getAttribute("teacher_id"));
            Account a = db.getAccountByID((int) session.getAttribute("account_id"));
            request.setAttribute("t", t);
            request.setAttribute("a", a);
            RequestDispatcher rd;
            rd = getServletContext().getRequestDispatcher("/pages/teacher_profile.jsp");
            rd.forward(request, response);
        }

    }
}
