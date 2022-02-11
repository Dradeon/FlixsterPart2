package com.example.flixsterapp.Models;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.parceler.Parcel;

import java.util.ArrayList;

@Parcel
public class Movie {
    private int movieID;
    private String title;
    private String desc;
    private String poster;
    private String backDrop;
    private double rating;

    // Parceler library needs empty constructor
    public Movie(){}


    public Movie(JSONObject jsonObject) throws JSONException {
        this.title = jsonObject.getString("original_title");
        this.desc = jsonObject.getString("overview");
        this.poster = jsonObject.getString("poster_path");
        this.backDrop = jsonObject.getString("backdrop_path");
        this.rating = jsonObject.getDouble("vote_average");
        this.movieID = jsonObject.getInt("id");
    }

    public static ArrayList<Movie> movieList(JSONArray jsonArray) throws JSONException {
        ArrayList<Movie> movies = new ArrayList<>();
        for(int i = 0; i < jsonArray.length(); i++){
            movies.add(new Movie(jsonArray.getJSONObject(i)));
        }
        return movies;
    }

    public String getTitle() {
        return title;
    }

    public String getDesc() {
        return desc;
    }

    public String getPoster() {
        return String.format("https://image.tmdb.org/t/p/w342%s",poster);
    }

    public String getBackDrop() {
        return String.format("https://image.tmdb.org/t/p/w342%s",backDrop);
    }

    public double getRating() {
        return rating;
    }

    public int getMovieID() {
        return movieID;
    }
}
