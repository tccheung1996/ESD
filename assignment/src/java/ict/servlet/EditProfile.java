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
@WebServlet(name = "EditProfile", urlPatterns = {"/EditProfile"})

public class EditProfile extends HttpServlet {

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
            HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(false);
        if (session.getAttribute("role_id") == null) {
            RequestDispatcher rd = getServletContext().getRequestDispatcher("/pages/login.jsp");
            rd.forward(request, response);
        }

        String role = request.getParameter("role");
        if ("Teacher".equalsIgnoreCase(role)) {
            String id_string = request.getParameter("tid");
            int id = Integer.parseInt(id_string);
            String name = request.getParameter("name");
            String email = request.getParameter("email");
            String password = request.getParameter("password");
            int account_id = Integer.parseInt(request.getParameter("account_id"));

            Teacher t = new Teacher();
            t.setAccount_id(account_id);
            t.setName(name);
            t.setTeacher_id(id);

            Account a = new Account();
            a.setAccount_id(account_id);
            a.setEmail(email);
            a.setPassword(password);

            db.editTeacher2(t, a);

            // call editCustomer to update the database record
            response.sendRedirect("/assignment/showProfile");

        } else if ("Student".equalsIgnoreCase(role)) {
            String id_string = request.getParameter("sid");
            int id = Integer.parseInt(id_string);
            String name = request.getParameter("name");
            String email = request.getParameter("email");
            String password = request.getParameter("password");
            int account_id = Integer.parseInt(request.getParameter("account_id"));
            Student s = new Student();
            s.setAccountID(account_id);
            s.setHasAdminRight(false);
            s.setName(name);
            s.setStudentID(id);
            Account a = new Account();
            a.setAccount_id(account_id);
            a.setEmail(email);
            a.setPassword(password);

            db.editStudent2(s, a);

            // call editCustomer to update the database record
            response.sendRedirect("/assignment/showProfile");

        } else if ("Administrator".equalsIgnoreCase(role)) {

        }
    }

}
