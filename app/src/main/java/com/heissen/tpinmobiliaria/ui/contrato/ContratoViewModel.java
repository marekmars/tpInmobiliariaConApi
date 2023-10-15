package com.heissen.tpinmobiliaria.ui.contrato;

import android.app.Application;
import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.heissen.tpinmobiliaria.models.Inmueble;
import com.heissen.tpinmobiliaria.request.ApiClient;
import com.heissen.tpinmobiliaria.request.ApiService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ContratoViewModel extends AndroidViewModel {
    private MutableLiveData<List<Inmueble>> mListInmueblesAlqu;
    Context context;

    public ContratoViewModel(@NonNull Application application) {
        super(application);
        mListInmueblesAlqu = new MutableLiveData<>();
        context = application;
        cargarInmuebles();
    }

    public LiveData<List<Inmueble>> getmListInmueblesAlqu() {
        return mListInmueblesAlqu;
    }

    public void cargarInmuebles() {
        String token = ApiService.leerToken(context);
        ApiService.ApiInterface apiService = ApiService.getApiInferface();
        Call<List<Inmueble>> llamada = apiService.obtenerInmueblesAlq(token);

        llamada.enqueue(new Callback<List<Inmueble>>() {
            @Override
            public void onResponse(Call<List<Inmueble>> call, Response<List<Inmueble>> response) {
                if (response.isSuccessful()) {
                    mListInmueblesAlqu.setValue(response.body());
                } else {
                    Log.d("salida", "ELSE " + response.raw().message());
                }
            }

            @Override
            public void onFailure(Call<List<Inmueble>> call, Throwable t) {
                Log.d("salida", "ERROR " + t.getMessage());
            }
        });
    }
}