package com.itraccoon.object;

import java.text.ParseException;
import java.util.Date;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "holiday")
@XmlAccessorType(XmlAccessType.FIELD)
public class Holiday {
    
    private Date    start;
    private String  startAsString;
    private Date    end;
    private String  endAsString;
    private Integer amountOfDays;
    private Integer amountOfDaysMinusWeekends;
    
    public Holiday() {
    }
    
    public Holiday(Date start, Date end, Integer amountOfDays) throws ParseException {
        super();
        this.start = start;
        this.startAsString = new FDate(start).getDateAsString();
        this.end = end;
        this.endAsString = new FDate(end).getDateAsString();
        this.amountOfDays = amountOfDays;
        this.amountOfDaysMinusWeekends = calculateRealDays(start, end);
    }
    
    private Integer calculateRealDays(Date start2, Date end2) {
        // TODO
        return null;
    }
    
    public Date getStart() {
        return start;
    }
    
    public void setStart(Date start) {
        this.start = start;
    }
    
    public String getStartAsString() {
        return startAsString;
    }
    
    public void setStartAsString(String startAsString) {
        this.startAsString = startAsString;
    }
    
    public Date getEnd() {
        return end;
    }
    
    public void setEnd(Date end) {
        this.end = end;
    }
    
    public String getEndAsString() {
        return endAsString;
    }
    
    public void setEndAsString(String endAsString) {
        this.endAsString = endAsString;
    }
    
    public Integer getAmount() {
        return amountOfDays;
    }
    
    public void setAmount(Integer amount) {
        this.amountOfDays = amount;
    }
    
    public Integer getAmountOfDaysMinusWeekends() {
        return amountOfDaysMinusWeekends;
    }
    
    public void setAmountOfDaysMinusWeekends(Integer amountOfDaysMinusWeekends) {
        this.amountOfDaysMinusWeekends = amountOfDaysMinusWeekends;
    }
    
}
