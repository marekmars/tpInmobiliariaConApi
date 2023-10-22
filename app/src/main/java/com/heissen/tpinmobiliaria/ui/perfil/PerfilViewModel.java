package com.heissen.tpinmobiliaria.ui.perfil;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.heissen.tpinmobiliaria.LoginActivity;
import com.heissen.tpinmobiliaria.MenuActivity;
import com.heissen.tpinmobiliaria.R;
import com.heissen.tpinmobiliaria.models.Propietario;
import com.heissen.tpinmobiliaria.request.ApiClient;
import com.heissen.tpinmobiliaria.request.ApiService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PerfilViewModel extends AndroidViewModel {

    private MutableLiveData<Propietario> mPropietario;
    private MutableLiveData<Boolean> activo;
    private Context context;
    private MutableLiveData<String> mBtnTitulo;

    public PerfilViewModel(@NonNull Application application) {
        super(application);
        mPropietario = new MutableLiveData<>();
        mBtnTitulo=new MutableLiveData<>();
        activo=new MutableLiveData<>();
        activo.setValue(false);
        context=getApplication();
        cargarUser();
    }

    public LiveData<Propietario> getmPropietario() {
        return mPropietario;
    }
    public LiveData<Boolean> getActivo() {
        return activo;
    }

    public LiveData<String> getmBtn() {
        return mBtnTitulo;
    }
    public void cargarUser() {
        String token= ApiService.leerToken(context);
        Log.d("salida","TOKEN: "+token);
        ApiService.ApiInterface apiInterface= ApiService.getApiInferface();
        Call<Propietario> llamada=apiInterface.obtenerPropietario(token);
        llamada.enqueue(new Callback<Propietario>() {
            @Override
            public void onResponse(Call<Propietario> call, Response<Propietario> response) {
                if(response.isSuccessful()){
                    Propietario propietarioAux=response.body();
                    Log.d("salida ",response.body().toString());
                    mPropietario.setValue(propietarioAux);
                    Log.d("salida ", "AVATAR DESDE VM: "+mPropietario.getValue().getAvatar()+"");
                }else {

                    Log.d("salida respuesta ",response.raw().message());
                }
            }

            @Override
            public void onFailure(Call<Propietario> call, Throwable t) {
                Log.d("salida falla ",t.getMessage());
            }
        });
    }
    public void editarPerfil(Propietario propietario){
        String token= ApiService.leerToken(context);
        ApiService.ApiInterface apiService = ApiService.getApiInferface();
        if(!activo.getValue()){
            activar();
        }else{
            Call<String>llamada= apiService.editarPropietario(token,propietario.getNombre(), propietario.getApellido(),
                    propietario.getDni(), propietario.getTelefono(), propietario.getCorreo(), propietario.getClave());
            llamada.enqueue(new Callback<String>() {
                @Override
                public void onResponse(Call<String> call, Response<String> response) {
                    if(response.isSuccessful()){
                        if(response.body()==null){
                            Toast.makeText(context, "Correo ya existente", Toast.LENGTH_SHORT).show();
                            activar();
                        }else if(response.body().equals("Reloguear")){
                            Toast.makeText(context, "Debe Volver a loguearse", Toast.LENGTH_SHORT).show();
                            Intent intent=new Intent(context, LoginActivity.class);
                            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                            context.startActivity(intent);
                        }else{
                            ApiService.guardarToken(getApplication(),"Bearer "+response.body());

                            Intent intent=new Intent(context, MenuActivity.class);
                            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            Toast.makeText(context, "Usuario actualizado correctamente", Toast.LENGTH_SHORT).show();
                            context.startActivity(intent);
                        }


                    }else{
                        Log.d("salida","ELSE "+response.raw().message());
                    }
                }

                @Override
                public void onFailure(Call<String> call, Throwable t) {
                    Log.d("salida","ERROR "+t.getMessage());
                }


            });

            Log.d("salida","desdeVm2: "+propietario.getAvatar());
            mPropietario.setValue(propietario);
            activar();
        }

    }
    public void activar() {
        activo.setValue(!this.activo.getValue());
        if(activo.getValue()){
            mBtnTitulo.setValue("Guardar");
        }else {
            mBtnTitulo.setValue("Editar");
        }
    }
}