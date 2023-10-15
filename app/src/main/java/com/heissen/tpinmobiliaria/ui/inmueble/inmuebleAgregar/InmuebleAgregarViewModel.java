package com.heissen.tpinmobiliaria.ui.inmueble.inmuebleAgregar;

import static androidx.core.app.ActivityCompat.startActivityForResult;

import android.app.Application;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.text.BoringLayout;
import android.util.Base64;
import android.util.Log;
import android.widget.ArrayAdapter;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.navigation.Navigation;

import com.heissen.tpinmobiliaria.models.Inmueble;
import com.heissen.tpinmobiliaria.models.TipoInmueble;
import com.heissen.tpinmobiliaria.models.UsoInmueble;
import com.heissen.tpinmobiliaria.request.ApiService;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class InmuebleAgregarViewModel extends AndroidViewModel {
    private static final int PICK_IMAGE_REQUEST = 1;


    private MutableLiveData<ArrayAdapter<String>> mAdapterTipo;
    private MutableLiveData<ArrayAdapter<String>> mAdapterUso;


    public InmuebleAgregarViewModel(@NonNull Application application) {
        super(application);
        mAdapterTipo = new MutableLiveData<>();
        mAdapterUso = new MutableLiveData<>();
        cargarSpiners();
    }

    public LiveData<ArrayAdapter<String>> getmAdapterTipo() {
        return mAdapterTipo;
    }

    public LiveData<ArrayAdapter<String>> getmAdapterUso() {
        return mAdapterUso;
    }




    public void cargarSpiners() {

        List<String> tipos = Arrays.stream(TipoInmueble.values())
                .map(TipoInmueble::name)
                .collect(Collectors.toList());
        mAdapterTipo.setValue(new ArrayAdapter<>(getApplication(), android.R.layout.simple_spinner_item, tipos));
        mAdapterTipo.getValue().setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        List<String> usos = Arrays.stream(UsoInmueble.values())
                .map(UsoInmueble::name)
                .collect(Collectors.toList());
        mAdapterUso.setValue(new ArrayAdapter<>(getApplication(), android.R.layout.simple_spinner_item, usos));
        mAdapterUso.getValue().setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
    }

    public String convertirImgBase64(Uri uri) {
        try {
            ContentResolver contentResolver = getApplication().getContentResolver();
            InputStream inputStream = contentResolver.openInputStream(uri);

            if (inputStream != null) {
                // Decodifica la imagen en un objeto Bitmap
                BitmapFactory.Options options = new BitmapFactory.Options();
                options.inSampleSize = 2; // Puedes ajustar este valor para comprimir más o menos
                Bitmap originalBitmap = BitmapFactory.decodeStream(inputStream, null, options);

                // Comprime la imagen (ajusta la calidad según tus necesidades)
                int quality = 50; // Rango de 0 (más comprimido) a 100 (menos comprimido)
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                originalBitmap.compress(Bitmap.CompressFormat.JPEG, quality, byteArrayOutputStream);

                // Convierte la imagen comprimida a Base64
                byte[] imageBytes = byteArrayOutputStream.toByteArray();
                return Base64.encodeToString(imageBytes, Base64.DEFAULT);
            } else {
                Log.d("salida", "Error al cargar la imagen");
                return null;
            }
        } catch (IOException e) {
            // Handle error: Ocurrió un error al leer la imagen.
            e.printStackTrace();
            return null;
        }
    }

    public void crearInmueble(Uri uri, Inmueble i) {
        String token = ApiService.leerToken(getApplication());
        ApiService.ApiInterface apiService = ApiService.getApiInferface();
        Log.d("salida", i.toString());
        Inmueble inmueble = i;
        inmueble.setLat("");
        inmueble.setLon("");
        inmueble.setFoto(convertirImgBase64(uri));
        Call<Inmueble> llamada = apiService.crearInmueble(token, inmueble);

        llamada.enqueue(new Callback<Inmueble>() {
            @Override
            public void onResponse(Call<Inmueble> call, Response<Inmueble> response) {
                if (response.isSuccessful()) {

                    Log.d("salida", response.body().toString());
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
}

