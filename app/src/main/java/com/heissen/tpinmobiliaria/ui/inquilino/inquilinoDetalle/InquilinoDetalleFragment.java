package com.heissen.tpinmobiliaria.ui.inquilino.inquilinoDetalle;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.heissen.tpinmobiliaria.R;
import com.heissen.tpinmobiliaria.databinding.FragmentInmuebleDetalleBinding;
import com.heissen.tpinmobiliaria.databinding.FragmentInquilinoDetalleBinding;
import com.heissen.tpinmobiliaria.ui.inmueble.inmuebleDetalle.InmuebleDetalleViewModel;

public class InquilinoDetalleFragment extends Fragment {

    private InquilinoDetalleViewModel vm;
    private FragmentInquilinoDetalleBinding binding;

    public static InquilinoDetalleFragment newInstance() {
        return new InquilinoDetalleFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        binding = FragmentInquilinoDetalleBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        vm = new ViewModelProvider(this).get(InquilinoDetalleViewModel.class);
        vm.getInquilino(getArguments());

      /*  vm.getmInquilino().observe(getViewLifecycleOwner(), inquilino -> {
            binding.tvCodigoFI.setText(inquilino.getIdInquilino()+"");
            binding.tvApellidoFI.setText(String.valueOf(inquilino.getApellido()));
            binding.tvNombreFI.setText(String.valueOf(inquilino.getNombre()));
            binding.tvDniFI.setText(String.valueOf(inquilino.getDNI()+""));
            binding.tvEmailFI.setText(String.valueOf(inquilino.getEmail()));
            binding.tvTelFI.setText(String.valueOf(inquilino.getTelefono()+""));
            binding.tvGaranteFI.setText(String.valueOf(inquilino.getNombreGarante()));
            binding.tvTelGaranteFI.setText(String.valueOf(inquilino.getTelefonoGarante()+""));
        });*/

        return root;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        vm = new ViewModelProvider(this).get(InquilinoDetalleViewModel.class);
        // TODO: Use the ViewModel
    }

}