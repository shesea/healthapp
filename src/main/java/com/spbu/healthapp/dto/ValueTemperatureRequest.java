package com.spbu.healthapp.dto;

import lombok.Data;

@Data
public class ValueTemperatureRequest {
    private String date;
    private String time;
    private int intPart;
    private int fracPart;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public int getIntPart() {
        return intPart;
    }

    public void setIntPart(int intPart) {
        this.intPart = intPart;
    }

    public int getFracPart() {
        return fracPart;
    }

    public void setFracPart(int fracPart) {
        this.fracPart = fracPart;
    }
}
