package com.heissen.tpinmobiliaria.ui.contrato.contratoDetalle.pago;

import android.app.Application;
import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.heissen.tpinmobiliaria.models.Contrato;
import com.heissen.tpinmobiliaria.models.Inmueble;
import com.heissen.tpinmobiliaria.models.Pago;
import com.heissen.tpinmobiliaria.request.ApiClient;

import java.util.ArrayList;
import java.util.List;

public class PagoViewModel extends AndroidViewModel {
    private ArrayList<Pago> listaPagos;
    private MutableLiveData<ArrayList<Pago>> pagos;
    private Context context;

    public PagoViewModel(@NonNull Application application) {
        super(application);
        context = application.getApplicationContext();
        pagos = new MutableLiveData<>();
    }

    public LiveData<ArrayList<Pago>> getInmuebles() {
        return pagos;
    }

    public void getPagos(Bundle bundle) {
        Contrato contrato = (Contrato) bundle.getSerializable("contrato",Contrato.class);
        listaPagos = ApiClient.getApi().obtenerPagos(contrato);
        pagos.setValue(listaPagos);
    }
}