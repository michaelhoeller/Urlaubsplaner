package com.itraccoon.main;

import java.util.ArrayList;
import java.util.List;

import com.itraccoon.object.User;

public class Runtime {
    
    private static Runtime instance;
    
    private Boolean        dataThere;
    private List<User>     userlist;
    
    private Integer        nextUserId;
    
    private Runtime() {
        setDataThere(false);
    }
    
    public static Runtime getInstance() {
        if (instance == null) {
            instance = new Runtime();
        }
        return instance;
    }
    
    public void addUserToList(User user) {
        user.setId(getNextUserId());
        if (userlist == null) {
            userlist = new ArrayList<User>();
        }
        getUserlist().add(user);
        setDataThere(true);
    }
    
    public List<User> getUserlist() {
        return userlist;
    }
    
    public void setUserlist(List<User> userlist) {
        setDataThere(true);
        this.userlist = userlist;
    }
    
    public Integer getNextUserId() {
        if (nextUserId == null) {
            nextUserId = 1;
        }
        return nextUserId++;
    }
    
    public void setNextUserId(Integer userId) {
        this.nextUserId = userId;
    }
    
    public Boolean isDataThere() {
        return dataThere;
    }
    
    public void setDataThere(Boolean dataThere) {
        this.dataThere = dataThere;
    }
}
