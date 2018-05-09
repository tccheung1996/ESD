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
@WebServlet(name = "HandleStudentManagement", urlPatterns = {"/handleStudentManagement"})

public class HandleStudentManagement extends HttpServlet {

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
        String action = request.getParameter("action");

        HttpSession session = request.getSession(false);

        RequestDispatcher rd = null;

        if (session.getAttribute("role_id") == null || (int) (session.getAttribute("role_id")) != 3) {
            rd = getServletContext().getRequestDispatcher("/pages/login.jsp");
            rd.forward(request, response);

        } else {

            if ("list".equalsIgnoreCase(action)) {
                // call the query db to get retrieve for all customer
                ArrayList<Student> students = db.getStudents();
                ArrayList<Account> accounts = new ArrayList<Account>();
                ArrayList<StudentClass> studentClasses = new ArrayList<StudentClass>();
                for (int i = 0; i < students.size(); i++) {
                    Account a = db.getAccountByID(((Student) (students.get(i))).getAccountID());
                    accounts.add(a);
                    StudentClass sc = db.getStudentClassByID(((Student) (students.get(i))).getClassID());
                    studentClasses.add(sc);
                }
                request.setAttribute("students", students);
                request.setAttribute("accounts", accounts);
                request.setAttribute("studentClasses", studentClasses);
                rd = getServletContext().getRequestDispatcher("/pages/management_student.jsp");
                rd.forward(request, response);

            } else if ("delete".equalsIgnoreCase(action)) {
                int id = Integer.parseInt(request.getParameter("id"));
                int aid = Integer.parseInt(request.getParameter("aid"));
                db.delStudent(id, aid);
                
                
                  ArrayList<Student> students = db.getStudents();
                ArrayList<Account> accounts = new ArrayList<Account>();
                ArrayList<StudentClass> studentClasses = new ArrayList<StudentClass>();
                for (int i = 0; i < students.size(); i++) {
                    Account a = db.getAccountByID(((Student) (students.get(i))).getAccountID());
                    accounts.add(a);
                    StudentClass sc = db.getStudentClassByID(((Student) (students.get(i))).getClassID());
                    studentClasses.add(sc);
                }
                request.setAttribute("students", students);
                request.setAttribute("accounts", accounts);
                request.setAttribute("studentClasses", studentClasses);
                rd = getServletContext().getRequestDispatcher("/pages/management_student.jsp");
                rd.forward(request, response);
                

                // redirect the result to list action
            } else if ("edit".equalsIgnoreCase(action)) {
                String id_string = (request.getParameter("id"));
                if (id_string != null) {
                    int sid = Integer.parseInt(id_string);
                    int aid = Integer.parseInt(request.getParameter("aid"));
                    Student s = db.getStudentByID(sid);
                    Account a = db.getAccountByID(aid);
                    request.setAttribute("s", s);
                    request.setAttribute("a", a);
                    rd = getServletContext().getRequestDispatcher("/pages/edit_student.jsp");
                    rd.forward(request, response);

                }

            } else if ("add".equalsIgnoreCase(action)) {
                rd = getServletContext().getRequestDispatcher("/pages/add_user.jsp");
                rd.forward(request, response);

            } else {
                PrintWriter out = response.getWriter();
                out.println("No such action!!!");
            }

        }

    }
}
