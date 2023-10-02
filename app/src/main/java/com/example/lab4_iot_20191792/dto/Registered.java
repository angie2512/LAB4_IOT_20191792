package com.example.lab4_iot_20191792.dto;

import java.io.Serializable;
import java.util.Date;

public class Registered implements Serializable {

    private Date date;
    private int age;

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
