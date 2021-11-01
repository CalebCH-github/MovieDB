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
import com.signature.moviedb.model.NowPlaying;
import com.signature.moviedb.model.Upcoming;

import java.util.ArrayList;
import java.util.List;

public class UpcomingAdapter extends RecyclerView.Adapter<UpcomingAdapter.CardViewViewHolder> {


    private Context context;
    private ArrayList<Upcoming.Results> listUpcoming;
    private ArrayList<Upcoming.Results> getListUpcoming(){return listUpcoming;}
    public void setListUpcoming(ArrayList<Upcoming.Results> listUpcoming) {
        this.listUpcoming = listUpcoming;
    } public UpcomingAdapter(Context context){
        this.context = context;
    }


    @NonNull
    @Override
    public UpcomingAdapter.CardViewViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_upcoming, parent, false);

        return new UpcomingAdapter.CardViewViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UpcomingAdapter.CardViewViewHolder holder, int position) {
        final Upcoming.Results results = getListUpcoming().get(position);

        holder.lbl_title_up.setText(results.getTitle());
        holder.lbl_overview_up.setText(results.getOverview());
        holder.lbl_release_date_up.setText(results.getRelease_date());
        Glide.with(context)
                .load(Const.IMG_URL + results.getPoster_path())
                .into(holder.img_poster_up);

        holder.cv_up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Bundle bundle = new Bundle();
                bundle.putString("movieId", ""+results.getId());
//                bundle.putString("desc", ""+results.getOverview());
//                bundle.putString("title", ""+results.getTitle());
//                bundle.putString("image", results.getPoster_path());
                Navigation.findNavController(view).navigate(R.id.action_upcomingFragment_to_MovieDetailsFragment
                ,bundle);

            }
        });

    }

    @Override
    public int getItemCount() {
        return getListUpcoming().size();
    }

    public class CardViewViewHolder extends RecyclerView.ViewHolder {
        ImageView img_poster_up;
        TextView lbl_title_up, lbl_overview_up, lbl_release_date_up;
        CardView cv_up;

        public CardViewViewHolder(@NonNull View itemView) {
            super(itemView);
            img_poster_up = itemView.findViewById(R.id.img_poster_card_upcoming);
            lbl_title_up = itemView.findViewById(R.id.lbl_title_upcoming);
            lbl_overview_up = itemView.findViewById(R.id.lbl_overview_upcoming);
            lbl_release_date_up = itemView.findViewById(R.id.lbl_releasedate_upcoming);
            cv_up = itemView.findViewById(R.id.cv_card_upcoming);
        }
    }
}
