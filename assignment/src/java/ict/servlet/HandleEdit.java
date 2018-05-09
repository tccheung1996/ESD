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
@WebServlet(name = "HandleEdit", urlPatterns = {"/handleEdit"})

public class HandleEdit extends HttpServlet {

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

        if (session.getAttribute("role_id") == null || (int) (session.getAttribute("role_id")) != 3) {
            RequestDispatcher rd = getServletContext().getRequestDispatcher("/pages/login.jsp");
            rd.forward(request, response);
        }

        String action = request.getParameter("action");

        if ("add".equalsIgnoreCase(action)) {
            String role = request.getParameter("role");
            if ("Teacher".equalsIgnoreCase(role)) {
                String name = request.getParameter("name");
                String email = request.getParameter("email");
                System.out.print(email);

                String password = request.getParameter("password");
                String password_again = request.getParameter("password_again");

                String position = request.getParameter("position");
                if (db.addTeacherRecord(name, email, password, position)) {
                    System.out.print("success");
                    response.sendRedirect("/assignment/handleStudentManagement?action=add");

                } else {
                    response.sendRedirect("pages/error_page.jsp");
                }

            } else if ("Student".equalsIgnoreCase(role)) {

                String name = request.getParameter("name");
                String email = request.getParameter("email");

                String password = request.getParameter("password");
                String password_again = request.getParameter("password_again");

                int class_id = Integer.parseInt(request.getParameter("class_id"));
                if (db.addStudentRecord(name, email, password, class_id)) {
                    System.out.print("success");
                    response.sendRedirect("/assignment/handleStudentManagement?action=add");

                } else {

                    response.sendRedirect("pages/error_page.jsp?url=add_user.jsp");
                }

            } else if ("Administrator".equalsIgnoreCase(role)) {

                String name = request.getParameter("name");
                String email = request.getParameter("email");

                String password = request.getParameter("password");
                String password_again = request.getParameter("password_again");

                if (db.addAdminRecord(name, email, password)) {
                    System.out.print("success");
                    response.sendRedirect("/assignment/handleStudentManagement?action=add");

                } else {

                    response.sendRedirect("pages/error_page.jsp?url=add_user.jsp");
                }

            }

        } else if ("edit".equalsIgnoreCase(action)) {
            String role = request.getParameter("role");
            if ("Teacher".equalsIgnoreCase(role)) {
                String id_string = request.getParameter("tid");
                int id = Integer.parseInt(id_string);
                String name = request.getParameter("name");
                String email = request.getParameter("email");
                String password = request.getParameter("password");
                String position = request.getParameter("position");
                int account_id = Integer.parseInt(request.getParameter("account_id"));
                
                Teacher t= new Teacher();
                t.setAccount_id(account_id);
                t.setName(name);
                t.setPosition(position);
                t.setTeacher_id(id);
                
                
            
                Account a = new Account();
                a.setAccount_id(account_id);
                a.setEmail(email);
                a.setPassword(password);

                db.editTeacher(t, a);

                // call editCustomer to update the database record
                response.sendRedirect("/assignment/handleTeacherManagement?action=list");

            } else if ("Student".equalsIgnoreCase(role)) {
                String id_string = request.getParameter("sid");
                int id = Integer.parseInt(id_string);
                String name = request.getParameter("name");
                String email = request.getParameter("email");
                String password = request.getParameter("password");
                int class_id = Integer.parseInt(request.getParameter("class_id"));
                int account_id = Integer.parseInt(request.getParameter("account_id"));
                Student s = new Student();
                s.setAccountID(account_id);
                s.setClassID(class_id);
                s.setHasAdminRight(false);
                s.setName(name);
                s.setStudentID(id);
                Account a = new Account();
                a.setAccount_id(account_id);
                a.setEmail(email);
                a.setPassword(password);

                db.editStudent(s, a);

                // call editCustomer to update the database record
                response.sendRedirect("/assignment/handleStudentManagement?action=list");

            } else if ("Administrator".equalsIgnoreCase(role)) {

            }

        } else {
            PrintWriter out = response.getWriter();
            out.println("No such action!!!");
        }
    }

}
