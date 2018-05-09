/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ict.servlet;

import ict.bean.*;
import ict.db.*;
import java.io.IOException;
import java.sql.SQLException;
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
@WebServlet(name = "ShowAssessmentPage", urlPatterns = {"/ShowAssessmentPage"})

public class ShowAssessmentPage extends HttpServlet {
    
    private AssessmentDB db;
    
    public void init() {
        
        String dbUser = this.getServletContext().getInitParameter("dbUser");
        String dbPassword = this.getServletContext().getInitParameter("dbPassword");
        String dbUrl = this.getServletContext().getInitParameter("dbUrl");
        db = new AssessmentDB(dbUrl, dbUser, dbPassword);
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
        try {
            String id = request.getParameter("id");
            String output = "";
            ArrayList<Module> modules = db.getStuMods(Integer.parseInt(id));
            
            for (int i = 0; i < modules.size(); i++) {
                Module m = modules.get(i);
                
                output += ("<h1><a href=\"/assignment/studentAssessment?action=list&studID=" + id + "&moduleID=" + m.getModuleID() + "\">" + m.getName() + "</a></h1>");
                
            }
            request.setAttribute("output", output);
            
            RequestDispatcher rd;
            rd = getServletContext().getRequestDispatcher("/pages/assessment_student.jsp");
            rd.forward(request, response);
        } catch (Exception e) {
            
        }
    }
}
