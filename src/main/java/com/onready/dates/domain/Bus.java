package com.onready.dates.domain;

import java.time.LocalDateTime;


public class Bus {
    
    private int line;
    private LocalDateTime arrivalTime;

    public int getLine() {
        return line;
    }

    public void setLine(int line) {
        this.line = line;
    }

    public LocalDateTime getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(LocalDateTime arrivalTime) {
        this.arrivalTime = arrivalTime;
    }
}
