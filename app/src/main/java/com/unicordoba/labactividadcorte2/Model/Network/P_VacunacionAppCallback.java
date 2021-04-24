package com.unicordoba.labactividadcorte2.Model.Network;

public interface P_VacunacionAppCallback <T> {
    void correto(T respuesta);
    void error(Exception excepcion);

}
