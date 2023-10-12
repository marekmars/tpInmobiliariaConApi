package com.heissen.tpinmobiliaria.ui.contrato.contratoDetalle;

import android.app.Application;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.heissen.tpinmobiliaria.models.Contrato;
import com.heissen.tpinmobiliaria.models.Inmueble;
import com.heissen.tpinmobiliaria.models.Inquilino;
import com.heissen.tpinmobiliaria.request.ApiClient;

public class ContratoDetalleViewModel extends AndroidViewModel {
    private MutableLiveData<Contrato> mContrato;

    public ContratoDetalleViewModel(@NonNull Application application) {
        super(application);
        mContrato = new MutableLiveData<>();

    }

    public LiveData<Contrato> getmContrato(){
        return mContrato;
    }
    public void getContrato(Bundle bundle){
        Contrato contrato = ApiClient.getApi().obtenerContratoVigente((Inmueble) bundle.getSerializable("inmuebleAlqu", Inmueble.class));
        this.mContrato.setValue(contrato);
    }
}