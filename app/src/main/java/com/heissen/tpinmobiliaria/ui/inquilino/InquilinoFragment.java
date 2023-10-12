package com.heissen.tpinmobiliaria.ui.inquilino;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.heissen.tpinmobiliaria.R;
import com.heissen.tpinmobiliaria.databinding.FragmentInquilinoBinding;

public class InquilinoFragment extends Fragment {

    private FragmentInquilinoBinding binding;
    private InquilinoViewModel vm;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        vm =
                new ViewModelProvider(this).get(InquilinoViewModel.class);

        binding = FragmentInquilinoBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        vm.getmListInmueblesAlqu().observe(getViewLifecycleOwner(), inmuebles -> {
            RecyclerView rv=getActivity().findViewById(R.id.rvInmueblesAlq);
            GridLayoutManager gridLayoutManager=new GridLayoutManager(getActivity(), 2,GridLayoutManager.VERTICAL,false);
            rv.setLayoutManager(gridLayoutManager);
            InquilinoAdapter adapter=new InquilinoAdapter(getActivity(),inmuebles,getLayoutInflater());
            rv.setAdapter(adapter);

        });

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}