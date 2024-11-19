package com.example.movie_finder;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;

public class MovieDetailsActivity extends AppCompatActivity {

    private ImageView moviePoster;
    private TextView detailTitle, detailYear, detailType;
    private Button backButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_details);

        moviePoster = findViewById(R.id.moviePoster);
        detailTitle = findViewById(R.id.detailTitle);
        detailYear = findViewById(R.id.detailYear);
        detailType = findViewById(R.id.detailType);
        backButton = findViewById(R.id.backButton);

        Movie movie = (Movie) getIntent().getSerializableExtra("movie");

        if (movie != null) {
            detailTitle.setText(movie.getTitle());
            detailYear.setText("Year: " + movie.getYear());
            detailType.setText("Type: " + movie.getType());
            Glide.with(this).load(movie.getPoster()).into(moviePoster);
        }

        backButton.setOnClickListener(view -> finish());
    }
}
