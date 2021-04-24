package com.unicordoba.labactividadcorte2.Model.Network;

import com.unicordoba.labactividadcorte2.Model.Entily.PuestosVacunacion;

import retrofit2.http.GET;

import java.util.ArrayList;

import retrofit2.Call;
public interface DatosApi {
@GET("u82n-j82m.json")
    Call<ArrayList<PuestosVacunacion>> obternerTodos();

}
