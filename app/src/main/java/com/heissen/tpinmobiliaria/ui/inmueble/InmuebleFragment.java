package com.heissen.tpinmobiliaria.ui.inmueble;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.heissen.tpinmobiliaria.R;
import com.heissen.tpinmobiliaria.databinding.FragmentInmuebleBinding;


public class InmuebleFragment extends Fragment {

    private FragmentInmuebleBinding binding;
    private InmuebleViewModel vm;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        vm =
                new ViewModelProvider(this).get(InmuebleViewModel.class);

        binding = FragmentInmuebleBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
vm.cargarInmuebles();
        vm.getmListInmuebles().observe(getViewLifecycleOwner(), inmuebles -> {
            RecyclerView rv=getActivity().findViewById(R.id.rvInmuebles);
            GridLayoutManager gridLayoutManager=new GridLayoutManager(getActivity(), 2,GridLayoutManager.VERTICAL,false);
            rv.setLayoutManager(gridLayoutManager);
            InmuebleAdapter adapter=new InmuebleAdapter(inmuebles,getActivity(),getLayoutInflater());
            rv.setAdapter(adapter);
        });
binding.btnAgregarInm.setOnClickListener(v -> Navigation.findNavController(v).navigate(R.id.nav_Agregar_Fragment));

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}