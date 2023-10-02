package com.example.lab4_iot_20191792.magnetometro;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;


import com.example.lab4_iot_20191792.dto.Person;

import java.util.ArrayList;

public class PersonasMagnetometroVM extends ViewModel {
    private final MutableLiveData<ArrayList<Person>> listaPersonasMagnetometro = new MutableLiveData<ArrayList<Person>>();

    public MutableLiveData<ArrayList<Person>> getListaPersonasMagnetometro() {
        return listaPersonasMagnetometro;
    }
}
