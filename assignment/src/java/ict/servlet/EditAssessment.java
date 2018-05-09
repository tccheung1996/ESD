/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ict.servlet;

import ict.db.AssessmentDB;
import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author
 */
@WebServlet(name = "edit_assessment", urlPatterns = {"/edit"})
public class EditAssessment extends HttpServlet {

    private AssessmentDB db;
    String[] questionID;
    String[] question;
    String[] answer1;
    String[] answer2;
    String[] answer3;
    String[] answer4;
    String[] trueAnswer;
    String AssID;
    int Type;
    String name;
    String des;
    int TimeLimit;
    int numOfAttem;
    String fromDate;
    String toDate;

    public void init() {
        String dbUser = this.getServletContext().getInitParameter("dbUser");
        String dbPassword = this.getServletContext().getInitParameter("dbPassword");
        String dbUrl = this.getServletContext().getInitParameter("dbUrl");
        db = new AssessmentDB(dbUrl, dbUser, dbPassword);
        try {
            db.initAssessment();
            db.initQuestion();
        } catch (SQLException ex) {
            Logger.getLogger(EditAssessment.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(EditAssessment.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(EditAssessment.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        HttpSession session = req.getSession(false);
        if (session.getAttribute("role_id") == null) {
            RequestDispatcher rd = getServletContext().getRequestDispatcher("/pages/login.jsp");
            rd.forward(req, resp);
        }

        AssID = req.getParameter("assID");
        if (req.getParameter("type").equals("exercise")) {
            Type = 1;
        } else if (req.getParameter("type").equals("test")) {
            Type = 2;
        } else {
            Type = 3;
        }
        name = req.getParameter("name");
        des = req.getParameter("des");
        TimeLimit = Integer.parseInt(req.getParameter("time")) * 100;
        numOfAttem = Integer.parseInt(req.getParameter("attempt"));
        fromDate = req.getParameter("fDate");
        toDate = req.getParameter("tDate");

        questionID = req.getParameterValues("question");

        try {

            if (db.editRecord(Integer.parseInt(AssID), Type, name, des, TimeLimit, numOfAttem, fromDate, toDate)) {
                if (questionID != null) {
                    db.delAssessmentQuestionByID(Integer.parseInt(AssID));
                    for (int i = 0; i < questionID.length; i++) {
                        db.editAssessmentQuestion(Integer.parseInt(AssID), Integer.parseInt(questionID[i]));
                    }
                    resp.sendRedirect("/assignment/ShowManageAssessment");
                }

            }

        } catch (SQLException ex) {
            Logger.getLogger(EditAssessment.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(EditAssessment.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

}
