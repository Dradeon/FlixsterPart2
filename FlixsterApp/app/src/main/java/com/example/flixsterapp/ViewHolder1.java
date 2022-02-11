package com.example.flixsterapp;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import androidx.core.util.Pair;
import androidx.annotation.NonNull;
import androidx.core.app.ActivityOptionsCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.flixsterapp.Models.Movie;

import org.parceler.Parcel;
import org.parceler.Parcels;

public class ViewHolder1 extends RecyclerView.ViewHolder{
    RelativeLayout container;
    TextView title;
    TextView overview;
    ImageView poster;
    Context context;

    public ViewHolder1(@NonNull View itemView, Context context) {
        super(itemView);
        title = itemView.findViewById(R.id.tv_title);
        overview = itemView.findViewById(R.id.tv_overview);
        poster = itemView.findViewById(R.id.img_Poster);
        container = itemView.findViewById(R.id.container);
        this.context = context;
    }

    public void bind1(Movie movie){
        title.setText(movie.getTitle());
        overview.setText(movie.getDesc());
        String imageURL;
        if(context.getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE){
            imageURL = movie.getBackDrop();
        }
        else{
            imageURL = movie.getPoster();
        }

        Glide.with(context).load(imageURL).into(poster);

        container.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(context, DetailsActivity.class);

                i.putExtra("movie",Parcels.wrap(movie));
                Pair<View, String> p1 = Pair.create((View)title, "title");
                Pair<View, String> p2 = Pair.create(overview, "overview");
                Pair[] yes = new Pair[]{p1,p2};
                ActivityOptionsCompat options = ActivityOptionsCompat.makeSceneTransitionAnimation((Activity) context,yes);

                context.startActivity(i,options.toBundle());
            }
        });
    }


}