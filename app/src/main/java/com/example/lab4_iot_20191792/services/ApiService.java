package com.example.lab4_iot_20191792.services;

import com.example.lab4_iot_20191792.dto.Person;
import com.example.lab4_iot_20191792.dto.ResultAPI;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiService {

    @GET("/api/")
    Call<ResultAPI> random();

}
