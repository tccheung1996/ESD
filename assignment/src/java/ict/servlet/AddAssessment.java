/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ict.servlet;

import ict.bean.AssessmentBean;
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
 * @author ASUS
 */
@WebServlet(name = "add_assessment", urlPatterns = {"/addAssessment"})
public class AddAssessment extends HttpServlet {

    private AssessmentDB db;
    int AssID;
    String[] questionID;
    int Type;
    String name;
    String des;
    int TimeLimit;
    int moduleID;
    int numOfAttem;
    String fromDate;
    String toDate;
    ArrayList<AssessmentBean> abList;

    public void init() {
        String dbUser = this.getServletContext().getInitParameter("dbUser");
        String dbPassword = this.getServletContext().getInitParameter("dbPassword");
        String dbUrl = this.getServletContext().getInitParameter("dbUrl");
        db = new AssessmentDB(dbUrl, dbUser, dbPassword);
        try {
            db.initAssessment();
            db.initQuestion();
            db.initAssessmentQuestion();
        } catch (SQLException ex) {
            Logger.getLogger(AddAssessment.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(AddAssessment.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(AddAssessment.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(false);
        if (session.getAttribute("role_id") == null) {
            RequestDispatcher rd = getServletContext().getRequestDispatcher("/pages/login.jsp");
            rd.forward(req, resp);
        }
        if (req.getParameter("type").equals("exercise")) {
            Type = 1;
        } else if (req.getParameter("type").equals("test")) {
            Type = 2;
        } else {
            Type = 3;
        }

        name = req.getParameter("name");
        des = req.getParameter("des");
        moduleID = Integer.parseInt(req.getParameter("moduleID"));
        TimeLimit = Integer.parseInt(req.getParameter("time")) * 100;
        numOfAttem = Integer.parseInt(req.getParameter("attempt"));
        fromDate = req.getParameter("fDate");
        toDate = req.getParameter("tDate");
        questionID = req.getParameterValues("questionID");
        try {

            if (db.addRecord(AssID, Type, moduleID, name, des, TimeLimit, numOfAttem, fromDate, toDate)) {

                abList = db.queryCustID();
                AssessmentBean ab = abList.get(abList.size() - 1);
                AssID = ab.getAssID();
                for (int i = 0; i < questionID.length; i++) {
                    db.addAssessmentQuestion(AssID, Integer.parseInt(questionID[i]));
                }

                RequestDispatcher rd;
                rd = getServletContext().getRequestDispatcher("/handleAssessment?action=" + "listQuestion&moduleID=" + moduleID);
                rd.forward(req, resp);
            }
        } catch (SQLException ex) {
            Logger.getLogger(AddAssessment.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(AddAssessment.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

}
