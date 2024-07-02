package com.classes;

import java.io.Serializable;

public class Resident implements Serializable {

    public int id; //Provided by MySQL AUTO INCREMENT
    private String name;
    private String gender;
    private String residentAdd;
    private Boolean isHomeOwner;
    private Boolean isTenant;
    private Boolean isActive; //Provided by MySQL, new users are active by default//Inactive status must be set manually;
    private String contactNo;

    public Resident(int id, String name, String gender, String residentAdd, Boolean isHomeOwner, Boolean isTenant, String contactNo){
        this.id = id;
        this.name = name;
        this.gender = gender;
        this.residentAdd = residentAdd;
        this.isHomeOwner = isHomeOwner;
        this.isTenant = isTenant;
        this.contactNo = contactNo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getResidentAdd() {
        return residentAdd;
    }

    public void setResidentAdd(String residentAdd) {
        this.residentAdd = residentAdd;
    }

    public Boolean getIsHomeOwner() {
        return isHomeOwner;
    }

    public void setIsHomeOwner(Boolean isHomeOwner) {
        this.isHomeOwner = isHomeOwner;
    }

    public Boolean getIsTenant() {
        return isTenant;
    }

    public void setIsTenant(Boolean isTenant) {
        this.isTenant = isTenant;
    }

    public Boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(Boolean isActive) {
        this.isActive = isActive;
    }

    public String getContactNo() {
        return contactNo;
    }

    public void setContactNo(String contactNo) {
        this.contactNo = contactNo;
    }
}

