package com.heissen.tpinmobiliaria.request;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.heissen.tpinmobiliaria.models.Contrato;
import com.heissen.tpinmobiliaria.models.Inmueble;
import com.heissen.tpinmobiliaria.models.Inquilino;
import com.heissen.tpinmobiliaria.models.Pago;
import com.heissen.tpinmobiliaria.models.Propietario;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Url;

public class ApiService {

    public static final String URL_BASE = "http://192.168.1.7:5000/";

    private static ApiInterface apiInterface;

    public static ApiInterface getApiInferface(){

        Gson gson= new GsonBuilder()
                .setLenient()
                .registerTypeAdapter(LocalDateTime.class, new Contrato.LocalDateTimeDeserializer())
                .create();

        Retrofit retrofit=new Retrofit.Builder()
                .baseUrl(URL_BASE)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        apiInterface=retrofit.create(ApiInterface.class);

        return apiInterface;


    }
    public static void guardarToken(Context context, String token){
        SharedPreferences sp=context.getSharedPreferences("token.xml",0);
        SharedPreferences.Editor editor=sp.edit();
        editor.putString("token",token);
        editor.commit();

    }

    public static void eliminarToken(Context context) {
        SharedPreferences sp = context.getSharedPreferences("token.xml", 0);
        SharedPreferences.Editor editor = sp.edit();
        editor.remove("token");
        editor.apply();
    }

    public static String leerToken(Context context){
        SharedPreferences sp=context.getSharedPreferences("token.xml",0);
        return sp.getString("token","");
    }
    public interface ApiInterface {
        final String URL_PROPIETARIOS = "api/Propietarios/";
        final String URL_INMUEBLES="api/Inmuebles/";
        final String URL_INQUILINOS="api/Inquilinos/";
        final String URL_CONTRATOS="api/Contratos/";
        final String URL_PAGOS="api/Pagos/";

        //Metodos Propietario
        @FormUrlEncoded
        @POST(URL_PROPIETARIOS+"login")
        Call<String> login(@Field("Correo") String correo, @Field("Clave") String clave);
        @FormUrlEncoded
        @PUT(URL_PROPIETARIOS+"editar")
        Call<String> editarPropietario(@Header("Authorization") String token, @Field("Nombre") String nombre, @Field("Apellido") String apellido,
        @Field("Dni") String dni, @Field("Telefono") String telefono,
        @Field("Correo") String correo, @Field("Clave") String clave);
        @GET(URL_PROPIETARIOS+"user")
        Call<Propietario> obtenerPropietario(@Header("Authorization") String token);
        @FormUrlEncoded
        @POST(URL_PROPIETARIOS+"email")
        Call<Propietario> inciarRecupero(@Field("correo") String correo);



        //metodos inmbueble
        @GET(URL_INMUEBLES+"propiedadesUsuario")
        Call<List<Inmueble>> obtenerPopiedades(@Header("Authorization") String token);

        @GET(URL_INMUEBLES+"{id}")
        Call<Inmueble> obtenerInmueble(@Header("Authorization") String token,@Path("id") int id);

        @PUT(URL_INMUEBLES+"toogleEstado/{id}")
        Call<Inmueble> toggleEstado(@Header("Authorization") String token,@Path("id") int id);

        @POST(URL_INMUEBLES+"crear")
        Call<Inmueble> crearInmueble(@Header("Authorization") String token,@Body Inmueble inmueble);

      /*  @GET(URL_INMUEBLES+"{id}/foto")
        Call<ResponseBody> getImage(@Header("Authorization") String token,@Path("id") int id);*/

        @GET(URL_INMUEBLES+"alquiladas")
        Call<List<Inmueble>> obtenerInmueblesAlq(@Header("Authorization") String token);

        //metodos inquilino
        @GET(URL_INQUILINOS+"inquiloActual/{id}")
        Call<Inquilino> obtenerInquiloAct(@Header("Authorization") String token, @Path("id") int id);

        //metodos contratos

        @GET(URL_CONTRATOS+"{id}")
        Call<Contrato> obtenerContrato(@Header("Authorization") String token,@Path("id") int id);

        //metodos pagos

        @GET(URL_PAGOS+"{idContrato}")
        Call<ArrayList<Pago>> obtenerPagos(@Header("Authorization") String token, @Path("idContrato") int idContrato);

    }
}
