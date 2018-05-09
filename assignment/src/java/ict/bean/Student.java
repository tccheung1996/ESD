/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ict.bean;

import java.io.Serializable;

/**
 *
 * @author admin
 */
public class Student implements Serializable {
    private int studentID;
    private int accountID;
    private int classID;
    private String name;
    private boolean hasAdminRight=false;

    public int getStudentID() {
        return studentID;
    }

    public void setStudentID(int studentID) {
        this.studentID = studentID;
    }

    public int getAccountID() {
        return accountID;
    }

    public void setAccountID(int accountID) {
        this.accountID = accountID;
    }

    public int getClassID() {
        return classID;
    }

    public void setClassID(int classID) {
        this.classID = classID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isHasAdminRight() {
        return hasAdminRight;
    }

    public void setHasAdminRight(boolean hasAdminRight) {
        this.hasAdminRight = hasAdminRight;
    }




    public Student() {
    }


}
