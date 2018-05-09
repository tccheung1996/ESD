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
@WebServlet(name = "add_question", urlPatterns = {"/addQuestion"})
public class AddQuestion extends HttpServlet {

    private AssessmentDB db;
    String[] question;
    String[] answer1;
    String[] answer2;
    String[] answer3;
    String[] answer4;
    String[] trueAnswer;
    String moduleID;

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
        
        
        
        
        question = req.getParameterValues("quest");
        answer1 = req.getParameterValues("answer1");
        answer2 = req.getParameterValues("answer2");
        answer3 = req.getParameterValues("answer3");
        answer4 = req.getParameterValues("answer4");
        trueAnswer = req.getParameterValues("trueAnswer");
        moduleID = req.getParameter("moduleID");
        try {
            if (question != null) {
                for (int i = 0; i < question.length; i++) {
                    db.addQuestion(question[i], moduleID, answer1[i], answer2[i], answer3[i], answer4[i], Integer.parseInt(trueAnswer[i]));
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

}
