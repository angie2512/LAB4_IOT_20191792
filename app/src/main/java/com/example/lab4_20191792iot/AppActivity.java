package com.example.lab4_20191792iot;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.PopUpToBuilder;

import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;


import com.example.lab4_20191792iot.acelerometro.PersonasAcelerometroVM;
import com.example.lab4_20191792iot.databinding.ActivityAppBinding;
import com.example.lab4_20191792iot.dto.Person;
import com.example.lab4_20191792iot.dto.ResultAPI;
import com.example.lab4_20191792iot.magnetometro.PersonasMagnetometroVM;
import com.example.lab4_20191792iot.services.ApiService;
import com.example.lab4_20191792iot.viewModels.AppActivityViewModel;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class AppActivity extends AppCompatActivity {
    ActivityAppBinding binding;

    String textoMagnetometro = "Ir al Magnetómetro";
    String textoAcelerometro = "Ir al Acelerómetro";
    ApiService servicioPersonas = new Retrofit.Builder()
            .baseUrl("https://randomuser.me")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiService.class);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAppBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        AppActivityViewModel appActivityViewModel = new ViewModelProvider(AppActivity.this).get(AppActivityViewModel.class);
        PersonasAcelerometroVM personasAcelerometroVM = new ViewModelProvider(AppActivity.this).get(PersonasAcelerometroVM.class);
        PersonasMagnetometroVM personasMagnetometroVM = new ViewModelProvider(AppActivity.this).get(PersonasMagnetometroVM.class);

        personasMagnetometroVM.getListaPersonasMagnetometro().postValue(new ArrayList<>());
        personasAcelerometroVM.getListaPersonasAcelerometro().postValue(new ArrayList<>());
        appActivityViewModel.getVistaActual().postValue("Inicio");

        binding.ingresar.setOnClickListener(view -> {
            if(binding.ingresar.getText().toString().equals(textoAcelerometro)){
                appActivityViewModel.getVistaActual().postValue("Acelerómetro");
                binding.ingresar.setText(textoMagnetometro);
            }else{
                appActivityViewModel.getVistaActual().postValue("Magnetómetro");
                binding.ingresar.setText(textoAcelerometro);
            }
        });

        binding.ojo.setOnClickListener(view -> {
            Log.d("msg-test-nombre"," "+binding.ingresar.getText().toString());
            if (binding.ingresar.getText().toString().equals(""+textoMagnetometro)){
                // Estoy en el Acelerómetro
                mostrarAlertaAcelerometro();
            }else{
                // Estoy en el Magnetómetro
                mostrarAlertaMagnetometro();
            }
        });

        binding.anadir.setOnClickListener(view -> {
            binding.anadir.setEnabled(false);
            binding.anadir.setEnabled(false);
            if (binding.anadir.getText().toString().equals(textoMagnetometro)){
                // Estoy en el Acelerómetro
                servicioPersonas.random().enqueue(new Callback<ResultAPI>() {
                    @Override
                    public void onResponse(Call<ResultAPI> call, Response<ResultAPI> response) {
                        if (response.isSuccessful()){
                            ArrayList<Person> listaUsuariosAcelerómetro = personasAcelerometroVM.getListaPersonasAcelerometro().getValue();
                            Person persona = response.body().getResults().get(0);
                            listaUsuariosAcelerómetro.add(persona);
                            personasAcelerometroVM.getListaPersonasAcelerometro().postValue(listaUsuariosAcelerómetro);
                            binding.ingresar.setEnabled(true);
                            binding.anadir.setEnabled(true);
                            ;
                        }
                    }
                    @Override
                    public void onFailure(Call<ResultAPI> call, Throwable t) {
                    }
                });

            }else{
                // Estoy en el magnetómetro
                servicioPersonas.random().enqueue(new Callback<ResultAPI>() {
                    @Override
                    public void onResponse(Call<ResultAPI> call, Response<ResultAPI> response) {
                        if (response.isSuccessful()){
                            ArrayList<Person> listaUsuariosMagnetómetro = personasMagnetometroVM.getListaPersonasMagnetometro().getValue();
                            Person persona = response.body().getResults().get(0);
                            listaUsuariosMagnetómetro.add(persona);
                            personasMagnetometroVM.getListaPersonasMagnetometro().postValue(listaUsuariosMagnetómetro);
                            binding.anadir.setEnabled(true);
                            binding.ingresar.setEnabled(true);
                        }
                    }
                    @Override
                    public void onFailure(Call<ResultAPI> call, Throwable t) {
                    }
                });


            }
        });
    }

    public void mostrarAlertaMagnetometro(){
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
        alertDialog.setTitle("Detalles - Magnetómetro");
        alertDialog.setMessage("Haga CLICK en 'Añadir' para agregar contactos a su lista."+
                " Esta aplicación está utilizando el MAGNETÓMETRO de su dispositivo.\n\n"+
                "De esta forma, la lista se mostrará el 100% cuando se apunte al NORTE. "+
                "Caso contrario, se desvanecerá...");
        alertDialog.setPositiveButton("Aceptar",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Log.d("msgAlerta", "Positivo");
                    }
                });
        alertDialog.show();
    }
    public void mostrarAlertaAcelerometro(){
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
        alertDialog.setTitle("Detalles - Acelerómetro");
        alertDialog.setMessage("Haga CLICK en 'Añadir' para agregar contactos a su lista."+
                " Esta aplicación está utilizando el ACELERÓMETRO de su dispositivo.\n\n"+
                "De esta forma, la lista hará scroll hacia abajo, cuando agite su dispositivo.");
        alertDialog.setPositiveButton("Aceptar",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Log.d("msgAlerta", "Positivo");
                    }
                });
        alertDialog.show();
    }
}