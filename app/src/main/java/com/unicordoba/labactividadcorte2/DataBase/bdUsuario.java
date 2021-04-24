package com.unicordoba.labactividadcorte2.DataBase;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.unicordoba.labactividadcorte2.Model.Entily.Usuario;

import java.util.ArrayList;

public class bdUsuario {
    Context context;
    Usuario usuario;
    ArrayList <Usuario> listUsuario;
    SQLiteDatabase sqLiteDatabase;
    String dataBase = "BDUsuarios";
    String tabla = "create table if not exists usuario(id integer primary key autoincrement,nombre_usuario text, correo text, pass text)";

    public bdUsuario(Context context) {
        this.context = context;
        sqLiteDatabase = context.openOrCreateDatabase(dataBase,context.MODE_PRIVATE,null);
        sqLiteDatabase.execSQL(tabla);
        usuario= new Usuario();

    }
    public boolean insertUsuario(Usuario usuario){
        if ( buscar(usuario.getCorreo())==0){
            ContentValues contentValues = new ContentValues();
            contentValues.put("nombre_usuario", usuario.getNombreUsuario() );
            contentValues.put("correo", usuario.getCorreo() );
            contentValues.put("pass", usuario.getPassword() );

            return (sqLiteDatabase.insert("usuario", null,contentValues)>0);

        }else {
            return false;

        }
    }



    public int buscar(String correo){
        int x=0;
        listUsuario = selectUsuario();
        for (Usuario user:listUsuario){
            if(user.getCorreo().equals(correo)){
                x++;
            }
        }

        return x;
    }
    public ArrayList<Usuario> selectUsuario(){
        ArrayList<Usuario> lista = new ArrayList<Usuario>();
        lista.clear();
        Cursor cursor= sqLiteDatabase.rawQuery("select * from usuario", null);
        if(cursor!=null && cursor.moveToFirst()){
            do {
                Usuario user = new Usuario();
                user.setId(cursor.getInt(0));
                user.setNombreUsuario(cursor.getString(1));
                user.setCorreo(cursor.getString(2));
                user.setPassword(cursor.getString(3));

                lista.add(user);
            }while (cursor.moveToNext());
        }
        return lista;
    }

    public int Login (String correo, String passwork){
        int aux= 0;
        Cursor cursor = sqLiteDatabase.rawQuery("select * from usuario",null);
        if(cursor!=null && cursor.moveToFirst()){
            do{
                if(cursor.getString(2).equals(correo)&& cursor.getString(3).equals(passwork)){
                    aux++;
                }


            }while (cursor.moveToNext());
        }
        return aux;
    }
    public Usuario getUsuario(String correo, String password){
        listUsuario=selectUsuario();
        for (Usuario user :listUsuario){
            if(user.getCorreo().equals(correo)&& user.getPassword().equals(password)){
                return user;
            }
        }
        return null;
    }
    public Usuario getUsuarioById(int id){
        listUsuario = selectUsuario();
        for (Usuario user: listUsuario){
            if(user.getId()==id){
                return user;
            }
        }
        return null;
    }
}
