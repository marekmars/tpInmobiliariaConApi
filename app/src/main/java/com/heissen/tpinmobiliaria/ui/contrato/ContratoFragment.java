package com.heissen.tpinmobiliaria.ui.contrato;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.heissen.tpinmobiliaria.R;
import com.heissen.tpinmobiliaria.databinding.FragmentContratoBinding;
import com.heissen.tpinmobiliaria.databinding.FragmentInquilinoBinding;
import com.heissen.tpinmobiliaria.ui.inquilino.InquilinoAdapter;
import com.heissen.tpinmobiliaria.ui.inquilino.InquilinoViewModel;

public class ContratoFragment extends Fragment {

    private FragmentContratoBinding binding;
    private ContratoViewModel vm;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        vm =
                new ViewModelProvider(this).get(ContratoViewModel.class);

        binding = FragmentContratoBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        vm.getmListInmueblesAlqu().observe(getViewLifecycleOwner(), inmuebles -> {
            RecyclerView rv=getActivity().findViewById(R.id.rvInmueblesAlqContrato);
            GridLayoutManager gridLayoutManager=new GridLayoutManager(getActivity(), 2,GridLayoutManager.VERTICAL,false);
            rv.setLayoutManager(gridLayoutManager);
            ContratoAdapter adapter=new ContratoAdapter(getActivity(),inmuebles,getLayoutInflater());
            rv.setAdapter(adapter);
        });

        return root;
    }



}