package com.heissen.tpinmobiliaria;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import com.heissen.tpinmobiliaria.databinding.ActivityRecuperoBinding;

public class RecuperoActivity extends AppCompatActivity {
    private RecuperoActivityViewModel vm;
    private ActivityRecuperoBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        vm = ViewModelProvider.AndroidViewModelFactory.getInstance(getApplication()).create(RecuperoActivityViewModel.class);
        binding = ActivityRecuperoBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.btnRecuperar.setOnClickListener(v -> {
            vm.pedidoRecuperoClave(binding.etCorreoRecupero.getText().toString());
            DialogoFragment dialog = new DialogoFragment();

            dialog.show(getSupportFragmentManager(), "DialogPersonalizado");
        });

    }
}