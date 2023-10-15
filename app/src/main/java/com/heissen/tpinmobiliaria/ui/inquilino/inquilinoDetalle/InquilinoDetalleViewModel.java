package com.heissen.tpinmobiliaria.ui.inquilino.inquilinoDetalle;

import android.app.Application;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.heissen.tpinmobiliaria.models.Inmueble;
import com.heissen.tpinmobiliaria.models.Inquilino;
import com.heissen.tpinmobiliaria.request.ApiClient;
import com.heissen.tpinmobiliaria.request.ApiService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class InquilinoDetalleViewModel extends AndroidViewModel {
    private MutableLiveData<Inquilino> mInquilino;
    private Context context;

    public InquilinoDetalleViewModel(@NonNull Application application) {
        super(application);
        mInquilino = new MutableLiveData<>();
        context=application;

    }

    public LiveData<Inquilino> getmInquilino(){
        return mInquilino;
    }
    public void getInquilino(Bundle bundle){
        String token = ApiService.leerToken(context);
        ApiService.ApiInterface apiService = ApiService.getApiInferface();
        Call<Inquilino> llamada = apiService.obtenerInquiloAct(token,bundle.getSerializable("inmuebleAlquInqui", Inmueble.class).getId());
        llamada.enqueue(new Callback<Inquilino>() {
            @Override
            public void onResponse(Call<Inquilino> call, Response<Inquilino> response) {
                if (response.isSuccessful()) {
                    mInquilino.setValue(response.body());
                    Log.d("salida",response.body().toString());
                } else {
                    Log.d("salida", "ELSE " + response.raw().message());
                }
            }
            @Override
            public void onFailure(Call<Inquilino> call, Throwable t) {
                Log.d("salida", "ERROR " + t.getMessage());
            }
        });


    }
}