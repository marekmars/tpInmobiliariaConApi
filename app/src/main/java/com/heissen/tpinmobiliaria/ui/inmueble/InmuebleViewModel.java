package com.heissen.tpinmobiliaria.ui.inmueble;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.heissen.tpinmobiliaria.MenuActivity;
import com.heissen.tpinmobiliaria.models.Inmueble;
import com.heissen.tpinmobiliaria.models.Propietario;
import com.heissen.tpinmobiliaria.request.ApiClient;
import com.heissen.tpinmobiliaria.request.ApiService;

import java.util.ArrayList;
import java.util.ConcurrentModificationException;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class InmuebleViewModel extends AndroidViewModel {

    private MutableLiveData<List<Inmueble>> mListInmuebles;
    private Context context;

    public InmuebleViewModel(@NonNull Application application) {
        super(application);
        mListInmuebles = new MutableLiveData<>();
        context = application;
        cargarInmuebles();

    }

    public LiveData<List<Inmueble>> getmListInmuebles() {
        return mListInmuebles;
    }

    public void cargarInmuebles() {
        String token = ApiService.leerToken(context);
        ApiService.ApiInterface apiService = ApiService.getApiInferface();
        Call<List<Inmueble>> llamada= apiService.obtenerPopiedades(token);

        llamada.enqueue(new Callback<List<Inmueble>>() {
            @Override
            public void onResponse(Call<List<Inmueble>> call, Response<List<Inmueble>> response) {
                if(response.isSuccessful()){
                    mListInmuebles.setValue(response.body());
                }else{
                    Log.d("salida","ELSE "+response.raw());
                }
            }

            @Override
            public void onFailure(Call<List<Inmueble>> call, Throwable t) {
                Log.d("salida","ERROR "+t.getMessage());
            }
        });
    }
}