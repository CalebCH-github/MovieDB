package com.signature.moviedb.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.signature.moviedb.R;
import com.signature.moviedb.helper.Const;
import com.signature.moviedb.model.Movies;

import java.util.List;

public class RV_ProComAdapter extends RecyclerView.Adapter<RV_ProComAdapter.LogoViewholder> {


    private List<Movies.ProductionCompanies> productionCompanies;
    //public List<Movies.ProductionCompanies> getProductionCompanies(){return productionCompanies;}
    private Context context;


    public Context getContext(){
        return context;
    }

    public RV_ProComAdapter(List<Movies.ProductionCompanies> productionCompanies, Context context) {
        this.productionCompanies = productionCompanies;
        this.context = context;
    }

    @NonNull
    @Override
    public LogoViewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_procom_icon, parent,
                false);
        return new RV_ProComAdapter.LogoViewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull LogoViewholder holder, int position) {
        if (productionCompanies.get(position).getLogo_path() == null){
            holder.logos.setImageResource(R.drawable.ic_baseline_broken_image_24);
        }else{
            Glide.with(context)
                    .load(Const.IMG_URL + productionCompanies.get(position).getLogo_path())
                    .into(holder.logos);
        }
    }

    @Override
    public int getItemCount() {
        return productionCompanies.size();
    }

    public class LogoViewholder extends RecyclerView.ViewHolder {

        ImageView logos;
        public LogoViewholder(@NonNull View itemView) {
            super(itemView);
            logos = itemView.findViewById(R.id.production_companies_logo);
        }
    }
}
