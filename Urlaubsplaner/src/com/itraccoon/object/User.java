package com.itraccoon.object;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "user")
@XmlAccessorType(XmlAccessType.FIELD)
public class User {
    
    private Integer id;
    private String  name;
    private Float   daysSpent;
    private Float   daysRemaining;
    private Float   daysPerYear;
    private Integer userRole;
    
    public User() {
    }
    
    public User(String name, Float daysAvailable, Float daysPerYear) {
        this.name = name;
        this.daysPerYear = daysPerYear;
        this.daysSpent = (float) 0;
        this.daysRemaining = daysAvailable;
    }
    
    public User(String name, Float daysSpent, Float daysRemaining, Float daysPerYear, Integer userRole) {
        this.name = name;
        this.daysSpent = daysSpent;
        this.daysRemaining = daysRemaining;
        this.daysPerYear = daysPerYear;
        this.userRole = userRole;
    }
    
    public Integer getId() {
        return id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public Float getDaysSpent() {
        return daysSpent;
    }
    
    public void setDaysSpent(Float daysSpent) {
        this.daysSpent = daysSpent;
    }
    
    public Float getDaysRemaining() {
        return daysRemaining;
    }
    
    public void setDaysRemaining(Float daysRemaining) {
        this.daysRemaining = daysRemaining;
    }
    
    public Float getDaysPerYear() {
        return daysPerYear;
    }
    
    public void setDaysPerYear(Float daysPerYear) {
        this.daysPerYear = daysPerYear;
    }
    
    public Integer getUserRole() {
        return userRole;
    }
    
    public void setUserRole(Integer userRole) {
        this.userRole = userRole;
    }
    
}
