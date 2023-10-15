package com.heissen.tpinmobiliaria.ui.contrato.contratoDetalle.pago;

import android.app.Application;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.heissen.tpinmobiliaria.models.Contrato;
import com.heissen.tpinmobiliaria.models.Inmueble;
import com.heissen.tpinmobiliaria.models.Pago;
import com.heissen.tpinmobiliaria.request.ApiClient;
import com.heissen.tpinmobiliaria.request.ApiService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PagoViewModel extends AndroidViewModel {
     private MutableLiveData<ArrayList<Pago>> mPagos;
    private Context context;

    public PagoViewModel(@NonNull Application application) {
        super(application);
        context = application.getApplicationContext();
        mPagos = new MutableLiveData<>();

    }

    public LiveData<ArrayList<Pago>> getmPagos() {
        return mPagos;
    }

    public void getPagos(Bundle bundle) {
        Contrato contrato = bundle.getSerializable("contrato",Contrato.class);
        String token = ApiService.leerToken(context);
        ApiService.ApiInterface apiService = ApiService.getApiInferface();
        Call<ArrayList<Pago>> llamada = apiService.obtenerPagos(token,contrato.getId());

        llamada.enqueue(new Callback<ArrayList<Pago>>() {
            @Override
            public void onResponse(Call<ArrayList<Pago>> call, Response<ArrayList<Pago>> response) {
                if (response.isSuccessful()) {
                    Log.d("salida", "SALIDA  " + response.body().toString());

                    mPagos.setValue(response.body());
                } else {
                    Log.d("salida", "ELSE " + response.raw().message());
                }
            }

            @Override
            public void onFailure(Call<ArrayList<Pago>> call, Throwable t) {
                Log.d("salida", "ERROR " + t.getMessage());
            }
        });

    }
}