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
public class ModuleBean implements Serializable{
    private int ModuleID;
    private String Name;
    private String des;
    private String aYear;

    public ModuleBean(int ModuleID, String Name, String des, String aYear) {
        this.ModuleID = ModuleID;
        this.Name = Name;
        this.des = des;
        this.aYear = aYear;
    }

    public ModuleBean() {
    }

    public int getModuleID() {
        return ModuleID;
    }

    public void setModuleID(int ModuleID) {
        this.ModuleID = ModuleID;
    }

    public String getName() {
        return Name;
    }

    public void setName(String Name) {
        this.Name = Name;
    }

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
    }

    public String getaYear() {
        return aYear;
    }

    public void setaYear(String aYear) {
        this.aYear = aYear;
    }
    
}
