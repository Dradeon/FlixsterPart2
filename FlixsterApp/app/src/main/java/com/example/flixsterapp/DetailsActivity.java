package com.example.flixsterapp;

import android.os.Bundle;
import android.util.Log;
import android.widget.RatingBar;
import android.widget.TextView;

import com.codepath.asynchttpclient.AsyncHttpClient;
import com.codepath.asynchttpclient.callback.JsonHttpResponseHandler;
import com.example.flixsterapp.Models.Movie;
import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;

import org.json.JSONArray;
import org.json.JSONException;
import org.parceler.Parcels;

import okhttp3.Headers;

public class DetailsActivity extends YouTubeBaseActivity {

    private static final String YOUTUBE_KEY_STRING = "AIzaSyCVClWl6qLoqYQRgJwrwO_ITzolJLU0wLQ";
    private static final String VIDEOS_URL = "https://api.themoviedb.org/3/movie/%d/videos?api_key=a07e22bc18f5cb106bfe4cc1f83ad8ed";

    TextView tv_title;
    TextView tv_overview;
    RatingBar ratingBar;
    YouTubePlayerView playerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        tv_title = findViewById(R.id.tv_title);
        tv_overview = findViewById(R.id.tv_overview);
        ratingBar = findViewById(R.id.ratingBar);
        playerView = findViewById(R.id.player);

        Movie movie = (Movie) Parcels.unwrap(getIntent().getParcelableExtra("movie"));
        tv_title.setText(movie.getTitle());
        tv_overview.setText(movie.getDesc());
        ratingBar.setRating((float) movie.getRating());

        AsyncHttpClient client = new AsyncHttpClient();
        client.get(String.format(VIDEOS_URL, movie.getMovieID()), new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Headers headers, JSON json) {
                try {
                    JSONArray results = json.jsonObject.getJSONArray("results");
                    if (results.length() == 0) {
                        return;
                    }
                    String youtubeKey = results.getJSONObject(0).getString("key");
                    Log.d("DetailsActivity",youtubeKey);
                    if(movie.getRating() > 8.0){
                        initializeYoutube(youtubeKey,true);
                    }
                    else{
                        initializeYoutube(youtubeKey,false);
                    }
                } catch (JSONException e) {
                    Log.e("DetailsActivity","JSON Error");
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(int statusCode, Headers headers, String response, Throwable throwable) {

            }
        });

    }

    private void initializeYoutube(final String youtubeKey, boolean autoPlay) {
        playerView.initialize(YOUTUBE_KEY_STRING, new YouTubePlayer.OnInitializedListener() {
            @Override
            public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {
                Log.d("DetailsActivity","OnInitializationSuccess");
                if(autoPlay){
                    youTubePlayer.setFullscreenControlFlags(YouTubePlayer.FULLSCREEN_FLAG_CONTROL_ORIENTATION);
                    youTubePlayer.loadVideo(youtubeKey);
                }
                else{
                    youTubePlayer.setFullscreenControlFlags(YouTubePlayer.FULLSCREEN_FLAG_CONTROL_ORIENTATION);
                    youTubePlayer.cueVideo(youtubeKey);
                }
            }

            @Override
            public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {
                Log.d("DetailsActivity",youTubeInitializationResult.toString());
            }
        });
    }

}