package com.example.lab4_iot_20191792.dto;

import java.io.Serializable;

public class Dob implements Serializable {

    private String date;
    private Integer age;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
}
