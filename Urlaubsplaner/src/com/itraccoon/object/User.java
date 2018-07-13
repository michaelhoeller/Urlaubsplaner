package com.itraccoon.object;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.itraccoon.util.Utils;

@XmlRootElement(name = "user")
@XmlAccessorType(XmlAccessType.FIELD)
public class User {
    
    private Integer       id;
    private String        name;
    private String        lastName;
    private Integer       daysAvailable;
    private Integer       daysSpent;
    private Integer       daysRemaining;
    private Integer       daysPerYear;
    
    @XmlElement(name = "holiday")
    private List<Holiday> holidays;
    
    public User() {
    }
    
    public User(String name, String lastName, Integer daysAvailable, Integer daysPerYear) {
        this.name = name;
        this.lastName = lastName;
        this.daysAvailable = daysAvailable;
        this.daysPerYear = daysPerYear;
        this.daysSpent = 0;
        this.daysRemaining = daysAvailable;
        this.holidays = new ArrayList<Holiday>();
    }
    
    public String getFullName() {
        return Utils.getFullName(this);
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
    
    public String getLastName() {
        return lastName;
    }
    
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    
    public Integer getDaysAvailable() {
        return daysAvailable;
    }
    
    public void setDaysAvailable(Integer days) {
        this.daysAvailable = days;
    }
    
    public Integer getDaysSpent() {
        return daysSpent;
    }
    
    public void setDaysSpent(Integer daysSpent) {
        this.daysSpent = daysSpent;
    }
    
    public List<Holiday> getHolidays() {
        return holidays;
    }
    
    public void setHolidays(List<Holiday> holidays) {
        this.holidays = holidays;
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
}
