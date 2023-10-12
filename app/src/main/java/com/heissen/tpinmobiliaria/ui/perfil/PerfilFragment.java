package com.heissen.tpinmobiliaria.ui.perfil;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;


import com.heissen.tpinmobiliaria.databinding.FragmentPerfilBinding;
import com.heissen.tpinmobiliaria.models.Propietario;

public class PerfilFragment extends Fragment {

    private PerfilViewModel vm;
    private FragmentPerfilBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        vm =
                new ViewModelProvider(this).get(PerfilViewModel.class);

        binding = FragmentPerfilBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        vm.getmPropietario().observe(getViewLifecycleOwner(), propietario -> {
            Log.d("salida", "IDAVATAR: " + propietario.getAvatar());
            binding.etCodigo.setText(propietario.getId() + "");
            binding.etDni.setText(propietario.getDni() + "");
            binding.etNombre.setText(propietario.getNombre());
            binding.etApellido.setText(propietario.getApellido());
            binding.etCorreo.setText(propietario.getCorreo());
            binding.etTelefono.setText(propietario.getTelefono());
            binding.imgAvatar.setImageResource(propietario.getAvatar());
        });



        vm.getActivo().observe(getViewLifecycleOwner(), activo -> {
            binding.etDni.setEnabled(activo);
            binding.etNombre.setEnabled(activo);
            binding.etApellido.setEnabled(activo);
            binding.etCorreo.setEnabled(activo);
            binding.etClave.setEnabled(activo);
            binding.etTelefono.setEnabled(activo);
        });

        vm.getmBtn().observe(getViewLifecycleOwner(),s -> {
            binding.btnEditar.setText(s);
        });

        binding.btnEditar.setOnClickListener(v -> {

            vm.editarPerfil(new Propietario(Integer.parseInt(binding.etCodigo.getText().toString())
                    , binding.etApellido.getText().toString()
                    , binding.etNombre.getText().toString()
                    , binding.etDni.getText().toString()
                    , binding.etTelefono.getText().toString()
                    , binding.etCorreo.getText().toString()
                    , binding.etClave.getText().toString()
                    , vm.getmPropietario().getValue().getAvatar())
            );


        });
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}