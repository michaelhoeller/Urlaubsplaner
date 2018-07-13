package com.itraccoon.object;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.itraccoon.constants.Constants;

public class FDate {
    
    private Date   date;
    private String dateAsString;
    
    public FDate(Date date) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat(Constants.DATEFORMAT);
        this.dateAsString = sdf.format(date);
        this.date = sdf.parse(dateAsString);
    }
    
    public Date getDate() {
        return date;
    }
    
    public void setDate(Date date) {
        this.date = date;
    }
    
    public String getDateAsString() {
        return dateAsString;
    }
    
    public void setDateAsString(String dateAsString) {
        this.dateAsString = dateAsString;
    }
    
}
