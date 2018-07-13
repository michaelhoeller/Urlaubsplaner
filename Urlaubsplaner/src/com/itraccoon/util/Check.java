package com.itraccoon.util;

public class Check {
    
    public static boolean isNotNullOrEmpty(String stringToTest) {
        if ((stringToTest == null) || stringToTest.trim().equals("")) {
            return false;
        }
        else {
            return true;
        }
    }
    
    public static boolean isNullOrEmpty(String stringToTest) {
        return !isNotNullOrEmpty(stringToTest);
    }
    
}
