/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ict.servlet;

import ict.bean.AssessmentAssignBean;
import ict.bean.AssessmentBean;
import ict.bean.AssessmentQuestionBean;
import ict.db.AssessmentDB;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
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
@WebServlet(name = "assign_assessment", urlPatterns = {"/assignAssessment"})
public class AssignAssessment extends HttpServlet {

    int assID;
    int studID;
    int moduleID;
    int numOfAttem = 0;
    String[] numOfQuestion;
    int correctAnswer;
    int usedTime;
    private AssessmentDB db;

    public void init() {
        String dbUser = this.getServletContext().getInitParameter("dbUser");
        String dbPassword = this.getServletContext().getInitParameter("dbPassword");
        String dbUrl = this.getServletContext().getInitParameter("dbUrl");
        db = new AssessmentDB(dbUrl, dbUser, dbPassword);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(false);
        if (session.getAttribute("role_id") == null) {
            RequestDispatcher rd = getServletContext().getRequestDispatcher("/pages/login.jsp");
            rd.forward(req, resp);
        }
        int sid=(int)session.getAttribute("stu_id");
        assID = Integer.parseInt(req.getParameter("assID"));
        studID = Integer.parseInt(req.getParameter("studID"));
        moduleID = Integer.parseInt(req.getParameter("moduleID"));
        numOfQuestion = req.getParameterValues("numOfQuestion");
        correctAnswer = 0;
        usedTime = Integer.parseInt(req.getParameter("usedTime"));
        for (int i = 0; i < numOfQuestion.length; i++) {
            if (req.getParameter(i + "") != null) {
                if (req.getParameter(i + "").equals("true")) {
                    correctAnswer += 1;
                }
            }
        }
        ArrayList<AssessmentAssignBean> aabList = null;
        try {

            aabList = db.queryAssignAssessmentByStudID(sid, assID);
                numOfAttem = 0;
            if (!aabList.isEmpty()) {
                numOfAttem = 0;
                AssessmentAssignBean aab = aabList.get(aabList.size() - 1);
                numOfAttem = aab.getNumOfAttem();

            }
            if (db.addAssessmentAssign(String.valueOf(assID), String.valueOf(studID), numOfAttem + 1, usedTime)) {
                aabList = db.queryAssignAssessmentByStudID(sid, assID);
                AssessmentAssignBean aab = aabList.get(aabList.size() - 1);
                if (db.addAssessmentResult(String.valueOf(aab.getAssignmentID()), correctAnswer)) {
                    req.setAttribute("correctAnswer", correctAnswer);
                    ArrayList<AssessmentBean> abList = db.queryAssessmentByID(assID);
                    ArrayList<AssessmentQuestionBean> aqbList = db.queryAssessmentQuestionByID(assID);

                    req.setAttribute("abList", abList);
                    req.setAttribute("aqbList", aqbList);

                    RequestDispatcher rd = this.getServletContext()
                            .getRequestDispatcher("/pages/assessmentResult.jsp");
                    rd.forward(req, resp);
                }

            }
        } catch (SQLException ex) {
            Logger.getLogger(AssignAssessment.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(AssignAssessment.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

}
