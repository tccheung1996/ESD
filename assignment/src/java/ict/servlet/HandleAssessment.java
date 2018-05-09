/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ict.servlet;

import ict.bean.AssessmentBean;
import ict.bean.AssessmentQuestionBean;
import ict.bean.QuestionBean;
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
@WebServlet(name = "handle_assessment", urlPatterns = {"/handleAssessment"})
public class HandleAssessment extends HttpServlet {

    private AssessmentDB db;

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
        if ("listQuestion".equalsIgnoreCase(action)) {
            try {
                ArrayList<QuestionBean> mbList = db.queryQuestion(Integer.parseInt(req.getParameter("moduleID")));
                if (mbList != null) {
                    req.setAttribute("mbList", mbList);
                    RequestDispatcher rd;
                    rd = getServletContext().getRequestDispatcher("/pages/questionPool.jsp");
                    rd.forward(req, resp);
                }

            } catch (SQLException ex) {
                Logger.getLogger(HandleAssessment.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(HandleAssessment.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else if ("listAssessment".equalsIgnoreCase(action)) {
            try {
                ArrayList<AssessmentBean> abList = db.queryAssessmentByModuleID(Integer.parseInt(req.getParameter("moduleID")));
                if (abList != null) {
                    req.setAttribute("abList", abList);
                    RequestDispatcher rd;
                    rd = getServletContext().getRequestDispatcher("/pages/assessment.jsp");
                    rd.forward(req, resp);
                }

            } catch (SQLException ex) {
                Logger.getLogger(HandleAssessment.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(HandleAssessment.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else if ("editQuestion".equalsIgnoreCase(action)) {
            try {
                ArrayList<AssessmentBean> abList = db.queryAssessmentByID(Integer.parseInt(req.getParameter("edit")));
                ArrayList<QuestionBean> mbList = db.queryQuestion(Integer.parseInt(req.getParameter("moduleID")));
                ArrayList<AssessmentQuestionBean> aqbList = db.queryAssessmentQuestionByID(Integer.parseInt(req.getParameter("edit")));
                if (abList != null) {
                    req.setAttribute("abList", abList);
                    req.setAttribute("mbList", mbList);
                    req.setAttribute("aqbList", aqbList);
                    RequestDispatcher rd;
                    rd = getServletContext().getRequestDispatcher("/pages/editAssessment.jsp");
                    rd.forward(req, resp);
                }

            } catch (SQLException ex) {
                Logger.getLogger(HandleAssessment.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(HandleAssessment.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else if ("delAssessment".equalsIgnoreCase(action)) {
            int assID = Integer.parseInt(req.getParameter("assID"));
            int moduleID = Integer.parseInt(req.getParameter("moduleID"));
            try {
                if (db.delAssessment(assID)) {
                    if (db.delAssessmentQuestionByID(assID)) {
                        RequestDispatcher rd;
                        rd = getServletContext().getRequestDispatcher("/handleAssessment?action=" + "listAssessment&moduleID=" + moduleID);
                        rd.forward(req, resp);
                    }
                }
            } catch (SQLException ex) {
                Logger.getLogger(HandleAssessment.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(HandleAssessment.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

}
