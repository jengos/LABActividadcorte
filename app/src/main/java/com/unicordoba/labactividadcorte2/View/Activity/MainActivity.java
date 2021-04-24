package com.unicordoba.labactividadcorte2.View.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;
import com.unicordoba.labactividadcorte2.DataBase.bdUsuario;
import com.unicordoba.labactividadcorte2.Model.Entily.Usuario;
import com.unicordoba.labactividadcorte2.R;
import com.unicordoba.labactividadcorte2.View.Fragment.HomeFragment;
import com.unicordoba.labactividadcorte2.View.Fragment.ListaSqlFragment;
import com.unicordoba.labactividadcorte2.View.Fragment.LogFragment;
import com.unicordoba.labactividadcorte2.View.Fragment.PerfilFragment;
import com.unicordoba.labactividadcorte2.Log.Log;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    DrawerLayout mDrawerLayout;
    NavigationView navigationView;
    ActionBarDrawerToggle toggle;
    Toolbar toolbardrawer;
    TextView nombreUsuario, Correo;
    int id = 0;
    Usuario user;
    public Log log = new Log();
    bdUsuario bdUser;
    int idUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        InicializarCompomentes();

        getSupportFragmentManager().beginTransaction().add(R.id.content, new HomeFragment()).commit();
        setTitle("Inicio");
        setSupportActionBar(toolbardrawer);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_nav_menu);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toggle = new ActionBarDrawerToggle(this, mDrawerLayout, toolbardrawer, R.string.drawer_open, R.string.drawer_close);
        mDrawerLayout.addDrawerListener(toggle);
        toggle.setDrawerIndicatorEnabled(true);
        navigationView.setNavigationItemSelectedListener(this);
        Bundle b = getIntent().getExtras();
        id = b.getInt("Id");
        bdUser = new bdUsuario(this);
        user = bdUser.getUsuarioById(id);
        nombreUsuario.setText(user.getNombreUsuario());
        Correo.setText((user.getCorreo()));
        idUser = id;
        log.WritetoFile(log.CapturarIngreso(getApplicationContext()), getApplicationContext());
    }

    private void InicializarCompomentes() {


        toolbardrawer = findViewById(R.id.drawer_toolbar);
        mDrawerLayout = findViewById(R.id.drawerLayout);
        navigationView = (NavigationView) findViewById(R.id.nav_drawer);
        View navHeader = navigationView.getHeaderView(0);
        nombreUsuario = (TextView) navHeader.findViewById(R.id.nombreUsuariodrawer);
        Correo = (TextView) navHeader.findViewById(R.id.correodrawer);
    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        SelecItemNav(item);
        return false;
    }

    private void SelecItemNav(MenuItem item) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        switch (item.getItemId()) {
            case R.id.inicio:
                fragmentTransaction.replace(R.id.content, new HomeFragment()).commit();
                break;
            case R.id.sqlite:
                fragmentTransaction.replace(R.id.content, new ListaSqlFragment()).commit();
                break;
            case R.id.perfilusuario:
                Bundle bundle = new Bundle();
                bundle.putString("param1", String.valueOf(user.getId()));
                PerfilFragment datosPerfil = new PerfilFragment();
                datosPerfil.setArguments(bundle);
                fragmentTransaction.replace(R.id.content, datosPerfil).commit();
                break;
            case R.id.logRegisto:
                fragmentTransaction.replace(R.id.content, new LogFragment()).commit();
                break;
            case R.id.cerrar_sesion:
                Toast.makeText(this, "Hasta Pronto", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();

                break;


        }
        setTitle(item.getTitle());
        mDrawerLayout.closeDrawers();
    }
}