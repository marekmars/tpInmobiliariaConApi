package com.heissen.tpinmobiliaria.ui.contrato;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.heissen.tpinmobiliaria.models.Inmueble;
import com.heissen.tpinmobiliaria.request.ApiClient;

import java.util.List;

public class ContratoViewModel extends AndroidViewModel {
    private MutableLiveData<List<Inmueble>> mListInmueblesAlqu;

    public ContratoViewModel(@NonNull Application application) {
        super(application);
        mListInmueblesAlqu=new MutableLiveData<>();
        cargarInmuebles();

    }

    public LiveData<List<Inmueble>> getmListInmueblesAlqu() {
        return mListInmueblesAlqu;
    }
    public void cargarInmuebles(){
        List<Inmueble>listinm;
        listinm= ApiClient.getApi().obtenerPropiedadesAlquiladas();
        mListInmueblesAlqu.setValue(listinm);

    }
}