package com.example.lab4_20191792iot.dto;

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