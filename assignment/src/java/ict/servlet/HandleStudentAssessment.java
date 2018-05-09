/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ict.servlet;

import ict.bean.AssessmentAssignBean;
import ict.bean.AssessmentBean;
import ict.bean.AssessmentQuestionBean;
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
 * @author
 */
@WebServlet(name = "student_assessment", urlPatterns = {"/studentAssessment"})
public class HandleStudentAssessment extends HttpServlet {
   
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

        if (session.getAttribute("role_id") == null ) {
            RequestDispatcher rd = getServletContext().getRequestDispatcher("/pages/login.jsp");
            rd.forward(req, resp);

        }
        
        String action = req.getParameter("action");
        
        if ("list".equalsIgnoreCase(action)) {
            String moduleID = req.getParameter("moduleID");
            int studID = Integer.parseInt(req.getParameter("studID"));
            try {
                ArrayList<AssessmentBean> abList = db.queryAssessmentByModuleID(Integer.parseInt(moduleID));
                
                ArrayList<ArrayList<AssessmentAssignBean>> aabList2 =new ArrayList<ArrayList<AssessmentAssignBean>>();
                
                for(int i=0; i<abList.size(); i++){
                    AssessmentBean ab = abList.get(i);
                    ArrayList<AssessmentAssignBean> aabList = db.queryAssignAssessmentByStudID(studID,ab.getAssID());
                    aabList2.add(aabList);
                }
                
                
                
                if (abList != null) {
                    req.setAttribute("abList", abList);
                    req.setAttribute("aabList2", aabList2);
                    RequestDispatcher rd;
                    rd = getServletContext().getRequestDispatcher("/pages/listAssessment.jsp");
                    rd.forward(req, resp);
                }
            } catch (SQLException ex) {
                Logger.getLogger(HandleStudentAssessment.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(HandleStudentAssessment.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else if ("do".equalsIgnoreCase(action)) {
            String moduleID = req.getParameter("moduleID");
            String assID = req.getParameter("assID");
            String studID = req.getParameter("studID");
            try {
                ArrayList<AssessmentBean> abList = db.queryAssessmentByID(Integer.parseInt(assID));
                AssessmentBean ab = abList.get(0);
                ArrayList<AssessmentAssignBean> aabList = db.queryAssignAssessmentByStudID(Integer.parseInt(studID), Integer.parseInt(assID));
                ArrayList<AssessmentQuestionBean> aqbList = db.queryAssessmentQuestionByID(Integer.parseInt(assID));
                QuestionBean qb = new QuestionBean();
                ArrayList<QuestionBean> qbList = new ArrayList<QuestionBean>();;
                
                for (int i = 0; i < aqbList.size(); i++) {
                    
                    AssessmentQuestionBean aqb = aqbList.get(i);
                    qb = db.queryQuestionByID(aqb.getQuestionID());
                    qbList.add(qb);
                }
                
                
               
                    req.setAttribute("abList", abList);
                    req.setAttribute("aabList", aabList);
                    req.setAttribute("aqbList", aqbList);
                    req.setAttribute("qbList", qbList);

                    RequestDispatcher rd;
                    rd = getServletContext().getRequestDispatcher("/pages/doAssessment.jsp");
                    rd.forward(req, resp);
                
            } catch (SQLException ex) {
                Logger.getLogger(HandleStudentAssessment.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(HandleStudentAssessment.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

}
