package com.example.lab4_20191792iot;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

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
    ApiService apiService = new Retrofit.Builder()
            .baseUrl("https://randomuser.me")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiService.class);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAppBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        AppActivityViewModel vistaviewmodel= new ViewModelProvider(AppActivity.this).get(AppActivityViewModel.class);
        PersonasAcelerometroVM personasAcelerometroVM = new ViewModelProvider(AppActivity.this).get(PersonasAcelerometroVM.class);
        PersonasMagnetometroVM personasMagnetometroVM = new ViewModelProvider(AppActivity.this).get(PersonasMagnetometroVM.class);

        personasMagnetometroVM.getListaPersonasMagnetometro().postValue(new ArrayList<>());
        personasAcelerometroVM.getListaPersonasAcelerometro().postValue(new ArrayList<>());
        vistaviewmodel.getVistaActual().postValue("Inicio");

        binding.ingresar.setOnClickListener(view -> {
            if(binding.ingresar.getText().toString().equals(textoAcelerometro)){
                vistaviewmodel.getVistaActual().postValue("Acelerómetro");
                binding.ingresar.setText(textoMagnetometro);
            }else{
                vistaviewmodel.getVistaActual().postValue("Magnetómetro");
                binding.ingresar.setText(textoAcelerometro);
            }
        });
        binding.anadir.setOnClickListener(view -> {
            binding.anadir.setEnabled(false);
            binding.ingresar.setEnabled(false);
            if (binding.ingresar.getText().toString().equals(textoMagnetometro)){
                apiService.random().enqueue(new Callback<ResultAPI>() {
                    @Override
                    public void onResponse(Call<ResultAPI> call, Response<ResultAPI> response) {
                        if (response.isSuccessful()){
                            ArrayList<Person> listaUsuariosAcelerometro = personasAcelerometroVM.getListaPersonasAcelerometro().getValue();
                            Person persona = response.body().getResults().get(0);
                            listaUsuariosAcelerometro.add(persona);
                            personasAcelerometroVM.getListaPersonasAcelerometro().postValue(listaUsuariosAcelerometro);
                            binding.anadir.setEnabled(true);
                            binding.ingresar.setEnabled(true);
                        }
                    }
                    @Override
                    public void onFailure(Call<ResultAPI> call, Throwable t) {
                    }
                });

            }else{
                apiService.random().enqueue(new Callback<ResultAPI>() {
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
}