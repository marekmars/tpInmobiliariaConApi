package com.heissen.tpinmobiliaria.ui.contrato.contratoDetalle;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.heissen.tpinmobiliaria.R;
import com.heissen.tpinmobiliaria.databinding.FragmentContratoDetalleBinding;
import com.heissen.tpinmobiliaria.databinding.FragmentInquilinoDetalleBinding;
import com.heissen.tpinmobiliaria.ui.inquilino.inquilinoDetalle.InquilinoDetalleViewModel;

public class ContratoDetalleFragment extends Fragment {

    private ContratoDetalleViewModel vm;
    private FragmentContratoDetalleBinding binding;

    public static ContratoDetalleFragment newInstance() {
        return new ContratoDetalleFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = FragmentContratoDetalleBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        vm = new ViewModelProvider(this).get(ContratoDetalleViewModel.class);
        vm.getContrato(getArguments());

        vm.getmContrato().observe(getViewLifecycleOwner(), contrato -> {
            binding.tvCodigoCD.setText(contrato.getId() + "");
            binding.tvFechaInicioCD.setText(String.valueOf(contrato.getFechaInicio()));
            binding.tvFechaFinCD.setText(String.valueOf(contrato.getFechaFin()));
            binding.tvMontoCD.setText(String.valueOf(contrato.getMensualidad() + ""));
            binding.tvInquilinoCD.setText(String.valueOf(contrato.getInquilino().toString()));
            binding.tvInmuebleCD.setText(String.valueOf(contrato.getInmueble().getDireccion()));
        });
        binding.btnPagos.setOnClickListener(v -> {
            Bundle bundle = new Bundle();
            bundle.putSerializable("contrato", vm.getmContrato().getValue());
            Navigation.findNavController(v).navigate(R.id.nav_pago, bundle);
        });
        return root;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        vm = new ViewModelProvider(this).get(ContratoDetalleViewModel.class);
        // TODO: Use the ViewModel
    }

}