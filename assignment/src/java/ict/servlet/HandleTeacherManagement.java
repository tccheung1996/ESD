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
@WebServlet(name = "HandleTeacherManagement", urlPatterns = {"/handleTeacherManagement"})

public class HandleTeacherManagement extends HttpServlet {

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

        if (session.getAttribute("role_id") == null || (int) (session.getAttribute("role_id")) != 3) {
            RequestDispatcher rd = getServletContext().getRequestDispatcher("/pages/login.jsp");
            rd.forward(request, response);

        }

        String action = request.getParameter("action");
        if ("list".equalsIgnoreCase(action)) {

            // call the query db to get retrieve for all customer
            ArrayList<Teacher> teachers = db.getTeachers();
            ArrayList<Account> accounts = new ArrayList<Account>();

            for (int i = 0; i < teachers.size(); i++) {
                Account a = db.getAccountByID(((Teacher) (teachers.get(i))).getAccount_id());
                accounts.add(a);

            }

            // set the result into the attribute
            request.setAttribute("teachers", teachers);
            request.setAttribute("accounts", accounts);

// redirect the result to the listCustomers.jsp
            RequestDispatcher rd;
            rd = getServletContext().getRequestDispatcher("/pages/management_teacher.jsp");
            rd.forward(request, response);
        }
        if ("edit".equalsIgnoreCase(action)) {
            String id_string = (request.getParameter("id"));
            if (id_string != null) {
                int tid = Integer.parseInt(id_string);
                int aid = Integer.parseInt(request.getParameter("aid"));
                Teacher t = db.getTeacherByID(tid);
                Account a = db.getAccountByID(aid);

                request.setAttribute("t", t);
                request.setAttribute("a", a);
                RequestDispatcher rd;
                rd = getServletContext().getRequestDispatcher("/pages/edit_teacher.jsp");
                rd.forward(request, response);

            }

        } else if ("delete".equalsIgnoreCase(action)) {

            int id = Integer.parseInt(request.getParameter("id"));
            int aid = Integer.parseInt(request.getParameter("aid"));
            db.delTeacher(id, aid);
            ArrayList<Teacher> teachers = db.getTeachers();
            ArrayList<Account> accounts = new ArrayList<Account>();

            for (int i = 0; i < teachers.size(); i++) {
                Account a = db.getAccountByID(((Teacher) (teachers.get(i))).getAccount_id());
                accounts.add(a);

            }

            // set the result into the attribute
            request.setAttribute("teachers", teachers);
            request.setAttribute("accounts", accounts);

            RequestDispatcher rd;
            rd = getServletContext().getRequestDispatcher("/pages/management_teacher.jsp");
            rd.forward(request, response);

        } else {
            PrintWriter out = response.getWriter();
            out.println("No such action!!!");
        }
    }
}
