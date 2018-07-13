package com.itraccoon.test;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;

import com.itraccoon.filehandling.SaveUserlist;
import com.itraccoon.main.Runtime;
import com.itraccoon.object.FDate;
import com.itraccoon.object.Holiday;
import com.itraccoon.object.User;

public class TesterClass {
    
    public TesterClass() throws ParseException {
        User user = new User();
        user.setName("Michi");
        user.setLastName("Test");
        user.setDaysPerYear(30);
        user.setDaysAvailable(20);
        user.setDaysSpent(10);
        user.setId(1);
        user.setHolidays(new ArrayList<Holiday>());
        
        Holiday holi = new Holiday();
        holi.setAmount(12);
        holi.setAmountOfDaysMinusWeekends(8);
        holi.setEnd(new Date());
        holi.setStart(new Date());
        holi.setEndAsString(new FDate(new Date()).getDateAsString());
        holi.setStartAsString(new FDate(new Date()).getDateAsString());
        user.getHolidays().add(holi);
        
        Holiday holi2 = new Holiday();
        holi2.setAmount(13);
        holi2.setAmountOfDaysMinusWeekends(1);
        holi2.setEnd(new Date());
        holi2.setStart(new Date());
        holi2.setEndAsString(new FDate(new Date()).getDateAsString());
        holi2.setStartAsString(new FDate(new Date()).getDateAsString());
        user.getHolidays().add(holi2);
        
        Runtime.getInstance().addUserToList(user);
        new SaveUserlist();
    }
}
