package com.heissen.tpinmobiliaria;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;

import com.heissen.tpinmobiliaria.databinding.ActivityLoginBinding;
import com.heissen.tpinmobiliaria.models.Dialogo;

public class LoginActivity extends AppCompatActivity {
    private ActivityLoginBinding binding;
    private LoginActivityViewModel vm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        vm = ViewModelProvider.AndroidViewModelFactory.getInstance(getApplication()).create(LoginActivityViewModel.class);
        vm.getMerror().observe(this, s -> {
            binding.tvErrorLogin.setText(s);
        });

        binding.btnLogin.setOnClickListener(v -> vm.login(binding.etCorreoLogin.getText().toString()
                , binding.etClaveLogin.getText().toString()));
        binding.btnRecupero.setOnClickListener(v -> vm.iniciarRecupero());

        solicitarPermisos();



    }
    @Override
    protected void onResume() {
        super.onResume();
        vm.iniciarDeteccionDeAgitacion();
    }

    @Override
    protected void onPause() {
        super.onPause();
        vm.detenerDeteccionDeAgitacion();
    }

    private void solicitarPermisos() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M
                && checkSelfPermission(android.Manifest.permission.CALL_PHONE)
                != PackageManager.PERMISSION_GRANTED&& checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED&& checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(new String[]{android.Manifest.permission.CALL_PHONE}, 1000);
            requestPermissions(new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION}, 1001);
            requestPermissions(new String[]{android.Manifest.permission.READ_EXTERNAL_STORAGE}, 1002);
        }
    }



}