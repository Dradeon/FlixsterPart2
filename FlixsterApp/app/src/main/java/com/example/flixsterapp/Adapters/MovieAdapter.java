package com.example.flixsterapp.Adapters;

import android.content.Context;
import android.content.res.Configuration;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.flixsterapp.Models.Movie;
import com.example.flixsterapp.R;
import com.example.flixsterapp.ViewHolder1;
import com.example.flixsterapp.Viewholder2;

import java.util.List;



public class MovieAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    Context context;
    List<Movie> movieList;
    private final int movieItem = 0, movieBackDrop = 1;

    public MovieAdapter(Context context, List<Movie> movies){
        this.context = context;
        this.movieList = movies;
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Log.d("MovieAdapter","OnCreateViewHolder");
        context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        RecyclerView.ViewHolder viewHolder;

        switch(viewType){
            case movieItem:
                View contactView = inflater.inflate(R.layout.movie_item, parent, false);
                viewHolder = new ViewHolder1(contactView,context);
                break;
            case movieBackDrop:
                View backDropView = inflater.inflate(R.layout.movie_item_backdrop,parent,false);
                viewHolder = new Viewholder2(backDropView,context);
                break;
            default:
                View v = inflater.inflate(R.layout.movie_item, parent, false);
                viewHolder = new ViewHolder1(v,context);
                break;
        }

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        Log.d("MovieAdapter","OnBindViewHolder: " + position);
        // Get Movie Position and Bind movie into viewHolder
        Movie movie = movieList.get(position);

        switch (holder.getItemViewType()) {
            case movieItem:
                ViewHolder1 vh1 = (ViewHolder1) holder;
                ((ViewHolder1) holder).bind1(movie);
                break;
            case movieBackDrop:
                Viewholder2 vh2 = (Viewholder2) holder;
                ((Viewholder2) holder).bind2(movie);
                break;
            default:
                ViewHolder1 vh = (ViewHolder1) holder;
                ((ViewHolder1) holder).bind1(movie);
                break;
        }
    }

    @Override
    public int getItemViewType(int position){
        if(movieList.get(position).getRating() > 8.0){
            return  movieBackDrop;
        }
        else{
            return movieItem;
        }
    }

    @Override
    public int getItemCount() {
        return movieList.size();
    }









}
