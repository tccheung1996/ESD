/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ict.bean;

/**
 *
 * @author ASUS
 */
public class ClassModuleBean {

    int classID;
    int moduleID;
    int teacherID;

    public ClassModuleBean() {
    }

    public ClassModuleBean(int classID, int moduleID, int teacherID) {
        this.classID = classID;
        this.moduleID = moduleID;
        this.teacherID = teacherID;
    }

    public int getClassID() {
        return classID;
    }

    public void setClassID(int classID) {
        this.classID = classID;
    }

    public int getModuleID() {
        return moduleID;
    }

    public void setModuleID(int moduleID) {
        this.moduleID = moduleID;
    }

    public int getTeacherID() {
        return teacherID;
    }

    public void setTeacherID(int teacherID) {
        this.teacherID = teacherID;
    }

}
