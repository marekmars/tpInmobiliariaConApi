package com.heissen.tpinmobiliaria.models;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.Navigation;

import com.heissen.tpinmobiliaria.request.ApiService;

public class Dialogo {
       public static AlertDialog logout(Activity activity) {
        return new AlertDialog.Builder(activity)
                .setTitle("Esta por cerrar la aplicacion")
                .setMessage("Cerrar la aplicacion")
                .setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        ApiService.eliminarToken(activity.getApplicationContext());
                        ((AppCompatActivity)activity).finishAndRemoveTask();
                        activity.finishAffinity();
                    }
                })
                .setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                })
                .create();
    }


}