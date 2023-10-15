package com.heissen.tpinmobiliaria.ui.inmueble.inmuebleAgregar;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import com.heissen.tpinmobiliaria.R;
import com.heissen.tpinmobiliaria.databinding.ActivityLoginBinding;
import com.heissen.tpinmobiliaria.databinding.FragmentInmuebleAgregarBinding;
import com.heissen.tpinmobiliaria.models.Inmueble;
import com.heissen.tpinmobiliaria.models.TipoInmueble;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class InmuebleAgregarFragment extends Fragment {

    private InmuebleAgregarViewModel vm;
    private FragmentInmuebleAgregarBinding binding;
    private ActivityResultLauncher<Intent> imagePickerLauncher;


    public static InmuebleAgregarFragment newInstance() {
        return new InmuebleAgregarFragment();
    }

    private Uri imgUri;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = FragmentInmuebleAgregarBinding.inflate(getLayoutInflater());
        vm = new ViewModelProvider(this).get(InmuebleAgregarViewModel.class);
        vm.getmAdapterTipo().observe(getViewLifecycleOwner(), stringArrayAdapter -> binding.spinerTipo.setAdapter(stringArrayAdapter));
        vm.getmAdapterUso().observe(getViewLifecycleOwner(), stringArrayAdapter -> binding.spinerUso.setAdapter(stringArrayAdapter));
imgUri=Uri.parse("android.resource://" + getActivity().getPackageName() + "/" + R.drawable.inmueble_default);
        binding.imgNuevoInmueble.setImageResource(R.drawable.inmueble_default);

        binding.btnImg.setOnClickListener(v -> cargarImagen());
        ;
        imagePickerLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult result) {
                        imgUri = result.getData().getData();
                        Log.d("salida", imgUri.toString());
                        binding.imgNuevoInmueble.setImageURI(imgUri);
                    }
                }
        );


        binding.btnCrearInm.setOnClickListener(v -> {
            Inmueble inmueble = new Inmueble(
                    binding.spinerUso.getSelectedItemPosition(),
                    binding.spinerTipo.getSelectedItemPosition(),
                    Integer.parseInt(binding.etAmbientesCrear.getText().toString()), true,
                    binding.etDireccionCrear.getText().toString(), Double.parseDouble(binding.etPrecioCrear.getText().toString()),
                    "", 0);
            vm.crearInmueble(imgUri, inmueble);
            Navigation.findNavController(requireView()).navigate(R.id.nav_inmueble);
        });



        return binding.getRoot();

    }

    private void cargarImagen() {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("image/*");
        imagePickerLauncher.launch(intent);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        // TODO: Use the ViewModel
    }


}