package com.unicordoba.labactividadcorte2.View.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.unicordoba.labactividadcorte2.Model.Entily.PuestosVacunacion;
import com.unicordoba.labactividadcorte2.R;

import java.util.ArrayList;

public class sqlPuestoAdapter extends RecyclerView.Adapter<sqlPuestoAdapter.sqlPuestoViewHolder> {
    private ArrayList<PuestosVacunacion> listPuesto;
    private OnItemClickListener onItemClickListener;

    public void setListPuesto(ArrayList<PuestosVacunacion> listPuesto) {
        this.listPuesto = listPuesto;
        notifyDataSetChanged();
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public sqlPuestoAdapter(ArrayList<PuestosVacunacion> listPuesto) {
        this.listPuesto = listPuesto;
    }

    class sqlPuestoViewHolder extends RecyclerView.ViewHolder {
        private TextView eT_nombrePuesto, eT_departamento, eT_municipio, eT_telefono, eT_direccion;
        private ImageButton  bT_Eliminar;

        public sqlPuestoViewHolder(@NonNull View itemView) {
            super(itemView);
            eT_nombrePuesto = itemView.findViewById(R.id.text_nombre_puesto);
            eT_departamento = itemView.findViewById(R.id.text_departamento);
            eT_municipio = itemView.findViewById(R.id.text_municio);
            eT_telefono = itemView.findViewById(R.id.text_Telefomo);
            eT_direccion = itemView.findViewById(R.id.text_direccion);

            bT_Eliminar = itemView.findViewById(R.id.bT_elimar);
        }
        public void onBind(PuestosVacunacion puestosVacunacion){
            eT_nombrePuesto.setText(puestosVacunacion.getSede_nombre());
            eT_departamento.setText(puestosVacunacion.getDepa_nombre());
            eT_municipio.setText(puestosVacunacion.getMuni_nombre());
            eT_telefono.setText(puestosVacunacion.getTelefono());
            eT_direccion.setText(puestosVacunacion.getDireccion());
            if(onItemClickListener!=null){

                bT_Eliminar.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        onItemClickListener.onItemClickEliminar(puestosVacunacion, getAdapterPosition());
                    }
                });
                itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        onItemClickListener.onItemClick(puestosVacunacion, getAdapterPosition());

                    }
                });
            }
        }

    }
    public sqlPuestoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType){
        View miVista = LayoutInflater.from(parent.getContext()).inflate(R.layout.puestossql_adaptaer,parent,false);
        return new sqlPuestoViewHolder(miVista);
    }

    @Override
    public void onBindViewHolder(@NonNull sqlPuestoViewHolder holder, int position) {
        PuestosVacunacion miPuestosVacunacion= listPuesto.get(position);
        holder.onBind(miPuestosVacunacion);
    }

    @Override
    public int getItemCount() {
        return listPuesto.size();
    }
    public interface OnItemClickListener{
        void onItemClick (PuestosVacunacion miPuestosVacunacion, int posicion);
        void onItemClickEliminar (PuestosVacunacion miPuestosVacunacion, int posicion);

    }

}
