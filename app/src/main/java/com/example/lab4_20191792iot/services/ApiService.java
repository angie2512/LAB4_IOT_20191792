package com.example.lab4_20191792iot.services;

import com.example.lab4_20191792iot.dto.ResultAPI;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiService {

    @GET("/api/")
    Call<ResultAPI> random();

}
