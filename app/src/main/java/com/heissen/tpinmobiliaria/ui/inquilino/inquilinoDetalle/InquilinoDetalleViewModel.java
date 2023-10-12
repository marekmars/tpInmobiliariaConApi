package com.heissen.tpinmobiliaria.ui.inquilino.inquilinoDetalle;

import android.app.Application;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.heissen.tpinmobiliaria.models.Inmueble;
import com.heissen.tpinmobiliaria.models.Inquilino;
import com.heissen.tpinmobiliaria.request.ApiClient;

public class InquilinoDetalleViewModel extends AndroidViewModel {
    private MutableLiveData<Inquilino> mInquilino;

    public InquilinoDetalleViewModel(@NonNull Application application) {
        super(application);
        mInquilino = new MutableLiveData<>();

    }

    public LiveData<Inquilino> getmInquilino(){
        return mInquilino;
    }
    public void getInquilino(Bundle bundle){
        Inquilino inquilino = ApiClient.getApi().obtenerInquilino((Inmueble) bundle.getSerializable("inmuebleAlquInqui", Inmueble.class));
        this.mInquilino.setValue(inquilino);

    }
}