package com.example.movie_finder;

import com.google.gson.annotations.SerializedName;
import java.util.List;

public class MovieResponse {

    @SerializedName("Search")
    private List<Movie> search;

    public List<Movie> getSearch() {
        return search;

    }

}
