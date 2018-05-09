/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ict.bean;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 *
 * @author user
 */
public class Material implements Serializable{
    private int materialID;
    private int moduleID;
    private String name;
    private String des;
    private String path;
    private String tag;
    private int isRestricted;
    private Timestamp restrictedDur;

    public int getMaterialID() {
        return materialID;
    }

    public void setMaterialID(int materialID) {
        this.materialID = materialID;
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

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public int getIsRestricted() {
        return isRestricted;
    }

    public void setIsRestricted(int isRestricted) {
        this.isRestricted = isRestricted;
    }

    public Timestamp getRestrictedDur() {
        return restrictedDur;
    }

    public void setRestrictedDur(Timestamp restrictedDur) {
        this.restrictedDur = restrictedDur;
    }

    public Material() {
    }
}
