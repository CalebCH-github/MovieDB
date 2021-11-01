package com.signature.moviedb.adapter;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.signature.moviedb.R;
import com.signature.moviedb.helper.Const;
import com.signature.moviedb.model.Popular;

import java.util.ArrayList;
import java.util.List;

public class PopularAdapter extends RecyclerView.Adapter<PopularAdapter.CardViewViewholder> {

    private Context context;
    private List<Popular.Results> listPopular;
    private List<Popular.Results> getListPopular(){return listPopular;}
    public void setListPopular(ArrayList<Popular.Results> listPopular) {
        this.listPopular = listPopular;
    } public PopularAdapter(Context context){
        this.context = context;
    }

    @NonNull
    @Override
    public CardViewViewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_popular, parent, false);

        return new PopularAdapter.CardViewViewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PopularAdapter.CardViewViewholder holder, int position) {
        final Popular.Results results = getListPopular().get(position);

        holder.lbl_title_pop.setText(results.getTitle());
        holder.lbl_overview_pop.setText(results.getOverview());
        holder.lbl_releasedate_pop.setText(results.getRelease_date());
        Glide.with(context)
                .load(Const.IMG_URL + results.getPoster_path())
                .into(holder.poster_film_popular);

        holder.cv_pop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Bundle bundle = new Bundle();
                bundle.putString("movieId", ""+results.getId());
//                bundle.putString("desc", ""+results.getOverview());
//                bundle.putString("title", ""+results.getTitle());
//                bundle.putString("image", results.getPoster_path());
                Navigation.findNavController(view).navigate(R.id.action_popularFragment_to_MovieDetailsFragment
                        ,bundle);

            }
        });
    }

    @Override
    public int getItemCount() {
        return getListPopular().size();
    }

    public class CardViewViewholder extends RecyclerView.ViewHolder {

        ImageView poster_film_popular;
        TextView lbl_title_pop, lbl_overview_pop, lbl_releasedate_pop;
        CardView cv_pop;
        public CardViewViewholder(@NonNull View itemView) {

            super(itemView);
            poster_film_popular = itemView.findViewById(R.id.poster_film_popular);
            lbl_title_pop = itemView.findViewById(R.id.lbl_title_popular);
            lbl_overview_pop = itemView.findViewById(R.id.lbl_overview_popular);
            lbl_releasedate_pop = itemView.findViewById(R.id.lbl_releasedate_popular);
            cv_pop = itemView.findViewById(R.id.cv_popular);
        }
    }
}
