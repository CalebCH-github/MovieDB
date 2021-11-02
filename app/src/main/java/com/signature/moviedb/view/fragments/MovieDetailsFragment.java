package com.signature.moviedb.view.fragments;

import android.app.ProgressDialog;
import android.content.ClipData;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.signature.moviedb.R;
import com.signature.moviedb.adapter.RV_GenresAdapter;
import com.signature.moviedb.adapter.RV_ProComAdapter;
import com.signature.moviedb.helper.Const;
import com.signature.moviedb.helper.ItemClickSupport;
import com.signature.moviedb.model.Movies;
import com.signature.moviedb.viewmodel.MovieViewModel;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link MovieDetailsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MovieDetailsFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public MovieDetailsFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment MovieDetailsFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static MovieDetailsFragment newInstance(String param1, String param2) {
        MovieDetailsFragment fragment = new MovieDetailsFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //loading more items + progress bar
        dialog = ProgressDialog.show(getActivity(),"", "Loading.. ", true);
        dialog.show();
        }


    private TextView lbl_movie_id, lbl_description, lbl_title, lbl_tagline, lbl_avg_vote, lbl_votes,
            lbl_releasedate, lbl_popularity;
    private ImageView poster_film, backdrop_film;
    private MovieViewModel viewModel;
    private String movieId;
    private RecyclerView rv_genre;
    private RecyclerView rv_procom ;
    //loading more items + progress bar
    private ProgressDialog dialog;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_movie_details, container, false);
        lbl_movie_id = view.findViewById(R.id.lbl_movieId_movie_details_fragment);
        lbl_description = view.findViewById(R.id.lbl_description_movie_details_fragment);
        lbl_title = view.findViewById(R.id.lbl_title_movie_details_fragment);
        lbl_tagline = view.findViewById(R.id.lbl_tagline_movie_details_fragment);
        poster_film = view.findViewById(R.id.posterfilm_movie_details_fragment);
        backdrop_film = view.findViewById(R.id.backdrop_film_movie_details_fragment);
        lbl_avg_vote = view.findViewById(R.id.avg_vote_movie_details_fragment);
        lbl_votes = view.findViewById(R.id.votes_movie_details_fragment);
        lbl_releasedate = view.findViewById(R.id.releasedate_movie_details_fragment);
        lbl_popularity = view.findViewById(R.id.popularity_movie_details_fragment);
        rv_genre = view.findViewById(R.id.rv_genre_movie_details_fragment);
        rv_procom = view.findViewById(R.id.rv_production_companies);


        movieId = getArguments().getString("movieId");
        lbl_movie_id.setText(movieId);

//        String description = getArguments().getString("desc");
//        lbl_description.setText(description);
//
//        String title = getArguments().getString("title");
//        lbl_title.setText(title);
//
//        Image image = getArguments().getParcelable("image");
//        poster_film.setTag(image);
//
//
//        String backdrop = getArguments().getString("backdrop");
//        lbl_backdrop.setText(backdrop);


//        poster_film = view.findViewById(R.id.posterfilm_movie_details_fragment);
//        ImageView poster = (ImageView) getArguments().getSerializable("image");
//        poster_film.setImageIcon(poster);
//        Glide.with(getBaseContext())
//                .load(Const.IMG_URL + image_path)
//                .into(poster_film);

        viewModel = new ViewModelProvider(getActivity()).get(MovieViewModel.class);
        viewModel.getMovieById(movieId);
        viewModel.getResultGetMovieById().observe(getActivity(), showMovieById);

        return view;
    }

    private Observer<Movies> showMovieById = new Observer<Movies>() {
        @Override
        public void onChanged(Movies movies) {

            RV_GenresAdapter adapter = new RV_GenresAdapter(movies.getGenres(),getActivity());
            RV_ProComAdapter adapterpc = new RV_ProComAdapter(movies.getProduction_companies(),getActivity());


            lbl_title.setText(movies.getTitle());
            lbl_description.setText(movies.getOverview());
            Glide.with(getActivity())
                    .load(Const.IMG_URL + movies.getPoster_path())
                    .into(poster_film);
            Glide.with(getActivity())
                    .load(Const.IMG_URL + movies.getBackdrop_path())
                    .into(backdrop_film);
            lbl_tagline.setText(movies.getTagline());
            lbl_avg_vote.setText("Average vote: "+movies.getVote_average());
            lbl_votes.setText("Votes: "+movies.getVote_count());
            lbl_releasedate.setText(movies.getRelease_date());
            lbl_popularity.setText(String.valueOf(movies.getPopularity()));

//            for (int i = 0; i <movies.getGenres().size(); i++){
//                if (i ==movies.getGenres().size()-1){
//                    rv_genre += movies.getGenres().get(i).getName();
//                }else{
//                    rv_genre += movies.getGenres().get(i).getName()+ " , ";
//                }
//            }
            //for recyclerview in the fragment
            rv_genre.setLayoutManager(new GridLayoutManager(getActivity(), 1, RecyclerView.HORIZONTAL, false));
            rv_procom.setLayoutManager(new GridLayoutManager(getActivity(), 1, RecyclerView.HORIZONTAL, false));
            rv_genre.setAdapter(adapter);
            rv_procom.setAdapter(adapterpc);
            //^ WHY IS MY LOGO NOT SHOWING?!
            ItemClickSupport.addTo(rv_procom).setOnItemClickListener(new ItemClickSupport.OnItemClickListener() {
                @Override
                public void onItemClicked(RecyclerView recyclerView, int position, View v) {
                    Toast.makeText(getContext(), movies.getProduction_companies().get(position).getName(),
                            Toast.LENGTH_SHORT).show();
                }
            });

            //loading + progress bar
            dialog.dismiss();
        }
    } ;
}