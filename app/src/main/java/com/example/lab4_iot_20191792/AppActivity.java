package com.example.lab4_iot_20191792;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.lab4_iot_20191792.databinding.ActivityMainBinding;
import com.example.lab4_iot_20191792.fragments.AcelerometroFragment;

public class AppActivity extends AppCompatActivity {

    ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        if(savedInstanceState == null){
            getSupportFragmentManager().beginTransaction()
                    .setReorderingAllowed(true)
                    .add(R.id.fragmentContainerView, AcelerometroFragment.class, null)
                    .commit();
        }
    }
}