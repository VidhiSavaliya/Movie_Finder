package com.example.movie_finder;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Movie implements Serializable {

    @SerializedName("Title")
    private String title;

    @SerializedName("Year")
    private String year;

    @SerializedName("imdbID")
    private String imdbId;

    @SerializedName("Type")
    private String type;

    @SerializedName("Poster")
    private String poster;

    public String getTitle() {
        return title;
    }

    public String getYear() {
        return year;
    }

    public String getImdbId() {
        return imdbId;
    }

    public String getType() {
        return type;
    }

    public String getPoster() {
        return poster;
    }
}
