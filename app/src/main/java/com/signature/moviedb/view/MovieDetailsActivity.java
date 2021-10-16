package com.signature.moviedb.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.signature.moviedb.R;
import com.signature.moviedb.helper.Const;

public class MovieDetailsActivity extends AppCompatActivity {

    private TextView lbl_movdet, text_title, text_date, text_description, text_vc;
    private String movie_id = "", title="", popular = "", image_path = "", desc = "",
            releasedate="", votecounter="";
    private ImageView img_details_poster;
    private Button btn_buy, btn_review;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_details);

        Intent intent = getIntent();
        movie_id = intent.getStringExtra("movie_id");
        popular = intent.getStringExtra("popular");
        image_path = intent.getStringExtra("image");
        desc = intent.getStringExtra("desc");
        releasedate = intent.getStringExtra("date");
        votecounter = intent.getStringExtra("votecount");
        title = intent.getStringExtra("title");


        text_title = findViewById(R.id.text_details_title);
        text_date = findViewById(R.id.text_details_date);
        text_description = findViewById(R.id.text_details_description);
        text_vc = findViewById(R.id.text_details_votecount);

        btn_buy = findViewById(R.id.btn_details_buy);
        btn_review = findViewById(R.id.btn_details_review);

        //ID film
        lbl_movdet = findViewById(R.id.lbl_movie_details);
        lbl_movdet.setText("Movie id is " +movie_id);

        //Title film
        text_title.setText(title);

        //Poster film
        img_details_poster = findViewById(R.id.img_details_poster);
        Glide.with(getBaseContext())
                .load(Const.IMG_URL + image_path)
                .into(img_details_poster);

        //Description film
        text_description.setText(desc);

        //Tahun film
        text_date.setText(releasedate);

        //Vote film
        text_vc.setText("Votes: "+votecounter);

        //about buy ticket btn
        btn_buy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(),"Buy ticket belom jadi", Toast.LENGTH_SHORT).show();
            }
        });

        //about review btn
        btn_review.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(),"Lihat review belom jadi", Toast.LENGTH_SHORT).show();
            }
        });


    }

//    private Observer<Reviews> showReviews = new Observer<Reviews>() {
//        @Override
//        public void update(Observable observable, Object o) {
//
//        }
//    }

    

    @Override
    public void onBackPressed() {
        finish();
    }


}