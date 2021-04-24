package com.unicordoba.labactividadcorte2.Model.Network;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public abstract class RetrofitService {
    private static Retrofit instancia = null;
    private static String Url = "https://www.datos.gov.co/resource/";

    public static Retrofit obtenerInstancias() {
        if (instancia == null) {
            instancia = new Retrofit.Builder()
                    .baseUrl(Url)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return instancia;
    }
}
