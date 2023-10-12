package com.heissen.tpinmobiliaria.ui.inmueble.inmuebleDetalle;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.heissen.tpinmobiliaria.R;
import com.heissen.tpinmobiliaria.databinding.FragmentInmuebleDetalleBinding;
import com.heissen.tpinmobiliaria.models.TipoInmueble;
import com.heissen.tpinmobiliaria.models.UsoInmueble;
import com.heissen.tpinmobiliaria.request.ApiService;

public class InmuebleDetalleFragment extends Fragment {

    private InmuebleDetalleViewModel vm;

    private FragmentInmuebleDetalleBinding binding;

    public static InmuebleDetalleFragment newInstance() {
        return new InmuebleDetalleFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = FragmentInmuebleDetalleBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        vm = new ViewModelProvider(this).get(InmuebleDetalleViewModel.class);

        vm.getInmuebles(getArguments());

        vm.getMInmueble().observe(getViewLifecycleOwner(), inmueble -> {
            binding.mySwitch.setChecked(inmueble.isDisponible());
            binding.tvAmbDetalle.setText(String.valueOf(inmueble.getAmbientes()));
            binding.tvDirDetalle.setText(inmueble.getDireccion());
            binding.tvCodigoDetalle.setText(inmueble.getId() + "");
            binding.tvPrecioDetalle.setText("$" + inmueble.getPrecio());
            binding.tvTipoDetalle.setText(TipoInmueble.values()[inmueble.getTipo()]+"");
            binding.tvUsoDetalle.setText(UsoInmueble.values()[inmueble.getUso()]+"");
            Log.d("salida",ApiService.URL_BASE+inmueble.getFoto());
            Glide.with(getContext())
                    .load(ApiService.URL_BASE+inmueble.getFoto())
                    .into(binding.imgInmueble);

        });
        vm.getmFotoInmueble().observe(getViewLifecycleOwner(),bitmap -> {
            binding.imgInmueble.setImageBitmap(bitmap);
        });
        vm.getmLabelSwich().observe(getViewLifecycleOwner(), s -> binding.labelSwich.setText(s));

        binding.mySwitch.setOnClickListener(v -> {
            vm.setEstado(vm.getMInmueble().getValue().getId());
        });
        return root;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        // TODO: Use the ViewModel
    }

}