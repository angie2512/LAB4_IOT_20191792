package com.example.lab4_iot_20191792.acelerometro;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.lab4_iot_20191792.dto.Person;

import java.util.ArrayList;

public class PersonasAcelerometroVM extends ViewModel {
    private final MutableLiveData<ArrayList<Person>> listaPersonasAcelerometro = new MutableLiveData<ArrayList<Person>>();

    public MutableLiveData<ArrayList<Person>> getListaPersonasAcelerometro() {
        return listaPersonasAcelerometro;
    }
}
