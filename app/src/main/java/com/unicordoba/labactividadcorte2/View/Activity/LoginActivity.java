package com.unicordoba.labactividadcorte2.View.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.unicordoba.labactividadcorte2.DataBase.bdUsuario;
import com.unicordoba.labactividadcorte2.Model.Entily.Usuario;
import com.unicordoba.labactividadcorte2.R;

public class LoginActivity extends AppCompatActivity {
    EditText eTcorreo, eTpassword;
    Usuario user;
    bdUsuario bdUser;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
       eTcorreo = findViewById(R.id.eT_correoLogin);
       eTpassword=findViewById(R.id.eT_passwordLogin);
       bdUser =new bdUsuario(this);
    }

    public void Registro(View view) {
        Intent intent = new Intent(LoginActivity.this, RegistroActivity.class);
        startActivity(intent);
    }

    public void IniciarSesion(View view) {
        String correo = eTcorreo.getText().toString();
        String password = eTpassword.getText().toString();
        if(correo.equals("")&&password.equals("")){
            Toast.makeText(this, "ERROR: Campos Vacios",Toast.LENGTH_LONG).show();
        }else if(bdUser.Login(correo,password)==1){
            Usuario usX =bdUser.getUsuario(correo,password);
            Toast.makeText(this, "Bienvenido ",Toast.LENGTH_LONG).show();
            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
            intent.putExtra("Id", usX.getId());
            startActivity(intent);
            finish();
        }else {
            Toast.makeText(this, "Datos erroneos ",Toast.LENGTH_LONG).show();
        }
    }
}