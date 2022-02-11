package com.example.flixsterapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import com.codepath.asynchttpclient.AsyncHttpClient;
import com.codepath.asynchttpclient.RequestParams;
import com.codepath.asynchttpclient.callback.JsonHttpResponseHandler;
import com.example.flixsterapp.Adapters.MovieAdapter;
import com.example.flixsterapp.Models.Movie;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import okhttp3.Headers;

public class MainActivity extends AppCompatActivity {
    public static final String URL_KEY = "https://api.themoviedb.org/3/movie/now_playing?api_key=a07e22bc18f5cb106bfe4cc1f83ad8ed";
    public static final String TAG = "Main_Activity";

    List<Movie> movieList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        movieList = new ArrayList<>();
        RecyclerView recyclerView = findViewById(R.id.rv_Movies);

        // Instantiate MovieAdapter and set it to the Recycler View
        MovieAdapter movieAdapter = new MovieAdapter(this,movieList);
        recyclerView.setAdapter(movieAdapter);

        
        // Set Layout Manager
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        AsyncHttpClient client = new AsyncHttpClient();
        RequestParams params = new RequestParams();
        client.get(URL_KEY, params, new JsonHttpResponseHandler() {

                    @Override
                    public void onSuccess(int statusCode, Headers headers, JSON json) {
                        // called when response HTTP status is "200 OK"
                        Log.d(TAG,"Data Fetching succeeded");
                        JSONObject jsonObject = json.jsonObject;
                        // Parse JSON Object from
                        try {
                            JSONArray jsonArray = jsonObject.getJSONArray("results");
                            Log.i(TAG,"RESULTS:" + jsonArray.toString());
                            movieList.addAll(Movie.movieList(jsonArray));
                            movieAdapter.notifyDataSetChanged();
                            Log.i(TAG,"MOVIES:" + movieList.size());
                        }
                        catch(JSONException e){
                            Log.e(TAG,"JSON Exception",e);
                        }

                    }

                    @Override
                    public void onFailure(int statusCode, Headers headers, String errorResponse, Throwable t) {
                        // called when response HTTP status is "4XX" (eg. 401, 403, 404)
                        Log.d(TAG,"Data Fetching failed");
                    }
                }
        );
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            // Respond to the action bar's Up/Home button
            case android.R.id.home:
                supportFinishAfterTransition();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }



}