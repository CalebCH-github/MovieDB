package com.signature.moviedb.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.signature.moviedb.R;
import com.signature.moviedb.helper.Const;
import com.signature.moviedb.model.Movies;

import java.util.List;

public class RV_GenresAdapter extends RecyclerView.Adapter<RV_GenresAdapter.TextViewholder> {

    private List<Movies.Genres> genres;
    private Context context;

    public Context getContext(){
        return context;
    }

    public RV_GenresAdapter(List<Movies.Genres> genres, Context context) {
        this.genres = genres;
        this.context = context;
    }


    @NonNull
    @Override
    public TextViewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.text_genres_movie_details, parent,
                false);
        return new RV_GenresAdapter.TextViewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TextViewholder holder, int position) {
        holder.lbl_rv_genres.setText(genres.get(position).getName());
    }

    @Override
    public int getItemCount() {
        return genres.size();
    }

    public class TextViewholder extends RecyclerView.ViewHolder {

        TextView lbl_rv_genres;

        public TextViewholder(@NonNull View itemView) {
            super(itemView);
            lbl_rv_genres = itemView.findViewById(R.id.text_genre_rv);
        }
    }


}
