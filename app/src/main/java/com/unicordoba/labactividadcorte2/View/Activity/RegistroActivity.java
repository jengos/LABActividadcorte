package com.unicordoba.labactividadcorte2.View.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.unicordoba.labactividadcorte2.DataBase.bdUsuario;
import com.unicordoba.labactividadcorte2.Model.Entily.Usuario;
import com.unicordoba.labactividadcorte2.R;

public class RegistroActivity extends AppCompatActivity {
    EditText eTcorreoRegistro, eTpasswordRegistro, eTnombreUsuarioRegistro ;
    private Toolbar toolbar;
    bdUsuario bdUser;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_registro);

        eTcorreoRegistro= findViewById(R.id.eT_correo_registro);
        eTpasswordRegistro= findViewById(R.id.eT_password_registro);
        eTnombreUsuarioRegistro= findViewById(R.id.eT_nombre_usuario_registro);
         bdUser = new bdUsuario(this);

        toolbar = findViewById(R.id.toolbarRegistro);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("REGISTRAR CUENTA");
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
                finish();
            }
        });
    }

    public void CrearCuenta(View view) {
        Usuario user= new Usuario();
        user.setNombreUsuario(eTnombreUsuarioRegistro.getText().toString());
        user.setCorreo(eTcorreoRegistro.getText().toString());
        user.setPassword(eTpasswordRegistro.getText().toString());

        if(!user.isNull()){
            Toast.makeText(this, "ERROR: Campos Vacios",Toast.LENGTH_LONG).show();
        }else if(bdUser.insertUsuario(user)){
            Toast.makeText(this, "Registro Exitoso",Toast.LENGTH_LONG).show();
            Intent intent= new Intent(RegistroActivity.this, LoginActivity.class);
            startActivity(intent);
            finish();
        }else{
            Toast.makeText(this, "Usuario ya registrado",Toast.LENGTH_LONG).show();
        }



    }
}