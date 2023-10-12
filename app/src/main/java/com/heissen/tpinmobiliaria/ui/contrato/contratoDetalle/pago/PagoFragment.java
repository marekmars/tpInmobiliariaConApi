package com.heissen.tpinmobiliaria.ui.contrato.contratoDetalle.pago;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.heissen.tpinmobiliaria.R;
import com.heissen.tpinmobiliaria.databinding.FragmentPagoBinding;

public class PagoFragment extends Fragment {

    private PagoViewModel vm;
    private FragmentPagoBinding binding;

    public static PagoFragment newInstance() {
        return new PagoFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        vm = new ViewModelProvider(this).get(PagoViewModel.class);
        binding = FragmentPagoBinding.inflate(inflater, container, false);
        vm.getPagos(getArguments());

        RecyclerView rv = binding.rvPagos;
        GridLayoutManager gm = new GridLayoutManager(getContext(),1,RecyclerView.VERTICAL,false);
        rv.setLayoutManager(gm);

        vm.getInmuebles().observe(getViewLifecycleOwner(), inmuebles -> {
            rv.setAdapter(new PagosAdapter(getContext(),inmuebles,getLayoutInflater()));
        });

        return binding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        vm = new ViewModelProvider(this).get(PagoViewModel.class);
        // TODO: Use the ViewModel
    }

}