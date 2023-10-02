package com.example.lab4_iot_20191792.viewModels;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class AppActivityViewModel extends ViewModel {

    private final MutableLiveData<String> vistaActual = new MutableLiveData<>();

    public MutableLiveData<String> getVistaActual() {
        return vistaActual;
    }
}
