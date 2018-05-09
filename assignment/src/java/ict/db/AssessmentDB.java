/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ict.db;

import ict.bean.AssessmentAssignBean;
import ict.bean.AssessmentBean;
import ict.bean.AssessmentQuestionBean;
import ict.bean.ClassModuleBean;
import ict.bean.Module;
import ict.bean.ModuleBean;
import ict.bean.QuestionBean;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ASUS
 */
public class AssessmentDB {

    String dbUrl;
    String dbUser;
    String dbPassword;

    public AssessmentDB(String dbUrl, String dbUser, String dbPassword) {
        this.dbUrl = dbUrl;
        this.dbUser = dbUser;
        this.dbPassword = dbPassword;
    }

    public Connection getConnection() throws SQLException, IOException, ClassNotFoundException {
        Class.forName("com.mysql.jdbc.Driver");
        return DriverManager.getConnection(dbUrl, dbUser, dbPassword);
    }

    public ArrayList<Module> getStuMods(int stuid) {
        ArrayList<Module> modules = new ArrayList<>();
        Connection cnnct = null;
        PreparedStatement pStmnt = null;
        String[] mods;
        try {
            cnnct = getConnection();
            String preQueryStatement = "SELECT ClassID FROM student WHERE StudentID=?";
            pStmnt = cnnct.prepareStatement(preQueryStatement);
            pStmnt.setInt(1, stuid);
            ResultSet rs = null;
            rs = pStmnt.executeQuery();
            if (rs.next()) {
                int classID = rs.getInt(1);
                System.out.println("Class " + classID);
                preQueryStatement = "SELECT ModuleID FROM class_module WHERE ClassID=?";
                pStmnt = cnnct.prepareStatement(preQueryStatement);
                pStmnt.setInt(1, classID);
                rs = pStmnt.executeQuery();
                while (rs.next()) {
                    int mID = rs.getInt(1);
                    System.out.println("Module " + mID);
                    preQueryStatement = "SELECT * FROM module WHERE ModuleID=?";
                    pStmnt = cnnct.prepareStatement(preQueryStatement);
                    pStmnt.setInt(1, mID);
                    ResultSet rs2 = pStmnt.executeQuery();
                    while (rs2.next()) {
                        Module m = new Module();
                        m.setModuleID(rs2.getInt(1));
                        m.setName(rs2.getString(2));
                        m.setDes(rs2.getString(3));
                        m.setaYear(rs2.getString(4));
                        modules.add(m);
                    }
                }
            }
            pStmnt.close();
            cnnct.close();
        } catch (SQLException ex) {
            while (ex != null) {
                ex.printStackTrace();
                ex = ex.getNextException();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return modules;
    }

    public boolean addRecord(int AssID, int Type, int moduleID, String name, String des, int TimeLimit, int numOfAttem, String fromDate, String toDate) throws SQLException, IOException, ClassNotFoundException {
        Connection conn = null;
        PreparedStatement pStmnt = null;
        boolean isSuccess = false;
        try {
            conn = getConnection();
            String preQueryStatement = "insert into assessment values (null,?,?,?,?,?,?,?,?)";
            pStmnt = conn.prepareStatement(preQueryStatement);

            pStmnt.setInt(1, Type);
           
            pStmnt.setString(2, name);
            pStmnt.setString(3, des);
            pStmnt.setInt(4, TimeLimit);
            pStmnt.setInt(5, numOfAttem);
            pStmnt.setString(6, fromDate);
            pStmnt.setString(7, toDate);
             pStmnt.setInt(8, moduleID);
            int rowCount = pStmnt.executeUpdate();
            if (rowCount >= 1) {
                isSuccess = true;

            }
            pStmnt.close();
            conn.close();
        } catch (SQLException ex) {
            while (ex != null) {
                ex.printStackTrace();
                ex = ex.getNextException();
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        return isSuccess;
    }

    public boolean addAssessmentQuestion(int AssID, int question) throws SQLException, IOException, ClassNotFoundException {
        Connection conn = null;
        PreparedStatement pStmnt = null;
        boolean isSuccess = false;
        try {
            conn = getConnection();
            String preQueryStatement = "insert into assessment_question values (?,?)";
            pStmnt = conn.prepareStatement(preQueryStatement);

            pStmnt.setInt(1, AssID);
            pStmnt.setInt(2, question);

            int rowCount = pStmnt.executeUpdate();
            if (rowCount >= 1) {
                isSuccess = true;

            }
            pStmnt.close();
            conn.close();
        } catch (SQLException ex) {
            while (ex != null) {
                ex.printStackTrace();
                ex = ex.getNextException();
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        return isSuccess;
    }

    public boolean editRecord(int AssID, int Type, String name, String des, int TimeLimit, int numOfAttem, String fromDate, String toDate) throws SQLException, IOException, ClassNotFoundException {
        Connection conn = null;
        PreparedStatement pStmnt = null;
        boolean isSuccess = false;
        try {
            conn = getConnection();
            String preQueryStatement = "UPDATE assessment SET TypeID=? , Name=? , Description=? , TimeLimit=? , MaxNumOfAttem=? , FromDate=?, ToDate=? " + "WHERE AssessmentID=?;";
            pStmnt = conn.prepareStatement(preQueryStatement);

            pStmnt.setInt(8, AssID);
            pStmnt.setInt(1, Type);
            pStmnt.setString(2, name);
            pStmnt.setString(3, des);
            pStmnt.setInt(4, TimeLimit);
            pStmnt.setInt(5, numOfAttem);
            pStmnt.setString(6, fromDate);
            pStmnt.setString(7, toDate);
            int rowCount = pStmnt.executeUpdate();
            if (rowCount >= 1) {
                isSuccess = true;

            }
            pStmnt.close();
            conn.close();
        } catch (SQLException ex) {
            while (ex != null) {
                ex.printStackTrace();
                ex = ex.getNextException();
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        return isSuccess;
    }

    public boolean addQuestion(String question, String moduleID, String ans1, String ans2, String ans3, String ans4, int trueAnswer) throws SQLException, IOException, ClassNotFoundException {
        Connection conn = null;
        PreparedStatement pStmnt = null;
        boolean isSuccess = false;
        try {
            conn = getConnection();
            String preQueryStatement = "insert into question_pool values (null,?,?,?,?,?,?,?)";
            pStmnt = conn.prepareStatement(preQueryStatement);

            pStmnt.setString(2, question);
            pStmnt.setInt(1, Integer.parseInt(moduleID));
            pStmnt.setString(3, ans1);
            pStmnt.setString(4, ans2);
            pStmnt.setString(5, ans3);
            pStmnt.setString(6, ans4);
            pStmnt.setInt(7, trueAnswer);

            int rowCount = pStmnt.executeUpdate();
            if (rowCount >= 1) {
                isSuccess = true;

            }
            pStmnt.close();
            conn.close();
        } catch (SQLException ex) {
            while (ex != null) {
                ex.printStackTrace();
                ex = ex.getNextException();
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        return isSuccess;
    }
    public boolean addAssessmentAssign(String assID, String studentID,  int numOfAttempt ,int time) throws SQLException, IOException, ClassNotFoundException {
        Connection conn = null;
        PreparedStatement pStmnt = null;
        boolean isSuccess = false;
        try {
            conn = getConnection();
            String preQueryStatement = "insert into assessment_assigned values (null,?,?,?,?)";
            pStmnt = conn.prepareStatement(preQueryStatement);

          
            pStmnt.setInt(1, Integer.parseInt(assID));
            pStmnt.setInt(2, Integer.parseInt(studentID));
            pStmnt.setInt(3, numOfAttempt);
            pStmnt.setInt(4, time);

            int rowCount = pStmnt.executeUpdate();
            if (rowCount >= 1) {
                isSuccess = true;

            }
            pStmnt.close();
            conn.close();
        } catch (SQLException ex) {
            while (ex != null) {
                ex.printStackTrace();
                ex = ex.getNextException();
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        return isSuccess;
    }
    public boolean addAssessmentResult(String assID,  int correctAnswer) throws SQLException, IOException, ClassNotFoundException {
        Connection conn = null;
        PreparedStatement pStmnt = null;
        boolean isSuccess = false;
        try {
            conn = getConnection();
            String preQueryStatement = "insert into assessment_result values (null,?,?)";
            pStmnt = conn.prepareStatement(preQueryStatement);

          
            pStmnt.setInt(1, Integer.parseInt(assID));
            pStmnt.setInt(2, correctAnswer);

            int rowCount = pStmnt.executeUpdate();
            if (rowCount >= 1) {
                isSuccess = true;

            }
            pStmnt.close();
            conn.close();
        } catch (SQLException ex) {
            while (ex != null) {
                ex.printStackTrace();
                ex = ex.getNextException();
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        return isSuccess;
    }

    public boolean editAssessmentQuestion(int AssID, int questionID) throws SQLException, IOException, ClassNotFoundException {
        Connection conn = null;
        PreparedStatement pStmnt = null;
        boolean isSuccess = false;
        try {
            conn = getConnection();
            String preQueryStatement = "insert into assessment_question values (?,?)";
            pStmnt = conn.prepareStatement(preQueryStatement);
            pStmnt.setInt(1, AssID);
            pStmnt.setInt(2, questionID);

            pStmnt.setInt(1, AssID);

            int rowCount = pStmnt.executeUpdate();
            if (rowCount >= 1) {

                isSuccess = true;

            }

            pStmnt.close();
            conn.close();
        } catch (SQLException ex) {
            while (ex != null) {
                ex.printStackTrace();
                ex = ex.getNextException();
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        return isSuccess;
    }

    public boolean editQuestion(int questID, String question, String ans1, String ans2, String ans3, String ans4, int trueAnswer) throws SQLException, IOException, ClassNotFoundException {
        Connection conn = null;
        PreparedStatement pStmnt = null;
        boolean isSuccess = false;
        try {
            conn = getConnection();
            String preQueryStatement = "UPDATE question_pool SET Description=? , answer1=? , answer2=? , answer3=? , answer4=? , CorrAns=? " + "WHERE QuestionID=?;";
            pStmnt = conn.prepareStatement(preQueryStatement);

            pStmnt.setString(1, question);
            pStmnt.setString(2, ans1);
            pStmnt.setString(3, ans2);
            pStmnt.setString(4, ans3);
            pStmnt.setString(5, ans4);
            pStmnt.setInt(6, trueAnswer);
            pStmnt.setInt(7, questID);

            int rowCount = pStmnt.executeUpdate();
            if (rowCount >= 1) {
                isSuccess = true;
            }
            pStmnt.close();
            conn.close();
        } catch (SQLException ex) {
            while (ex != null) {
                ex.printStackTrace();
                ex = ex.getNextException();
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        return isSuccess;
    }

    public boolean delAssessment(int AssId) throws SQLException, IOException, ClassNotFoundException {
        Connection conn = null;
        PreparedStatement pStmnt = null;
        boolean isSuccess = false;
        try {
            conn = getConnection();
            String preQueryStatement = "DELETE from assessment where AssessmentID=?";
            pStmnt = conn.prepareStatement(preQueryStatement);

            pStmnt.setInt(1, AssId);

            int rowCount = pStmnt.executeUpdate();
            if (rowCount >= 1) {
                isSuccess = true;

            }
            pStmnt.close();
            conn.close();
        } catch (SQLException ex) {
            while (ex != null) {
                ex.printStackTrace();
                ex = ex.getNextException();
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        return isSuccess;
    }

    public boolean delQuestion(int questID) throws SQLException, IOException, ClassNotFoundException {
        Connection conn = null;
        PreparedStatement pStmnt = null;
        boolean isSuccess = false;
        try {
            conn = getConnection();
            String preQueryStatement = "DELETE from question_pool where QuestionID=?";
            pStmnt = conn.prepareStatement(preQueryStatement);

            pStmnt.setInt(1, questID);

            int rowCount = pStmnt.executeUpdate();
            if (rowCount >= 1) {
                isSuccess = true;

            }
            pStmnt.close();
            conn.close();
        } catch (SQLException ex) {
            while (ex != null) {
                ex.printStackTrace();
                ex = ex.getNextException();
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        return isSuccess;
    }

    public boolean delAssessmentQuestionByID(int AssId) throws SQLException, IOException, ClassNotFoundException {
        Connection conn = null;
        PreparedStatement pStmnt = null;
        boolean isSuccess = false;
        try {
            conn = getConnection();
            String preQueryStatement = "DELETE from assessment_question where AssessmentID=?";
            pStmnt = conn.prepareStatement(preQueryStatement);

            pStmnt.setInt(1, AssId);

            int rowCount = pStmnt.executeUpdate();
            if (rowCount >= 1) {
                isSuccess = true;

            }
            pStmnt.close();
            conn.close();
        } catch (SQLException ex) {
            while (ex != null) {
                ex.printStackTrace();
                ex = ex.getNextException();
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        return isSuccess;
    }

    public boolean delAseesmentByID(int AssId) throws SQLException, IOException, ClassNotFoundException {
        Connection conn = null;
        PreparedStatement pStmnt = null;
        boolean isSuccess = false;
        try {
            conn = getConnection();
            String preQueryStatement = "DELETE from assessment where AssessmentID=?";
            pStmnt = conn.prepareStatement(preQueryStatement);

            pStmnt.setInt(1, AssId);

            int rowCount = pStmnt.executeUpdate();
            if (rowCount >= 1) {
                isSuccess = true;

            }
            pStmnt.close();
            conn.close();
        } catch (SQLException ex) {
            while (ex != null) {
                ex.printStackTrace();
                ex = ex.getNextException();
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        return isSuccess;
    }

    public ArrayList<AssessmentBean> queryCustID() throws SQLException, IOException, ClassNotFoundException {
        Connection conn = null;
        PreparedStatement pStmnt = null;
        ArrayList<AssessmentBean> acb = new ArrayList<AssessmentBean>();
        AssessmentBean ab = null;
        try {
            conn = getConnection();

            String preQueryStatement = "select * from assessment";
            pStmnt = conn.prepareStatement(preQueryStatement);
          
            ResultSet rs = pStmnt.executeQuery();

            while (rs.next()) {
                String time = rs.getTime("TimeLimit").toString().substring(3, 5);

                ab = new AssessmentBean();
                ab.setAssID(rs.getInt("AssessmentID"));
                ab.setType(rs.getInt("TypeID"));
                ab.setName(rs.getString("Name"));
                ab.setDes(rs.getString("Description"));
                ab.setTimeLimit(Integer.parseInt(time));
                ab.setNumOfAttem(rs.getInt("MaxNumOfAttem"));
                ab.setFromDate(rs.getString("FromDate"));
                ab.setToDate(rs.getString("ToDate"));
                acb.add(ab);
            }
            pStmnt.close();
            conn.close();
        } catch (SQLException ex) {
            while (ex != null) {
                ex.printStackTrace();
                ex = ex.getNextException();
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return acb;
    }

    public ArrayList<AssessmentBean> queryAssessmentByID(int id) throws SQLException, IOException, ClassNotFoundException {
        Connection conn = null;
        PreparedStatement pStmnt = null;
        ArrayList<AssessmentBean> acb = new ArrayList<AssessmentBean>();
        AssessmentBean ab = null;
        try {
            conn = getConnection();

            String preQueryStatement = "select * from assessment where 	AssessmentID=? ";
            pStmnt = conn.prepareStatement(preQueryStatement);
            pStmnt.setInt(1, id);
            ResultSet rs = pStmnt.executeQuery();

            while (rs.next()) {
                String time = rs.getTime("TimeLimit").toString().substring(3, 5);

                ab = new AssessmentBean();
                ab.setAssID(rs.getInt("AssessmentID"));
                ab.setType(rs.getInt("TypeID"));
                ab.setModuleID(rs.getInt("ModuleID"));
                ab.setName(rs.getString("Name"));
                ab.setDes(rs.getString("Description"));
                ab.setTimeLimit(Integer.parseInt(time));
                ab.setNumOfAttem(rs.getInt("MaxNumOfAttem"));
                ab.setFromDate(rs.getString("FromDate"));
                ab.setToDate(rs.getString("ToDate"));
                acb.add(ab);
            }
            pStmnt.close();
            conn.close();
        } catch (SQLException ex) {
            while (ex != null) {
                ex.printStackTrace();
                ex = ex.getNextException();
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return acb;
    }
    public ArrayList<AssessmentQuestionBean> queryAssessmentQuestionByID(int id) throws SQLException, IOException, ClassNotFoundException {
        Connection conn = null;
        PreparedStatement pStmnt = null;
        ArrayList<AssessmentQuestionBean> aqb = new ArrayList<AssessmentQuestionBean>();
        AssessmentQuestionBean ab = null;
        try {
            conn = getConnection();

            String preQueryStatement = "select * from assessment_question where AssessmentID=? ";
            pStmnt = conn.prepareStatement(preQueryStatement);
            pStmnt.setInt(1, id);
            ResultSet rs = pStmnt.executeQuery();

            while (rs.next()) {


                ab = new AssessmentQuestionBean();
                ab.setAssID(rs.getInt("AssessmentID"));
                ab.setQuestionID(rs.getInt("QuestionID"));
                aqb.add(ab);
            }
            pStmnt.close();
            conn.close();
        } catch (SQLException ex) {
            while (ex != null) {
                ex.printStackTrace();
                ex = ex.getNextException();
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return aqb;
    }

    public ArrayList<ClassModuleBean> queryClassModuleByID(int id) throws SQLException, IOException, ClassNotFoundException {
        Connection conn = null;
        PreparedStatement pStmnt = null;
        List<String> moduleID =null;
        ArrayList<ClassModuleBean> cmbList = new ArrayList<ClassModuleBean>();
        ClassModuleBean cmb = null;
        
       
        try {
            conn = getConnection();

            String preQueryStatement = "SELECT * FROM class_module WHERE ClassID=? ";
            pStmnt = conn.prepareStatement(preQueryStatement);
            pStmnt.setInt(1, id);
            ResultSet rs = pStmnt.executeQuery();

            while (rs.next()) {
               cmb = new ClassModuleBean();
               
               cmb.setClassID(rs.getInt("ClassID"));
               cmb.setModuleID(rs.getInt("ModuleID"));
               cmb.setTeacherID(rs.getInt("TeacherID"));
               
               cmbList.add(cmb);
            }
            pStmnt.close();
            conn.close();
        } catch (SQLException ex) {
            while (ex != null) {
                ex.printStackTrace();
                ex = ex.getNextException();
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
       
        return cmbList;
    }

    public ArrayList<AssessmentBean> queryAssessmentByModuleID(int id) throws SQLException, IOException, ClassNotFoundException {
        Connection conn = null;
        PreparedStatement pStmnt = null;
        ArrayList<AssessmentBean> acb = new ArrayList<AssessmentBean>();
        AssessmentBean ab = null;
        try {
            conn = getConnection();

            String preQueryStatement = "select * from assessment where 	ModuleID=? ";
            pStmnt = conn.prepareStatement(preQueryStatement);
            pStmnt.setInt(1, id);
            ResultSet rs = pStmnt.executeQuery();

            while (rs.next()) {
                String time = rs.getTime("TimeLimit").toString().substring(3, 5);

                ab = new AssessmentBean();
                ab.setAssID(rs.getInt("AssessmentID"));
                ab.setType(rs.getInt("TypeID"));
                ab.setModuleID(rs.getInt("ModuleID"));
                ab.setName(rs.getString("Name"));
                ab.setDes(rs.getString("Description"));
                ab.setTimeLimit(Integer.parseInt(time));
                ab.setNumOfAttem(rs.getInt("MaxNumOfAttem"));
                ab.setFromDate(rs.getString("FromDate"));
                ab.setToDate(rs.getString("ToDate"));
                acb.add(ab);
            }
            pStmnt.close();
            conn.close();
        } catch (SQLException ex) {
            while (ex != null) {
                ex.printStackTrace();
                ex = ex.getNextException();
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return acb;
    }

    public ArrayList<ModuleBean> queryModule() throws SQLException, IOException, ClassNotFoundException {
        Connection conn = null;
        PreparedStatement pStmnt = null;
        ArrayList<ModuleBean> mcb = new ArrayList<ModuleBean>();
        ModuleBean mb = null;
        try {
            conn = getConnection();

            String preQueryStatement = "select * from module";
            pStmnt = conn.prepareStatement(preQueryStatement);

            ResultSet rs = pStmnt.executeQuery();

            while (rs.next()) {

                mb = new ModuleBean();
                mb.setModuleID(rs.getInt("ModuleID"));
                mb.setName(rs.getString("Name"));
                mb.setDes(rs.getString("Description"));
                mb.setaYear(rs.getString("AcademicYear"));
                mcb.add(mb);
            }
            pStmnt.close();
            conn.close();
        } catch (SQLException ex) {
            while (ex != null) {
                ex.printStackTrace();
                ex = ex.getNextException();
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return mcb;
    }

    public ArrayList<ModuleBean> queryModuleByID(int id) throws SQLException, IOException, ClassNotFoundException {
        Connection conn = null;
        PreparedStatement pStmnt = null;
        ArrayList<ModuleBean> mcb = new ArrayList<ModuleBean>();
        ModuleBean mb = null;
        try {
            conn = getConnection();

            String preQueryStatement = "select * from module where ModuleID=?";
            pStmnt = conn.prepareStatement(preQueryStatement);
            pStmnt.setInt(1, id);
            ResultSet rs = pStmnt.executeQuery();

            while (rs.next()) {

                mb = new ModuleBean();
                mb.setModuleID(rs.getInt("ModuleID"));
                mb.setName(rs.getString("Name"));
                mb.setDes(rs.getString("Description"));
                mb.setaYear(rs.getString("AcademicYear"));
                mcb.add(mb);
            }
            pStmnt.close();
            conn.close();
        } catch (SQLException ex) {
            while (ex != null) {
                ex.printStackTrace();
                ex = ex.getNextException();
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return mcb;
    }

    public ArrayList<QuestionBean> queryQuestion(int ModuleID) throws SQLException, IOException, ClassNotFoundException {
        Connection conn = null;
        PreparedStatement pStmnt = null;
        ArrayList<QuestionBean> qcb = new ArrayList<QuestionBean>();
        QuestionBean qb = null;
        try {
            conn = getConnection();

            String preQueryStatement = "select * from question_pool where ModuleID=?";
            pStmnt = conn.prepareStatement(preQueryStatement);
            pStmnt.setInt(1, ModuleID);

            ResultSet rs = pStmnt.executeQuery();

            while (rs.next()) {

                qb = new QuestionBean();
                qb.setQuestID(rs.getInt("QuestionID"));
                qb.setModuleID(rs.getInt("ModuleID"));
                qb.setQuestion(rs.getString("Description"));
                qb.setAnswer1(rs.getString("answer1"));
                qb.setAnswer2(rs.getString("answer2"));
                qb.setAnswer3(rs.getString("answer3"));
                qb.setAnswer4(rs.getString("answer4"));
                qb.setTrueAnswer(rs.getInt("CorrAns"));

                qcb.add(qb);
            }
            pStmnt.close();
            conn.close();
        } catch (SQLException ex) {
            while (ex != null) {
                ex.printStackTrace();
                ex = ex.getNextException();
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return qcb;
    }
    public ArrayList<AssessmentAssignBean> queryAssignAssessmentByStudID(int studentID,int assID) throws SQLException, IOException, ClassNotFoundException {
        Connection conn = null;
        PreparedStatement pStmnt = null;
        ArrayList<AssessmentAssignBean> aab = new ArrayList<AssessmentAssignBean>();
        AssessmentAssignBean qb = null;
        try {
            conn = getConnection();

            String preQueryStatement = "select * from assessment_assigned where StudentID=? and AssessmentID=?";
            pStmnt = conn.prepareStatement(preQueryStatement);
            pStmnt.setInt(1, studentID);
            pStmnt.setInt(2, assID);

            ResultSet rs = pStmnt.executeQuery();

            while (rs.next()) {

                qb = new AssessmentAssignBean();
                qb.setAssignmentID(rs.getInt("AssignmentID"));
                qb.setAssessmentID(rs.getInt("AssessmentID"));
                qb.setStudentID(rs.getInt("StudentID"));
                qb.setNumOfAttem(rs.getInt("CurrentNumOfAttem"));
                qb.setCurrentTime(rs.getInt("CurrentTime"));

                aab.add(qb);
            }
            pStmnt.close();
            conn.close();
        } catch (SQLException ex) {
            while (ex != null) {
                ex.printStackTrace();
                ex = ex.getNextException();
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return aab;
    }

    public QuestionBean queryQuestionByID(int questionID) throws SQLException, IOException, ClassNotFoundException {
        Connection conn = null;
        PreparedStatement pStmnt = null;
        ArrayList<QuestionBean> qcb = new ArrayList<QuestionBean>();
        QuestionBean qb = null;
        try {
            conn = getConnection();

            String preQueryStatement = "select * from question_pool where QuestionID=?";
            pStmnt = conn.prepareStatement(preQueryStatement);
            pStmnt.setInt(1, questionID);

            ResultSet rs = pStmnt.executeQuery();

            while (rs.next()) {

                qb = new QuestionBean();
                qb.setQuestID(rs.getInt("QuestionID"));
                qb.setModuleID(rs.getInt("ModuleID"));
                qb.setQuestion(rs.getString("Description"));
                qb.setAnswer1(rs.getString("answer1"));
                qb.setAnswer2(rs.getString("answer2"));
                qb.setAnswer3(rs.getString("answer3"));
                qb.setAnswer4(rs.getString("answer4"));
                qb.setTrueAnswer(rs.getInt("CorrAns"));

               
            }
            pStmnt.close();
            conn.close();
        } catch (SQLException ex) {
            while (ex != null) {
                ex.printStackTrace();
                ex = ex.getNextException();
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return qb;
    }

    public boolean initAssessment() throws SQLException, IOException, ClassNotFoundException {
        Connection conn = null;
        PreparedStatement pStmnt = null;
        boolean isSuccess = false;
        try {
            conn = getConnection();

            String preQueryStatement = "select * from assessment";
            pStmnt = conn.prepareStatement(preQueryStatement);
            //pStmnt.setString(1, name);
            ResultSet rs = pStmnt.executeQuery();

            if (!rs.next()) {
                preQueryStatement = "ALTER TABLE assessment AUTO_INCREMENT = 1";
                pStmnt = conn.prepareStatement(preQueryStatement);
                int rowCount = pStmnt.executeUpdate();
                if (rowCount >= 1) {
                    isSuccess = true;
                }
            }
            pStmnt.close();
            conn.close();
        } catch (SQLException ex) {
            while (ex != null) {
                ex.printStackTrace();
                ex = ex.getNextException();
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return isSuccess;
    }

    public boolean initAssessmentQuestion() throws SQLException, IOException, ClassNotFoundException {
        Connection conn = null;
        PreparedStatement pStmnt = null;
        boolean isSuccess = false;
        try {
            conn = getConnection();

            String preQueryStatement = "select * from assessment_question";
            pStmnt = conn.prepareStatement(preQueryStatement);
            //pStmnt.setString(1, name);
            ResultSet rs = pStmnt.executeQuery();

            if (!rs.next()) {
                preQueryStatement = "ALTER TABLE assessment_question AUTO_INCREMENT = 1";
                pStmnt = conn.prepareStatement(preQueryStatement);
                int rowCount = pStmnt.executeUpdate();
                if (rowCount >= 1) {
                    isSuccess = true;
                }
            }
            pStmnt.close();
            conn.close();
        } catch (SQLException ex) {
            while (ex != null) {
                ex.printStackTrace();
                ex = ex.getNextException();
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return isSuccess;
    }

    public boolean initQuestion() throws SQLException, IOException, ClassNotFoundException {
        Connection conn = null;
        PreparedStatement pStmnt = null;
        boolean isSuccess = false;
        try {
            conn = getConnection();

            String preQueryStatement = "select * from question_pool";
            pStmnt = conn.prepareStatement(preQueryStatement);
            //pStmnt.setString(1, name);
            ResultSet rs = pStmnt.executeQuery();

            if (!rs.next()) {
                preQueryStatement = "ALTER TABLE question_pool AUTO_INCREMENT = 1";
                pStmnt = conn.prepareStatement(preQueryStatement);
                int rowCount = pStmnt.executeUpdate();
                if (rowCount >= 1) {
                    isSuccess = true;
                }
            }
            pStmnt.close();
            conn.close();
        } catch (SQLException ex) {
            while (ex != null) {
                ex.printStackTrace();
                ex = ex.getNextException();
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return isSuccess;
    }
}
