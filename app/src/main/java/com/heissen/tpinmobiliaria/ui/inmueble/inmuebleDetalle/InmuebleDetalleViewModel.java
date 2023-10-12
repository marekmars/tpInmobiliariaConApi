package com.heissen.tpinmobiliaria.ui.inmueble.inmuebleDetalle;

import android.app.Application;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.heissen.tpinmobiliaria.R;
import com.heissen.tpinmobiliaria.models.Inmueble;
import com.heissen.tpinmobiliaria.request.ApiClient;
import com.heissen.tpinmobiliaria.request.ApiService;

import java.io.IOException;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class InmuebleDetalleViewModel extends AndroidViewModel {

    private MutableLiveData<Inmueble> mInmueble;
    private MutableLiveData<String> mLabelSwich;
    private MutableLiveData<Bitmap>mFotoInmueble;

    private Bitmap fotoImbueble;

    private Context context;

    public InmuebleDetalleViewModel(@NonNull Application application) {
        super(application);
        mInmueble = new MutableLiveData<>();
        mLabelSwich = new MutableLiveData<>();
        mFotoInmueble=new MutableLiveData<>();
        context = application;

    }

    public LiveData<Bitmap> getmFotoInmueble() {
        return mFotoInmueble;
    }

    public LiveData<String> getmLabelSwich() {
        return mLabelSwich;
    }

    public LiveData<Inmueble> getMInmueble() {
        return mInmueble;
    }

/*
    private void recuperarImagen(int id) {
        String token = ApiService.leerToken(context);
        ApiService.ApiInterface apiService = ApiService.getApiInferface();
        Call<ResponseBody> llamada = apiService.getImage(token, id);

        llamada.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful()) {
                    Log.d("salida", "RESPONSE: " + response.body());
                    byte[] imageBytes = new byte[0];
                    try {
                        imageBytes = response.body().bytes();
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }

                    mFotoInmueble.setValue(BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.length));
                } else {
                    Log.d("salida", "ELSE " + response.raw());
                    mFotoInmueble.setValue(BitmapFactory.decodeResource(getApplication().getResources(), R.drawable.inmueble_default));
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Log.d("salida", "ERROR " + t.getMessage());
            }

        });



    }*/

    public void getInmuebles(Bundle bundle) {
        int idInmueble = bundle.getSerializable("inmuebleSelec", Inmueble.class).getId();
        String token = ApiService.leerToken(context);
        ApiService.ApiInterface apiService = ApiService.getApiInferface();
        Call<Inmueble> llamada = apiService.obtenerInmueble(token, idInmueble);

        llamada.enqueue(new Callback<Inmueble>() {
            @Override
            public void onResponse(Call<Inmueble> call, Response<Inmueble> response) {
                if (response.isSuccessful()) {
                    Log.d("salida", "RESPONSE: " + response.body());

                    mInmueble.setValue(response.body());
                  /*  recuperarImagen(response.body().getId());*/
                    if (mInmueble.getValue().isDisponible()) {
                        mLabelSwich.setValue("Deshabilitar");
                    } else {
                        mLabelSwich.setValue("Habilitar");
                    }
                } else {
                    Log.d("salida", "ELSE " + response.raw());
                }
            }

            @Override
            public void onFailure(Call<Inmueble> call, Throwable t) {
                Log.d("salida", "ERROR " + t.getMessage());
            }
        });

    }

    public void setEstado(int id) {
        String token = ApiService.leerToken(context);
        ApiService.ApiInterface apiService = ApiService.getApiInferface();
        Log.d("salida", "ID: " + id);
        Call<Inmueble> llamada = apiService.toggleEstado(token, id);

        llamada.enqueue(new Callback<Inmueble>() {
            @Override
            public void onResponse(Call<Inmueble> call, Response<Inmueble> response) {
                if (response.isSuccessful()) {
                    Log.d("salida", "RESPONSE " + response.raw().message());
                    mInmueble.setValue(response.body());
                    if (mInmueble.getValue().isDisponible()) {
                        mLabelSwich.setValue("Desabilitar");
                    } else {
                        mLabelSwich.setValue("Habilitar");
                    }
                } else {
                    Log.d("salida", "ELSE " + response.raw().message());
                }
            }

            @Override
            public void onFailure(Call<Inmueble> call, Throwable t) {
                Log.d("salida", "ERROR " + t.getMessage());
            }

        });


    }
}