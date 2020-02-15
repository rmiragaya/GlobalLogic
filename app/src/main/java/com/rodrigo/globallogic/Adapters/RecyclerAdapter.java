package com.rodrigo.globallogic.Adapters;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.rodrigo.globallogic.Models.Laptop;
import com.rodrigo.globallogic.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> {
    private static final String TAG = "RecyclerAdapter";

    private ArrayList<Laptop> laptopList;
    private OnItemClickListener mListener;

    /* interface for onclick on product in recyclerView */
    public interface OnItemClickListener{
        void onItemClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener){
        mListener = listener;
    }

    public RecyclerAdapter( ArrayList<Laptop> productoList) {
        this.laptopList = new ArrayList<>(productoList);

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.laptop_layout, parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Laptop currentLaptop = laptopList.get(position);
        holder.titulo.setText(currentLaptop.getTitle());
        holder.detalle.setText(currentLaptop.getDescription());
        Log.d(TAG, "currentLaptop.getImageUrl(): " + currentLaptop.getImageUrl());
        //todo cambiar placeholderes
        Picasso.get().load(currentLaptop.getImageUrl()).fit().centerInside().placeholder(R.drawable.globallogic_logo_chico).error(R.drawable.globallogic_logo_chico).into(holder.imagen);

    }

    @Override
    public int getItemCount() {
        return laptopList.size();
    }

     class ViewHolder extends RecyclerView.ViewHolder{

        ImageView imagen;
        TextView titulo, detalle;
        ConstraintLayout mLayout;

         ViewHolder(@NonNull View itemView) {
            super(itemView);
            imagen = itemView.findViewById(R.id.imagen_id);
            titulo = itemView.findViewById(R.id.titulo_id);
            detalle = itemView.findViewById(R.id.detalle_id);
            mLayout = itemView.findViewById(R.id.recyclerLayout);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mListener != null){
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION){
                            mListener.onItemClick(position);
                        }
                    }
                }
            });
        }
    }

    /* update recycler */
    public void updateData(ArrayList<Laptop> productoList){
        this.laptopList = productoList;
        this.notifyDataSetChanged();
    }

}
