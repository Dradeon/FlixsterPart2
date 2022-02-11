package com.example.flixsterapp;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.flixsterapp.Models.Movie;

import org.parceler.Parcels;

public class Viewholder2 extends RecyclerView.ViewHolder{
    RelativeLayout container;
    ImageView backDrop;
    Context context;

    public Viewholder2(@NonNull View itemView, Context context) {
        super(itemView);
        backDrop = itemView.findViewById(R.id.img_BackDrop);
        container = itemView.findViewById(R.id.containerBackdrop);
        this.context = context;
    }

    public void bind2(Movie movie){

        Glide.with(context).load(movie.getBackDrop()).into(backDrop);

        container.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(context, DetailsActivity.class);

                i.putExtra("movie", Parcels.wrap(movie));

                context.startActivity(i);
            }
        });
    }

}
