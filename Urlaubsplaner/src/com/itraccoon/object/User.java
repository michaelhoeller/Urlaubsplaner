package com.itraccoon.object;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "user")
@XmlAccessorType(XmlAccessType.FIELD)
public class User {
    
    private Integer id;
    private String  name;
    private Integer daysSpent;
    private Integer daysRemaining;
    private Integer daysPerYear;
    private Integer userRole;
    
    public User() {
    }
    
    public User(String name, Integer daysAvailable, Integer daysPerYear) {
        this.name = name;
        this.daysPerYear = daysPerYear;
        this.daysSpent = 0;
        this.daysRemaining = daysAvailable;
    }
    
    public User(String name, Integer daysSpent, Integer daysRemaining, Integer daysPerYear, Integer userRole) {
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
    
    public Integer getDaysSpent() {
        return daysSpent;
    }
    
    public void setDaysSpent(Integer daysSpent) {
        this.daysSpent = daysSpent;
    }
    
    public Integer getDaysRemaining() {
        return daysRemaining;
    }
    
    public void setDaysRemaining(Integer daysRemaining) {
        this.daysRemaining = daysRemaining;
    }
    
    public Integer getDaysPerYear() {
        return daysPerYear;
    }
    
    public void setDaysPerYear(Integer daysPerYear) {
        this.daysPerYear = daysPerYear;
    }
    
    public Integer getUserRole() {
        return userRole;
    }
    
    public void setUserRole(Integer userRole) {
        this.userRole = userRole;
    }
    
}
