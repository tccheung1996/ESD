/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ict.bean;

import java.io.Serializable;

/**
 *
 * @author 
 */
public class AssessmentBean implements Serializable{
    int AssID;
    int Type;
    int moduleID;
    String name; 
    String des;
    int TimeLimit; 
    int numOfAttem;
    String fromDate;
    String toDate;

    public AssessmentBean() {
    }

    public AssessmentBean(int AssID, int Type, int moduleID, String name, String des, int TimeLimit, int numOfAttem, String fromDate, String toDate) {
        this.AssID = AssID;
        this.Type = Type;
        this.moduleID = moduleID;
        this.name = name;
        this.des = des;
        this.TimeLimit = TimeLimit;
        this.numOfAttem = numOfAttem;
        this.fromDate = fromDate;
        this.toDate = toDate;
    }

    public int getAssID() {
        return AssID;
    }

    public void setAssID(int AssID) {
        this.AssID = AssID;
    }

    public int getType() {
        return Type;
    }

    public void setType(int Type) {
        this.Type = Type;
    }

    public int getModuleID() {
        return moduleID;
    }

    public void setModuleID(int moduleID) {
        this.moduleID = moduleID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
    }

    public int getTimeLimit() {
        return TimeLimit;
    }

    public void setTimeLimit(int TimeLimit) {
        this.TimeLimit = TimeLimit;
    }

    public int getNumOfAttem() {
        return numOfAttem;
    }

    public void setNumOfAttem(int numOfAttem) {
        this.numOfAttem = numOfAttem;
    }

    public String getFromDate() {
        return fromDate;
    }

    public void setFromDate(String fromDate) {
        this.fromDate = fromDate;
    }

    public String getToDate() {
        return toDate;
    }

    public void setToDate(String toDate) {
        this.toDate = toDate;
    }

    
}
