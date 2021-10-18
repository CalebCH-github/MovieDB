package com.signature.moviedb.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.signature.moviedb.R;
import com.signature.moviedb.helper.Const;
import com.signature.moviedb.model.Movies;
import com.signature.moviedb.model.NowPlaying;
import com.signature.moviedb.model.Reviews;
import com.signature.moviedb.view.activities.MovieDetailsActivity;

import java.util.List;

public class NowPlayingAdapter extends RecyclerView.Adapter<NowPlayingAdapter.CardViewViewHolder> {

    private Context context;
    private List<Movies> listmovies;
    private List<Reviews.Results> listReview;
    private List<Reviews.Results> getListReview(){return listReview;}
    public void setListReview(List<Reviews.Results> listReview){
        this.listReview = listReview;
    }
    private List<NowPlaying.Results> listNowPlaying;
    private List<NowPlaying.Results> getListNowPlaying(){return listNowPlaying;}
    public void setListNowPlaying(List<NowPlaying.Results> listNowPlaying){
        this.listNowPlaying = listNowPlaying;
    }public NowPlayingAdapter(Context context){
        this.context = context;
    }


    @NonNull
    @Override
    public NowPlayingAdapter.CardViewViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_now_playing, parent,
                false);

        return new NowPlayingAdapter.CardViewViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NowPlayingAdapter.CardViewViewHolder holder, int position) {
        final NowPlaying.Results results = getListNowPlaying().get(position);
        //final Reviews.Results results1 = getListReview().get(position);
        holder.lbl_title.setText(results.getTitle());
        holder.lbl_overview.setText(results.getOverview());
        holder.lbl_releasedate.setText(results.getRelease_date());
        Glide.with(context)
                .load(Const.IMG_URL + results.getPoster_path())
                .into(holder.img_poster_card_nowplaying);

        holder.cv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, MovieDetailsActivity.class);
                intent.putExtra("movie_id", ""+results.getId());
                intent.putExtra("title", results.getTitle());
                intent.putExtra("image", results.getPoster_path());
                intent.putExtra("desc", results.getOverview());
                //intent.putExtra("review", results1.getContent());
                intent.putExtra("popular", results.getPopularity());
                intent.putExtra("date", results.getRelease_date());
                intent.putExtra("votecount", results.getVote_count());

                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return getListNowPlaying().size();
    }

    public class CardViewViewHolder extends RecyclerView.ViewHolder {

        TextView lbl_title, lbl_overview, lbl_releasedate;
        ImageView img_poster_card_nowplaying;
        CardView cv;

        public CardViewViewHolder(@NonNull View itemView) {
            super(itemView);
            img_poster_card_nowplaying = itemView.findViewById(R.id.img_poster_card_nowplaying);
            lbl_title = itemView.findViewById(R.id.lbl_title);
            lbl_overview = itemView.findViewById(R.id.lbl_overview);
            lbl_releasedate = itemView.findViewById(R.id.lbl_releasedate);
            cv = itemView.findViewById(R.id.cv_card_nowplaying);
        }
    }
}
