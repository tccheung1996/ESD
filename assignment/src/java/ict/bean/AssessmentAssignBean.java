/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ict.bean;

/**
 *
 * @author 
 */
public class AssessmentAssignBean {
    int assignmentID;
    int assessmentID;
    int studentID;
    int NumOfAttem;
    int CurrentTime;

    public AssessmentAssignBean() {
    }

    public AssessmentAssignBean(int assignmentID, int assessmentID, int studentID, int NumOfAttem, int CurrentTime) {
        this.assignmentID = assignmentID;
        this.assessmentID = assessmentID;
        this.studentID = studentID;
        this.NumOfAttem = NumOfAttem;
        this.CurrentTime = CurrentTime;
    }

    public int getAssignmentID() {
        return assignmentID;
    }

    public void setAssignmentID(int assignmentID) {
        this.assignmentID = assignmentID;
    }

    public int getAssessmentID() {
        return assessmentID;
    }

    public void setAssessmentID(int assessmentID) {
        this.assessmentID = assessmentID;
    }

    public int getStudentID() {
        return studentID;
    }

    public void setStudentID(int studentID) {
        this.studentID = studentID;
    }

    public int getNumOfAttem() {
        return NumOfAttem;
    }

    public void setNumOfAttem(int NumOfAttem) {
        this.NumOfAttem = NumOfAttem;
    }

    public int getCurrentTime() {
        return CurrentTime;
    }

    public void setCurrentTime(int CurrentTime) {
        this.CurrentTime = CurrentTime;
    }
    
    
}
