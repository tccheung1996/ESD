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
public class AssessmentQuestionBean {
    int assID;
    int questionID;

    public AssessmentQuestionBean() {
    }

    public AssessmentQuestionBean(int assID, int questionID) {
        this.assID = assID;
        this.questionID = questionID;
    }

    public int getAssID() {
        return assID;
    }

    public void setAssID(int assID) {
        this.assID = assID;
    }

    public int getQuestionID() {
        return questionID;
    }

    public void setQuestionID(int questionID) {
        this.questionID = questionID;
    }

    
    
}
