package com.heissen.tpinmobiliaria.ui.contrato.contratoDetalle;

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
import com.heissen.tpinmobiliaria.models.Inquilino;
import com.heissen.tpinmobiliaria.request.ApiClient;
import com.heissen.tpinmobiliaria.request.ApiService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ContratoDetalleViewModel extends AndroidViewModel {
    private MutableLiveData<Contrato> mContrato;
    Context context;

    public ContratoDetalleViewModel(@NonNull Application application) {
        super(application);
        mContrato = new MutableLiveData<>();
        context=application;


    }
    public LiveData<Contrato> getmContrato(){
        return mContrato;
    }
    public void getContrato(Bundle bundle){
        String token = ApiService.leerToken(context);
        ApiService.ApiInterface apiService = ApiService.getApiInferface();
        Call<Contrato> llamada = apiService.obtenerContrato(token,bundle.getSerializable("inmuebleAlqu",Inmueble.class).getId());

        llamada.enqueue(new Callback<Contrato>() {
            @Override
            public void onResponse(Call<Contrato> call, Response<Contrato> response) {
                if (response.isSuccessful()) {
                    Log.d("salida", "SALIDA  " + response.body().toString());
                    mContrato.setValue(response.body());
                } else {
                    Log.d("salida", "ELSE " + response.raw().message());
                }
            }

            @Override
            public void onFailure(Call<Contrato> call, Throwable t) {
                Log.d("salida", "ERROR " + t.getMessage());
            }
        });
    }
}