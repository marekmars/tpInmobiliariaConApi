package com.heissen.tpinmobiliaria;

import android.app.Application;
import android.content.Context;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.heissen.tpinmobiliaria.models.Propietario;
import com.heissen.tpinmobiliaria.request.ApiClient;

public class MenuActivityViewModel extends AndroidViewModel {
    private Context context;
    private MutableLiveData<Propietario> mPropietario;

    public MenuActivityViewModel(@NonNull Application application) {
        super(application);
        mPropietario = new MutableLiveData<>();
    }

    public LiveData<Propietario> getmPropietario() {
        return mPropietario;
    }

   /* public void cargarUser() {
        Propietario propietario= ApiClient.getApi().obtenerUsuarioActual();
        if (propietario != null) {
            mPropietario.setValue(propietario);
        }else{

            Toast.makeText(context, "ERROR AL OBTENER EL USUARIO", Toast.LENGTH_SHORT).show();
        }
    }*/

}
