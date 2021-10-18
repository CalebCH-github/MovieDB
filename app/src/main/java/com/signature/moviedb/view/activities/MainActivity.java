package com.signature.moviedb.view.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.android.material.textfield.TextInputLayout;
import com.signature.moviedb.R;
import com.signature.moviedb.model.Movies;
import com.signature.moviedb.viewmodel.MovieViewModel;

public class MainActivity extends AppCompatActivity {

    private MovieViewModel viewModel;
    private Button btn_hit_main;
    private TextView text_show_main;
    private TextInputLayout til_movieid_main;
    private ImageView img_poster;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        text_show_main = findViewById(R.id.text_show_main);
        til_movieid_main = findViewById(R.id.til_movieid_main);
        img_poster = findViewById(R.id.img_poster);
        viewModel = new ViewModelProvider(MainActivity.this).get(MovieViewModel.class);

        btn_hit_main = findViewById(R.id.btn_hit_main);
        btn_hit_main.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String movieId = til_movieid_main.getEditText().getText().toString().trim();
                if (movieId.isEmpty()){
                    til_movieid_main.setError("Please fill movie id field");
                }else{
                    til_movieid_main.setError(null);
                    viewModel.getMovieById(movieId);
                    viewModel.getResultGetMovieById().observe(MainActivity.this, showResultMovie);
                }
//                viewModel.getMovieById("");
                viewModel.getResultGetMovieById().observe(MainActivity.this, showResultMovie);
            }
        });
    }

    private Observer<Movies> showResultMovie = new Observer<Movies>() {
        @Override
        public void onChanged(Movies movies) {
            if (movies == null) {
                text_show_main.setText("No Movie with selected id");
            }else {

                String title = movies.getTitle();
                String img_path = movies.getPoster_path().toString();
                String full_path = "https://image.tmdb.org/t/p/w500/" + img_path;
                Glide.with(MainActivity.this).load(full_path).into(img_poster);
                text_show_main.setText(title);
            }

        }
    };
}