/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ict.db;

import ict.bean.*;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;

public class ProjectDB {

    private String url = "";
    private String username = "";
    private String password = "";

    public ProjectDB(String url, String username, String password) {
        this.url = url;
        this.username = username;
        this.password = password;
    }

    public Connection getConnection() throws SQLException, IOException {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (Exception e) {
        }
        return DriverManager.getConnection(url, username, password);
    }

    public boolean addTeacherRecord(String name, String email, String password, String position) {
        PreparedStatement pStmnt = null;
        boolean isSuccess = false;
        try {
            Connection cnnct = getConnection();
            String preQueryStatement = "insert into account values (0, ?, ?, ? );";
            pStmnt = cnnct.prepareStatement(preQueryStatement);
            pStmnt.setInt(1, 1);
            pStmnt.setString(2, email);
            System.out.print(email);
            pStmnt.setString(3, password);
            int rowCount = pStmnt.executeUpdate();
            if (rowCount >= 1) {
                preQueryStatement = "select * from account where Email=? and Password=?";
                pStmnt = cnnct.prepareStatement(preQueryStatement);
                pStmnt.setString(1, email);
                pStmnt.setString(2, password);
                ResultSet rs = pStmnt.executeQuery();
                if (rs.next()) {
                    int account_id = rs.getInt(1);
                    preQueryStatement = "insert into teacher values (0, ?, ?, ?)";
                    pStmnt = cnnct.prepareStatement(preQueryStatement);
                    pStmnt.setInt(1, account_id);
                    pStmnt.setString(2, name);
                    pStmnt.setString(3, position);
                    rowCount = pStmnt.executeUpdate();
                    if (rowCount >= 1) {
                        isSuccess = true;
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
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return isSuccess;
    }

    public boolean addStudentRecord(String name, String email, String password, int class_id) {
        PreparedStatement pStmnt = null;
        boolean isSuccess = false;
        try {
            Connection cnnct = getConnection();
            String preQueryStatement = "insert into account values (0, ?, ?, ? );";
            pStmnt = cnnct.prepareStatement(preQueryStatement);
            pStmnt.setInt(1, 2);
            pStmnt.setString(2, email);
            pStmnt.setString(3, password);
            int rowCount = pStmnt.executeUpdate();
            if (rowCount >= 1) {
                preQueryStatement = "select * from account where Email=? and Password=?";
                pStmnt = cnnct.prepareStatement(preQueryStatement);
                pStmnt.setString(1, email);
                pStmnt.setString(2, password);
                ResultSet rs = pStmnt.executeQuery();
                if (rs.next()) {
                    int account_id = rs.getInt(1);
                    preQueryStatement = "insert into student values (0, ?, ?, ?, 0)";
                    pStmnt = cnnct.prepareStatement(preQueryStatement);
                    pStmnt.setInt(1, account_id);
                    pStmnt.setInt(2, class_id);
                    pStmnt.setString(3, name);

                    rowCount = pStmnt.executeUpdate();
                    if (rowCount >= 1) {
                        isSuccess = true;
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
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return isSuccess;
    }

    public boolean addAdminRecord(String name, String email, String password) {
        PreparedStatement pStmnt = null;
        boolean isSuccess = false;
        try {
            Connection cnnct = getConnection();
            String preQueryStatement = "insert into account values (0, ?, ?, ? );";
            pStmnt = cnnct.prepareStatement(preQueryStatement);
            pStmnt.setInt(1, 3);
            pStmnt.setString(2, email);
            pStmnt.setString(3, password);
            int rowCount = pStmnt.executeUpdate();
            if (rowCount >= 1) {
                preQueryStatement = "select * from account where Email=? and Password=?";
                pStmnt = cnnct.prepareStatement(preQueryStatement);
                pStmnt.setString(1, email);
                pStmnt.setString(2, password);
                ResultSet rs = pStmnt.executeQuery();
                if (rs.next()) {
                    int account_id = rs.getInt(1);
                    preQueryStatement = "insert into admin values (0, ?, ?)";
                    pStmnt = cnnct.prepareStatement(preQueryStatement);
                    pStmnt.setInt(1, account_id);
                    pStmnt.setString(2, name);

                    rowCount = pStmnt.executeUpdate();
                    if (rowCount >= 1) {
                        isSuccess = true;
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
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return isSuccess;
    }

    public ArrayList<Student> getStudents() {
        ArrayList<Student> students = new ArrayList<Student>();
        Connection cnnct = null;
        PreparedStatement pStmnt = null;
        try {
            cnnct = getConnection();
            String preQueryStatement = "select * from student";
            pStmnt = cnnct.prepareStatement(preQueryStatement);
            ResultSet rs = null;
            rs = pStmnt.executeQuery();
            while (rs.next()) {
                Student s = new Student();
                s.setAccountID(rs.getInt(2));
                s.setClassID(rs.getInt(3));

                s.setName(rs.getString(4));
                s.setStudentID(rs.getInt(1));
                if (rs.getInt(5) == 1) {
                    s.setHasAdminRight(true);
                }
                students.add(s);
            }
            pStmnt.close();
            cnnct.close();
        } catch (SQLException ex) {
            while (ex != null) {
                ex.printStackTrace();
                ex = ex.getNextException();
            }
        } catch (IOException ex) {
            ex.printStackTrace();

        }
        return students;
    }

    public ArrayList<Teacher> getTeachers() {
        ArrayList<Teacher> teachers = new ArrayList<Teacher>();
        Connection cnnct = null;
        PreparedStatement pStmnt = null;
        try {
            cnnct = getConnection();
            String preQueryStatement = "select * from teacher";
            pStmnt = cnnct.prepareStatement(preQueryStatement);
            ResultSet rs = null;
            rs = pStmnt.executeQuery();
            while (rs.next()) {
                Teacher t = new Teacher();
                t.setAccount_id(rs.getInt(2));
                t.setName(rs.getString(3));

                t.setPosition(rs.getString(4));

                t.setTeacher_id(rs.getInt(1));

                teachers.add(t);
            }
            pStmnt.close();
            cnnct.close();
        } catch (SQLException ex) {
            while (ex != null) {
                ex.printStackTrace();
                ex = ex.getNextException();
            }
        } catch (IOException ex) {
            ex.printStackTrace();

        }
        return teachers;
    }

    public StudentClass getStudentClassByID(int id) {
        Connection cnnct = null;
        PreparedStatement pStmnt = null;
        StudentClass c = null;
        try {
            cnnct = getConnection();
            String preQueryStatement = "select * from class where ClassID=?";
            pStmnt = cnnct.prepareStatement(preQueryStatement);
            pStmnt.setInt(1, id);
            ResultSet rs = null;
            rs = pStmnt.executeQuery();
            if (rs.next()) {
                c = new StudentClass();
                c.setClass_id(rs.getInt(1));
                c.setClass_name(rs.getString(2));
                c.setLevel(rs.getInt(3));

            }
            pStmnt.close();
            cnnct.close();
        } catch (SQLException ex) {
            while (ex != null) {
                ex.printStackTrace();
                ex = ex.getNextException();
            }
        } catch (IOException ex) {
            ex.printStackTrace();

        }
        return c;
    }

    public Account getAccountByID(int id) {
        Connection cnnct = null;
        PreparedStatement pStmnt = null;
        Account a = null;
        try {
            cnnct = getConnection();
            String preQueryStatement = "select * from account where AccountID=?";
            pStmnt = cnnct.prepareStatement(preQueryStatement);
            pStmnt.setInt(1, id);
            ResultSet rs = null;
            rs = pStmnt.executeQuery();
            if (rs.next()) {
                a = new Account();
                a.setAccount_id(id);
                a.setEmail(rs.getString(3));
                a.setPassword(rs.getString(4));
                a.setRole_id(rs.getInt(2));
            }
            pStmnt.close();
            cnnct.close();
        } catch (SQLException ex) {
            while (ex != null) {
                ex.printStackTrace();
                ex = ex.getNextException();
            }
        } catch (IOException ex) {
            ex.printStackTrace();

        }
        return a;
    }

    public Teacher getTeacherByID(int id) {
        Connection cnnct = null;
        PreparedStatement pStmnt = null;
        Teacher t = null;
        try {
            cnnct = getConnection();
            String preQueryStatement = "select * from teacher where TeacherID=?";
            pStmnt = cnnct.prepareStatement(preQueryStatement);
            pStmnt.setInt(1, id);
            ResultSet rs = null;
            rs = pStmnt.executeQuery();
            if (rs.next()) {
                t = new Teacher();
                t.setAccount_id(rs.getInt(2));
                t.setName(rs.getString(3));
                t.setPosition(rs.getString(4));
                t.setTeacher_id(rs.getInt(1));

            }
            pStmnt.close();
            cnnct.close();
        } catch (SQLException ex) {
            while (ex != null) {
                ex.printStackTrace();
                ex = ex.getNextException();
            }
        } catch (IOException ex) {
            ex.printStackTrace();

        }
        return t;
    }

    public Student getStudentByID(int id) {
        Connection cnnct = null;
        PreparedStatement pStmnt = null;
        Student s = null;
        try {
            cnnct = getConnection();
            String preQueryStatement = "select * from student where StudentID=?";
            pStmnt = cnnct.prepareStatement(preQueryStatement);
            pStmnt.setInt(1, id);
            ResultSet rs = null;
            rs = pStmnt.executeQuery();
            if (rs.next()) {
                s = new Student();
                s.setAccountID(rs.getInt(2));
                s.setClassID(rs.getInt(3));
                if (rs.getInt(3) == 1) {
                    s.setHasAdminRight(true);
                }
                s.setName(rs.getString(4));
                s.setStudentID(rs.getInt(1));

            }
            pStmnt.close();
            cnnct.close();
        } catch (SQLException ex) {
            while (ex != null) {
                ex.printStackTrace();
                ex = ex.getNextException();
            }
        } catch (IOException ex) {
            ex.printStackTrace();

        }
        return s;
    }

    public boolean editTeacher(Teacher t, Account a) {
        Connection cnnct = null;
        PreparedStatement pStmnt = null;
        boolean isSuccess = false;
        try {
            cnnct = getConnection();
            String preQueryStatement = "update teacher set Position=?,"
                    + "Name=?"
                    + "where TeacherID=?";
            pStmnt = cnnct.prepareStatement(preQueryStatement);

            pStmnt.setString(1, t.getPosition());
            pStmnt.setString(2, t.getName());
            pStmnt.setInt(3, t.getTeacher_id());
            int result = pStmnt.executeUpdate();
            if (result == 1) {
                preQueryStatement = "update account set Email=?,"
                        + "Password=?"
                        + "where AccountID=?";
                pStmnt = cnnct.prepareStatement(preQueryStatement);
                pStmnt.setString(1, a.getEmail());
                pStmnt.setString(2, a.getPassword());
                pStmnt.setInt(3, a.getAccount_id());
                result = pStmnt.executeUpdate();
                if (result == 1) {
                    isSuccess = true;

                }
            }
            pStmnt.close();
            cnnct.close();
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

    public boolean editTeacher2(Teacher t, Account a) {
        Connection cnnct = null;
        PreparedStatement pStmnt = null;
        boolean isSuccess = false;
        try {
            cnnct = getConnection();
            String preQueryStatement = "update teacher set "
                    + "Name=?"
                    + "where TeacherID=?";
            pStmnt = cnnct.prepareStatement(preQueryStatement);

            pStmnt.setString(1, t.getName());
            pStmnt.setInt(2, t.getTeacher_id());
            int result = pStmnt.executeUpdate();
            if (result == 1) {
                preQueryStatement = "update account set Email=?,"
                        + "Password=?"
                        + "where AccountID=?";
                pStmnt = cnnct.prepareStatement(preQueryStatement);
                pStmnt.setString(1, a.getEmail());
                pStmnt.setString(2, a.getPassword());
                pStmnt.setInt(3, a.getAccount_id());
                result = pStmnt.executeUpdate();
                if (result == 1) {
                    isSuccess = true;

                }
            }
            pStmnt.close();
            cnnct.close();
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

    public boolean editStudent(Student s, Account a) {
        Connection cnnct = null;
        PreparedStatement pStmnt = null;
        boolean isSuccess = false;
        try {
            cnnct = getConnection();
            String preQueryStatement = "update student set ClassID=?,"
                    + "Name=?"
                    + "where StudentID=?";
            pStmnt = cnnct.prepareStatement(preQueryStatement);

            pStmnt.setInt(1, s.getClassID());
            pStmnt.setString(2, s.getName());
            pStmnt.setInt(3, s.getStudentID());
            int result = pStmnt.executeUpdate();
            if (result == 1) {
                preQueryStatement = "update account set Email=?,"
                        + "Password=?"
                        + "where AccountID=?";
                pStmnt = cnnct.prepareStatement(preQueryStatement);
                pStmnt.setString(1, a.getEmail());
                pStmnt.setString(2, a.getPassword());
                pStmnt.setInt(3, a.getAccount_id());
                result = pStmnt.executeUpdate();
                if (result == 1) {
                    isSuccess = true;

                }
            }
            pStmnt.close();
            cnnct.close();
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

    public boolean editStudent2(Student s, Account a) {
        Connection cnnct = null;
        PreparedStatement pStmnt = null;
        boolean isSuccess = false;
        try {
            cnnct = getConnection();
            String preQueryStatement = "update student set Name=? where StudentID=?";
            pStmnt = cnnct.prepareStatement(preQueryStatement);
            pStmnt.setString(1, s.getName());
            pStmnt.setInt(2, s.getStudentID());
            int result = pStmnt.executeUpdate();
            if (result == 1) {
                preQueryStatement = "update account set Email=?,"
                        + "Password=?"
                        + "where AccountID=?";
                pStmnt = cnnct.prepareStatement(preQueryStatement);
                pStmnt.setString(1, a.getEmail());
                pStmnt.setString(2, a.getPassword());
                pStmnt.setInt(3, a.getAccount_id());
                result = pStmnt.executeUpdate();
                if (result == 1) {
                    isSuccess = true;

                }
            }
            pStmnt.close();
            cnnct.close();
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

    public String[] isValidUser(String user, String pwd) throws SQLException, IOException, ClassNotFoundException {
        Connection c = getConnection();
        String preQueryStatement = "SELECT * FROM account WHERE Email =  ? and Password =  ?";
        PreparedStatement ps = c.prepareStatement(preQueryStatement);
        ps.setString(1, user);
        ps.setString(2, pwd);
        ResultSet rs = ps.executeQuery();
        String[] data = new String[2];
        if (rs.next()) {
            int accountID = rs.getInt(1);
            int roleID = rs.getInt(2);
            data[0] = accountID + "";
            data[1] = roleID + "";
        }
        ps.close();
        c.close();
        return data;
    }

    public boolean delStudent(int sid, int aid) {
        Connection cnnct = null;
        PreparedStatement pStmnt = null;
        boolean isSuccess = false;
        try {
            cnnct = getConnection();
            String preQueryStatement = "delete from student where StudentID=?";
            pStmnt = cnnct.prepareStatement(preQueryStatement);
            pStmnt.setInt(1, sid);
            int result = pStmnt.executeUpdate();
            if (result == 1) {
                preQueryStatement = "delete from account where AccountID=?";
                pStmnt = cnnct.prepareStatement(preQueryStatement);
                pStmnt.setInt(1, aid);
                result = pStmnt.executeUpdate();
                if (result == 1) {
                    isSuccess = true;

                }

            }
            pStmnt.close();
            cnnct.close();
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

    public boolean delTeacher(int tid, int aid) {
        Connection cnnct = null;
        PreparedStatement pStmnt = null;
        boolean isSuccess = false;
        try {
            cnnct = getConnection();
            String preQueryStatement = "delete from Teacher where TeacherID=?";
            pStmnt = cnnct.prepareStatement(preQueryStatement);
            pStmnt.setInt(1, tid);
            int result = pStmnt.executeUpdate();
            if (result == 1) {
                preQueryStatement = "delete from account where AccountID=?";
                pStmnt = cnnct.prepareStatement(preQueryStatement);
                pStmnt.setInt(1, aid);
                result = pStmnt.executeUpdate();
                if (result == 1) {
                    isSuccess = true;

                }

            }
            pStmnt.close();
            cnnct.close();
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

    public boolean addMaterial(int moduleID, String mName, String des, String path, String tag, int isR, Timestamp rDate) {
        PreparedStatement pStmnt = null;
        boolean isSuccess = false;
        try {
            Connection cnnct = getConnection();
            String preQueryStatement = "INSERT INTO material VALUES (0, ?, ?, ?, ?, ?, ?, ?);";
            pStmnt = cnnct.prepareStatement(preQueryStatement);
            pStmnt.setInt(1, moduleID);
            pStmnt.setString(2, mName);
            pStmnt.setString(3, des);
            pStmnt.setString(4, path);
            pStmnt.setString(5, tag);
            pStmnt.setInt(6, isR);
            pStmnt.setTimestamp(7, rDate);

            int rowCount = pStmnt.executeUpdate();
            if (rowCount >= 1) {
                isSuccess = true;
            }
            pStmnt.close();
            cnnct.close();
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

    public ArrayList<Material> getMaterials() {
        ArrayList<Material> materials = new ArrayList<Material>();
        Connection cnnct = null;
        PreparedStatement pStmnt = null;
        try {
            cnnct = getConnection();
            String preQueryStatement = "select * from material";
            pStmnt = cnnct.prepareStatement(preQueryStatement);
            ResultSet rs = null;
            rs = pStmnt.executeQuery();
            while (rs.next()) {
                Material m = new Material();
                m.setMaterialID(rs.getInt(1));
                m.setModuleID(rs.getInt(2));
                m.setName(rs.getString(3));
                m.setDes(rs.getString(4));
                m.setPath(rs.getString(5));
                m.setTag(rs.getString(6));
                m.setIsRestricted(rs.getInt(7));
                m.setRestrictedDur(rs.getTimestamp(8));
                materials.add(m);
            }
            pStmnt.close();
            cnnct.close();
        } catch (SQLException ex) {
            while (ex != null) {
                ex.printStackTrace();
                ex = ex.getNextException();
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return materials;
    }

    public ArrayList<Material> getModMaterials(int id) {
        ArrayList<Material> materials = new ArrayList<Material>();
        Connection cnnct = null;
        PreparedStatement pStmnt = null;
        try {
            cnnct = getConnection();
            String preQueryStatement = "select * from material WHERE ModuleID=?";
            pStmnt = cnnct.prepareStatement(preQueryStatement);
            pStmnt.setInt(1, id);
            ResultSet rs = null;
            rs = pStmnt.executeQuery();
            while (rs.next()) {
                Material m = new Material();
                m.setMaterialID(rs.getInt(1));
                m.setModuleID(rs.getInt(2));
                m.setName(rs.getString(3));
                m.setDes(rs.getString(4));
                m.setPath(rs.getString(5));
                m.setTag(rs.getString(6));
                m.setIsRestricted(rs.getInt(7));
                m.setRestrictedDur(rs.getTimestamp(8));
                materials.add(m);
            }
            pStmnt.close();
            cnnct.close();
        } catch (SQLException ex) {
            while (ex != null) {
                ex.printStackTrace();
                ex = ex.getNextException();
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return materials;
    }

    public String getPath(int id) {
        Connection cnnct = null;
        PreparedStatement pStmnt = null;
        String path = "";
        try {
            cnnct = getConnection();
            String preQueryStatement = "SELECT Path FROM material WHERE MaterialID=?";
            pStmnt = cnnct.prepareStatement(preQueryStatement);
            pStmnt.setInt(1, id);
            ResultSet rs = pStmnt.executeQuery();
            while (rs.next()) {
                path = rs.getString("Path");
            }
        } catch (SQLException ex) {
            while (ex != null) {
                ex.printStackTrace();
                ex = ex.getNextException();
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return path;
    }

    public Material getMaterial(int id) {
        Connection cnnct = null;
        PreparedStatement pStmnt = null;
        Material m = new Material();
        try {
            cnnct = getConnection();
            String preQueryStatement = "SELECT * FROM material WHERE MaterialID=?";
            pStmnt = cnnct.prepareStatement(preQueryStatement);
            pStmnt.setInt(1, id);
            ResultSet rs = pStmnt.executeQuery();
            while (rs.next()) {
                m.setMaterialID(rs.getInt(1));
                m.setModuleID(rs.getInt(2));
                m.setName(rs.getString("Name"));
                m.setDes(rs.getString("Description"));
                m.setPath(rs.getString("Path"));
                m.setTag(rs.getString("Tag"));
                m.setIsRestricted(rs.getInt("IsRestricted"));
                m.setRestrictedDur(rs.getTimestamp("ExpireTime"));
            }
        } catch (SQLException ex) {
            while (ex != null) {
                ex.printStackTrace();
                ex = ex.getNextException();
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return m;
    }

    public ArrayList<Module> getAllModules() {
        ArrayList<Module> modules = new ArrayList<>();
        Connection cnnct = null;
        PreparedStatement pStmnt = null;
        try {
            cnnct = getConnection();
            String preQueryStatement = "select * from module";
            pStmnt = cnnct.prepareStatement(preQueryStatement);
            ResultSet rs = null;
            rs = pStmnt.executeQuery();
            while (rs.next()) {
                Module m = new Module();
                m.setModuleID(rs.getInt(1));
                m.setName(rs.getString(2));
                m.setDes(rs.getString(3));
                m.setaYear(rs.getString(4));
                modules.add(m);
            }
            pStmnt.close();
            cnnct.close();
        } catch (SQLException ex) {
            while (ex != null) {
                ex.printStackTrace();
                ex = ex.getNextException();
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return modules;
    }

    public ArrayList<AssessmentBean> getAssessmentByModule(int id) {
        ArrayList<AssessmentBean> assessments = new ArrayList<AssessmentBean>();
        Connection cnnct = null;
        PreparedStatement pStmnt = null;
        try {
            cnnct = getConnection();
            String preQueryStatement = "select * from assessment where ModuleID=?";
            pStmnt = cnnct.prepareStatement(preQueryStatement);
            pStmnt.setInt(1, id);

            ResultSet rs = null;
            rs = pStmnt.executeQuery();
            while (rs.next()) {
                AssessmentBean ab = new AssessmentBean();
                ab.setAssID(rs.getInt(1));
                ab.setType(rs.getInt(2));
                ab.setName(rs.getString(3));

                assessments.add(ab);
            }
            pStmnt.close();
            cnnct.close();
        } catch (SQLException ex) {
            while (ex != null) {
                ex.printStackTrace();
                ex = ex.getNextException();
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return assessments;
    }

    public boolean editMaterial(int matID, int moduleID, String mName, String des, String tag, int isR, Timestamp rDate) {
        PreparedStatement pStmnt = null;
        boolean isSuccess = false;
        try {
            Connection cnnct = getConnection();
            String preQueryStatement = "UPDATE material SET ModuleID=?, Name=?,Description=?,Tag=?,IsRestricted=?,ExpireTime=? WHERE MaterialID=?";
            pStmnt = cnnct.prepareStatement(preQueryStatement);
            pStmnt.setInt(1, moduleID);
            pStmnt.setString(2, mName);
            pStmnt.setString(3, des);
            pStmnt.setString(4, tag);
            pStmnt.setInt(5, isR);
            pStmnt.setTimestamp(6, rDate);
            pStmnt.setInt(7, matID);
            int rowCount = pStmnt.executeUpdate();
            if (rowCount >= 1) {
                isSuccess = true;
            }
            pStmnt.close();
            cnnct.close();
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

    public boolean delMaterial(int id) {
        PreparedStatement pStmnt = null;
        boolean isSuccess = false;
        try {
            Connection cnnct = getConnection();
            String preQueryStatement = "DELETE FROM material WHERE MaterialID=?;";
            pStmnt = cnnct.prepareStatement(preQueryStatement);
            pStmnt.setInt(1, id);
            int rowCount = pStmnt.executeUpdate();
            if (rowCount >= 1) {
                isSuccess = true;
            }
            pStmnt.close();
            cnnct.close();
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
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return modules;
    }

    public int getStuID(int id) {
        Connection cnnct = null;
        PreparedStatement pStmnt = null;
        int stu_id = -1;
        try {
            cnnct = getConnection();
            String preQueryStatement = "SELECT StudentID FROM student WHERE AccountID=?";
            pStmnt = cnnct.prepareStatement(preQueryStatement);
            pStmnt.setInt(1, id);
            ResultSet rs = pStmnt.executeQuery();
            while (rs.next()) {
                stu_id = rs.getInt("StudentID");
            }
        } catch (SQLException ex) {
            while (ex != null) {
                ex.printStackTrace();
                ex = ex.getNextException();
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return stu_id;
    }

    public int getTeacherID(int id) {
        Connection cnnct = null;
        PreparedStatement pStmnt = null;
        int teacher_id = -1;
        try {
            cnnct = getConnection();
            String preQueryStatement = "SELECT TeacherID FROM Teacher WHERE AccountID=?";
            pStmnt = cnnct.prepareStatement(preQueryStatement);
            pStmnt.setInt(1, id);
            ResultSet rs = pStmnt.executeQuery();
            while (rs.next()) {
                teacher_id = rs.getInt("TeacherID");
            }
        } catch (SQLException ex) {
            while (ex != null) {
                ex.printStackTrace();
                ex = ex.getNextException();
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return teacher_id;
    }

    public ArrayList<StudentPerformance> getStudentPerformances(int aid) {
        ArrayList<StudentPerformance> studentPerfors = new ArrayList<StudentPerformance>();
        Connection cnnct = null;
        PreparedStatement pStmnt = null;
        try {
            int[] sids = null;
            cnnct = getConnection();
            String preQueryStatement = "select DISTINCT  StudentID from assessment_assigned ";
            pStmnt = cnnct.prepareStatement(preQueryStatement);
            ResultSet rs = null;
            rs = pStmnt.executeQuery();
            sids = new int[100];
            int i = 0;
            while (rs.next()) {
                sids[i] = rs.getInt(1);
                i++;
            }
            for (int j = 0; j < sids.length; j++) {
                preQueryStatement = "select * from assessment_result ar, assessment a, assessment_assigned aa, student s, class c \n"
                        + "where ar.AssignmentID=aa.AssignmentID and aa.AssessmentID=a.AssessmentID and aa.StudentID=s.StudentID\n"
                        + "and c.ClassID=s.ClassID and \n"
                        + "a.AssessmentID="+aid+" and s.StudentID=" + sids[j] + "  \n"
                        + "ORDER BY \n"
                        + "    ar.NumOfCorrect DESC \n"
                        + "LIMIT 1;";
                pStmnt = cnnct.prepareStatement(preQueryStatement);
                rs=null;
                rs = pStmnt.executeQuery();
                if(rs.next()){
                StudentPerformance sp = new StudentPerformance();
                sp.setClassName(rs.getString(24));
                sp.setSid(rs.getInt(18));
                sp.setStudName(rs.getString(21));
                String preQueryStatement2 = "select count(*) from assessment_question where AssessmentID =?;";
                PreparedStatement pStmnt2 = cnnct.prepareStatement(preQueryStatement2);
                pStmnt2.setInt(1, aid);
                ResultSet rs2 = null;
                rs2 = pStmnt2.executeQuery();
                int numOfQuestion = 0;
                while (rs2.next()) {
                    numOfQuestion = rs2.getInt(1);
                }
                int numOfCorect = rs.getInt(3);
                sp.setResult(numOfCorect + "/" + numOfQuestion);
                int score = (int) (((double) numOfCorect / numOfQuestion) * 100);
                sp.setScire(score + "/100");
                sp.setScore(score);
                studentPerfors.add(sp);
                pStmnt2.close();}
            }

            pStmnt.close();
            cnnct.close();
        } catch (SQLException ex) {
            while (ex != null) {
                ex.printStackTrace();
                ex = ex.getNextException();
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return studentPerfors;
    }

}
