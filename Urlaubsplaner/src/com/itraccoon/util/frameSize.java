package com.itraccoon.util;

public enum frameSize {
    QUARTER_SCREEN(4), HALF_SCREEN(2), FULL_SCREEN(1);
    
    private int size;
    
    frameSize(int size) {
        this.size = size;
    }
    
    public int size() {
        return size;
    }
    
}
