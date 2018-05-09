/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ict.servlet;

import ict.bean.QuestionBean;
import javax.servlet.annotation.WebServlet;
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
 * @author a1
 */
@WebServlet(name = "handle_question", urlPatterns = {"/handleQuestion"})
public class HandleQuestion extends HttpServlet {

    private AssessmentDB db;
    int questionID;
    String question;
    int moduleID;
    String answer1;
    String answer2;
    String answer3;
    String answer4;
    String trueAnswer;

    public void init() {
        String dbUser = this.getServletContext().getInitParameter("dbUser");
        String dbPassword = this.getServletContext().getInitParameter("dbPassword");
        String dbUrl = this.getServletContext().getInitParameter("dbUrl");
        db = new AssessmentDB(dbUrl, dbUser, dbPassword);

        try {
            db.initAssessment();
            db.initQuestion();
        } catch (SQLException ex) {
            Logger.getLogger(AddQuestion.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(AddQuestion.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(AddQuestion.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(false);
        if (session.getAttribute("role_id") == null) {
            RequestDispatcher rd = getServletContext().getRequestDispatcher("/pages/login.jsp");
            rd.forward(req, resp);
        }

        String action = req.getParameter("action");
        if ("edit".equalsIgnoreCase(action)) {
            try {
                QuestionBean qbList = db.queryQuestionByID(Integer.parseInt(req.getParameter("questID")));
                if (qbList != null) {
                    req.setAttribute("qbList", qbList);
                    RequestDispatcher rd;
                    rd = getServletContext().getRequestDispatcher("/pages/editQuestion.jsp");
                    rd.forward(req, resp);
                }
            } catch (SQLException ex) {
                Logger.getLogger(HandleQuestion.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(HandleQuestion.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else if ("editQuestion".equalsIgnoreCase(action)) {

            questionID = Integer.parseInt(req.getParameter("questID"));
            question = req.getParameter("quest");
            answer1 = req.getParameter("answer1");
            answer2 = req.getParameter("answer2");
            answer3 = req.getParameter("answer3");
            answer4 = req.getParameter("answer4");
            trueAnswer = req.getParameter("trueAnswer");
            moduleID = Integer.parseInt(req.getParameter("moduleID"));

            try {
                if (db.editQuestion(questionID, question, answer1, answer2, answer3, answer4, Integer.parseInt(trueAnswer))) {
                    RequestDispatcher rd;
                    rd = getServletContext().getRequestDispatcher("/handleAssessment?action=" + "listQuestion&moduleID=" + moduleID);
                    rd.forward(req, resp);
                }
            } catch (SQLException ex) {
                Logger.getLogger(HandleQuestion.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(HandleQuestion.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else if ("delQuestion".equalsIgnoreCase(action)) {
            questionID = Integer.parseInt(req.getParameter("delete"));
            moduleID = Integer.parseInt(req.getParameter("moduleID"));

            try {
                if (db.delQuestion(questionID)) {
                    RequestDispatcher rd;
                    rd = getServletContext().getRequestDispatcher("/handleAssessment?action=" + "listQuestion&moduleID=" + moduleID);
                    rd.forward(req, resp);
                }
            } catch (SQLException ex) {
                Logger.getLogger(HandleQuestion.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(HandleQuestion.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

}
