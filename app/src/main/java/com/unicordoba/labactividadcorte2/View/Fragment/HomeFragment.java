package com.unicordoba.labactividadcorte2.View.Fragment;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.unicordoba.labactividadcorte2.DataBase.bdPuestosVacunacion;
import com.unicordoba.labactividadcorte2.Model.Entily.PuestosVacunacion;
import com.unicordoba.labactividadcorte2.Model.Network.P_VacunacionAppCallback;
import com.unicordoba.labactividadcorte2.Model.Repository.PuestoRepository;
import com.unicordoba.labactividadcorte2.R;
import com.unicordoba.labactividadcorte2.View.Adapter.puestoVacunacionAdapter;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HomeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomeFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    bdPuestosVacunacion databasepuestos;
    RecyclerView rv_PuestoRetrofil;
    private ArrayList<PuestosVacunacion> listPuesto;
    puestoVacunacionAdapter miAdapter;
    private PuestoRepository puestoRepository;

    public HomeFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment HomeFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static HomeFragment newInstance(String param1, String param2) {
        HomeFragment fragment = new HomeFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        listPuesto = new ArrayList<>();
        databasepuestos= new bdPuestosVacunacion(getContext());
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        rv_PuestoRetrofil = view.findViewById(R.id.rV_datosPuesto);
        puestoRepository = new PuestoRepository(getContext());
        miAdapter = new puestoVacunacionAdapter(listPuesto);
        configuarListado();
        actualizarListado();
        return view;
    }

    private void configuarListado() {
        rv_PuestoRetrofil.setLayoutManager(new LinearLayoutManager(getContext()));
        rv_PuestoRetrofil.setHasFixedSize(true);
        miAdapter.setOnItemClickListener(new puestoVacunacionAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(PuestosVacunacion miPuestosVacunacion, int posicion) {

            }

            @Override
            public void onItemClickEliminar(PuestosVacunacion miPuestosVacunacion, int posicion) {

            }

            @Override
            public void onItemClickGuardar(PuestosVacunacion miPuestosVacunacion, int posicion) {
                try {
                   /* PuestosVacunacion sql = new PuestosVacunacion();
                    sql.setDepa_nombre(miPuestosVacunacion.getDepa_nombre());
                    sql.setMuni_nombre(miPuestosVacunacion.getMuni_nombre());
                    sql.setSede_nombre(miPuestosVacunacion.getSede_nombre());

                    sql.setTelefono(miPuestosVacunacion.getTelefono());
                    sql.setEmail(miPuestosVacunacion.getEmail());
                    sql.setFecha_corte_resps(miPuestosVacunacion.getFecha_corte_resps());
                    sql.setNaju_nombre(miPuestosVacunacion.getNaju_nombre());
*/

                    databasepuestos.insertar(miPuestosVacunacion);
                    Toast.makeText(getContext(), miPuestosVacunacion.getSede_nombre()+" fue agregado a la Base de dato Local ", Toast.LENGTH_LONG).show();
                }catch (Exception e){
                    Toast.makeText(getContext(), "Error ", Toast.LENGTH_SHORT).show();

                }
            }
        });
    }



    private void actualizarListado() {
        puestoRepository.obtenerTodosPV(new P_VacunacionAppCallback<ArrayList<PuestosVacunacion>>() {
            @Override
            public void correto(ArrayList<PuestosVacunacion> respuesta) {
                miAdapter.setPuestosList(respuesta);
            }

            @Override
            public void error(Exception excepcion) {
                Toast.makeText(getContext(), "Error al traer datos", Toast.LENGTH_SHORT).show();
            }
        });
       rv_PuestoRetrofil.setAdapter(miAdapter);
    }



}