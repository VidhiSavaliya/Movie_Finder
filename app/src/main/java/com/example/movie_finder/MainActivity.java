package com.example.movie_finder;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private EditText searchField;
    private Button searchButton;
    private RecyclerView recyclerView;
    private MovieAdapter adapter;
    private OMDBApi api;
    private final String API_KEY = "13b105ac";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize views
        searchField = findViewById(R.id.searchField);
        searchButton = findViewById(R.id.searchButton);
        recyclerView = findViewById(R.id.recyclerView);

        // Set up RecyclerView
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new MovieAdapter(new ArrayList<>());
        recyclerView.setAdapter(adapter);

        // Initialize Retrofit
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://www.omdbapi.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        api = retrofit.create(OMDBApi.class);

        // Handle search button click
        searchButton.setOnClickListener(view -> searchMovies());
    }

    private void searchMovies() {
        String query = searchField.getText().toString().trim();

        if (query.isEmpty()) {
            Toast.makeText(this, "Please enter a movie title", Toast.LENGTH_SHORT).show();
            return;
        }

        api.searchMovies(query, API_KEY).enqueue(new Callback<MovieResponse>() {
            @Override
            public void onResponse(Call<MovieResponse> call, Response<MovieResponse> response) {
                if (response.body() != null && response.body().getSearch() != null) {
                    List<Movie> movies = response.body().getSearch();
                    Log.d("API Response", "Movies found: " + movies.size()); // Debugging log
                    adapter.updateMovieList(movies); // Update RecyclerView
                } else {
                    Log.d("API Response", "No movies found or response body is null");
                    Toast.makeText(MainActivity.this, "No movies found", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<MovieResponse> call, Throwable t) {
                Log.e("API Failure", "Error: " + t.getMessage());
                Toast.makeText(MainActivity.this, "Failed to fetch data", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
