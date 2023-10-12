package com.heissen.tpinmobiliaria.ui.logout;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.heissen.tpinmobiliaria.R;
import com.heissen.tpinmobiliaria.databinding.FragmentContratoBinding;
import com.heissen.tpinmobiliaria.databinding.FragmentLogoutBinding;
import com.heissen.tpinmobiliaria.models.Dialogo;
import com.heissen.tpinmobiliaria.ui.contrato.ContratoViewModel;


public class LogoutFragment extends Fragment {

    private FragmentLogoutBinding binding;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        AlertDialog dialog = Dialogo.logout(getActivity());

        // Establece un OnClickListener personalizado para el botón "Cancelar"
        dialog.setButton(Dialog.BUTTON_NEGATIVE, "Cancelar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                // Cierra el fragmento actual al hacer clic en "Cancelar"
                NavController navController = Navigation.findNavController(requireActivity(), R.id.nav_host_fragment_content_main);

                // Navega al fragmento de inicio (home)
                navController.navigate(R.id.nav_ubicacion);
            }
        });

        binding = FragmentLogoutBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        dialog.show();
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}