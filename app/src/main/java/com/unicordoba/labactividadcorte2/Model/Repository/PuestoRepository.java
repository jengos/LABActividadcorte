package com.unicordoba.labactividadcorte2.Model.Repository;

import android.content.Context;

import com.unicordoba.labactividadcorte2.Model.Entily.PuestosVacunacion;
import com.unicordoba.labactividadcorte2.Model.Network.DatosApi;
import com.unicordoba.labactividadcorte2.Model.Network.P_VacunacionAppCallback;
import com.unicordoba.labactividadcorte2.Model.Network.RetrofitService;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class PuestoRepository {
    private Context mContext;

    private ArrayList<PuestosVacunacion> listPuestos;
     DatosApi datosApi;



    public PuestoRepository(Context mContext) {
        this.mContext = mContext;
        Retrofit retrofit = RetrofitService.obtenerInstancias();
        this.listPuestos = new ArrayList<>();
        datosApi = retrofit.create(DatosApi.class);
    }
    public void obtenerTodosPV(P_VacunacionAppCallback<ArrayList<PuestosVacunacion>> respuesta){
        datosApi.obternerTodos().enqueue(new Callback<ArrayList<PuestosVacunacion>>() {
            @Override
            public void onResponse(Call<ArrayList<PuestosVacunacion>> call, Response<ArrayList<PuestosVacunacion>> response) {
            listPuestos = response.body();
            for (int i = 0; i<listPuestos.size(); i++){
                listPuestos.get(i).Complementar(i+"");
            }
            respuesta.correto(listPuestos);
            }

            @Override
            public void onFailure(Call<ArrayList<PuestosVacunacion>> call, Throwable t) {

            }
        });

    }
}
