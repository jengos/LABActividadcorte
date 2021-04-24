package com.unicordoba.labactividadcorte2.DataBase;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.BaseAdapter;

import com.unicordoba.labactividadcorte2.Model.Entily.PuestosVacunacion;
import com.unicordoba.labactividadcorte2.Model.Entily.Usuario;

import java.util.ArrayList;

public class bdPuestosVacunacion {
    Context context;
    PuestosVacunacion vacunacion;
    SQLiteDatabase databasePuestos;
    ArrayList<PuestosVacunacion> puestosList = new ArrayList<PuestosVacunacion>();
    String nombreBD = "BDUsuarios";
    String tabla = " create table if not exists puesto_vacunacion(id integer primary key autoincrement," +
            " depa_nombre text, muni_nombre text, sede_nombre text,direccion text, telefono text, email text," +
            " naju_nombre text, fecha_corte_resps text) ";

    public bdPuestosVacunacion(Context c) {
        this.context = c;
        databasePuestos = c.openOrCreateDatabase(nombreBD, Context.MODE_PRIVATE, null);
        databasePuestos.execSQL(tabla);
    }

    public boolean insertar(PuestosVacunacion puesto) {

        ContentValues contentValues = new ContentValues();
        contentValues.put("depa_nombre", puesto.getDepa_nombre());
        contentValues.put("muni_nombre", puesto.getMuni_nombre());
        contentValues.put("sede_nombre", puesto.getSede_nombre());
        contentValues.put("direccion", puesto.getDireccion());
        contentValues.put("telefono", puesto.getTelefono());
        contentValues.put("email", puesto.getEmail());
        contentValues.put("naju_nombre", puesto.getNaju_nombre());
        contentValues.put("fecha_corte_resps", puesto.getFecha_corte_resps());
        return (databasePuestos.insert("puesto_vacunacion", null, contentValues)) > 0;

    }

    public boolean eliminar(int id) {
        return (databasePuestos.delete("puesto_vacunacion","id=" +id, null))>0;
    }

    public ArrayList<PuestosVacunacion> getPuestosList() {

        Cursor cursor = databasePuestos.rawQuery("select * from puesto_vacunacion ", null);
        if (cursor != null && cursor.getCount() > 0) {
            cursor.moveToFirst();
            do {
                puestosList.add(new PuestosVacunacion(cursor.getInt(0),
                        cursor.getString(1),
                        cursor.getString(2),
                        cursor.getString(3),
                        cursor.getString(4),
                        cursor.getString(5),
                        cursor.getString(6),
                        cursor.getString(7),
                        cursor.getString(8)));
            } while (cursor.moveToNext());
        }
        return puestosList;
    }

    public PuestosVacunacion detalle(int posicion) {
        Cursor cursor = databasePuestos.rawQuery("select * from puesto_vacunacion ", null);
        cursor.moveToPosition(posicion);
        vacunacion = new PuestosVacunacion(cursor.getInt(0),
                cursor.getString(1),
                cursor.getString(2),
                cursor.getString(3),
                cursor.getString(4),
                cursor.getString(5),
                cursor.getString(6),
                cursor.getString(7),
                cursor.getString(8));
        return vacunacion;
    }

    public int buscar(String sedeNombre) {
        int x = 0;
        puestosList = selectPuesto();
        for (PuestosVacunacion pesto : puestosList) {
            if (pesto.getSede_nombre().equals(sedeNombre)) {
                x++;
            }
        }

        return x;
    }

    private ArrayList<PuestosVacunacion> selectPuesto() {
        ArrayList<PuestosVacunacion> lista = new ArrayList<PuestosVacunacion>();
        lista.clear();
        Cursor cursor = databasePuestos.rawQuery("select * from puesto_vacunacion ", null);
        if (cursor != null && cursor.moveToFirst()) {
            do {
                PuestosVacunacion puestosVacunacion = new PuestosVacunacion();
                puestosVacunacion.setId(cursor.getInt(0));
                puestosVacunacion.setDepa_nombre(cursor.getString(1));
                puestosVacunacion.setMuni_nombre(cursor.getString(2));
                puestosVacunacion.setSede_nombre(cursor.getString(3));
                puestosVacunacion.setDireccion(cursor.getString(4));
                puestosVacunacion.setTelefono(cursor.getString(5));
                puestosVacunacion.setEmail(cursor.getString(6));
                puestosVacunacion.setNaju_nombre(cursor.getString(7));
                puestosVacunacion.setFecha_corte_resps(cursor.getString(8));

                lista.add(puestosVacunacion);
            } while (cursor.moveToNext());
        }
        return lista;
    }

}
