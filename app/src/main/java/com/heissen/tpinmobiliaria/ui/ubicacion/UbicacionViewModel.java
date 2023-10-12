package com.heissen.tpinmobiliaria.ui.ubicacion;

import android.app.Application;
import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;


import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class UbicacionViewModel extends AndroidViewModel {

    private Context context;
    private static final LatLng IMOBILIARIA = new LatLng(-33.150720, -66.306864);
    private MutableLiveData<MapaActual> mMapa;

    public UbicacionViewModel(@NonNull Application application) {
        super(application);
        context = getApplication();

        mMapa = new MutableLiveData<>();
    }

    public LiveData<MapaActual> getmMapa() {
        return mMapa;
    }

    public void obtenerMapa() {
        MapaActual ma = new MapaActual();
        mMapa.setValue(ma);
    }

    public class MapaActual implements OnMapReadyCallback {

        @Override
        public void onMapReady(@NonNull GoogleMap googleMap) {
            googleMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
            googleMap.addMarker(new MarkerOptions().position(IMOBILIARIA).title("Inmobiliaria de La Punta"));
            CameraPosition camPos = new CameraPosition.Builder()
                    .target(IMOBILIARIA)
                    .zoom(17)
                    .bearing(45)
                    .build();
            CameraUpdate update = CameraUpdateFactory.newCameraPosition(camPos);
            googleMap.animateCamera(update);

        }

    }

}