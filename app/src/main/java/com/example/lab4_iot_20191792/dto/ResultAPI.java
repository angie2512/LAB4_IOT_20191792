package com.example.lab4_iot_20191792.dto;

import java.util.List;

public class ResultAPI {
    private List<Person> results;

    public List<Person> getResults() {
        return results;
    }

    public void setResults(List<Person> results) {
        this.results = results;
    }
}