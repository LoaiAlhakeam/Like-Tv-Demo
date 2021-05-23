package com.example.likedemo.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONArrayRequestListener;
import com.example.likedemo.R;
import com.example.likedemo.adapters.RecyclerAdapter;
import com.example.likedemo.models.Category;
import com.example.likedemo.models.Movie;
import com.example.likedemo.services.AlphabeticSort;
import com.example.likedemo.services.DatesSort;
import com.example.likedemo.services.RateSort;
import com.google.android.material.tabs.TabLayout;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.ConcurrentModificationException;
import java.util.Date;
import java.util.Locale;


public class AllMovie extends AppCompatActivity {

    TextView day,dayWritten,month,clock;
    TabLayout categoriesTab,sortTab;
    RecyclerView recyclerView;
    ArrayList<Movie> movies= new ArrayList<Movie>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_movie);
        Intent intent = getIntent();
        String token = intent.getStringExtra("token");

        day = (TextView) findViewById(R.id.dayViewer);
        dayWritten = (TextView) findViewById(R.id.dayWritrViewer);
        month = (TextView) findViewById(R.id.monthViewer);
        clock = (TextView) findViewById(R.id.clockViewer);
        categoriesTab = (TabLayout) findViewById(R.id.categoriesTab);
        sortTab = (TabLayout) findViewById(R.id.sortTab);
        recyclerView =(RecyclerView) findViewById(R.id.recyclerView);

        day.setText(new SimpleDateFormat("dd ", Locale.getDefault()).format(new Date()));
        dayWritten.setText(new SimpleDateFormat("E",Locale.getDefault()).format(new Date()));
        month.setText(new SimpleDateFormat("MMMM",Locale.getDefault()).format(new Date()));
        clock.setText(new SimpleDateFormat("hh:mm",Locale.getDefault()).format(new Date()));

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL, false));


        try {
            getCategory(token,categoriesTab);

        } catch (JSONException e) {
            e.printStackTrace();
        }

        sortTab.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                Log.d("sort tab",tab.getText().toString());
                switch (tab.getText().toString()){
                    case "Date":
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                            movies.sort(new DatesSort());
                        }
                            buildRecyclerView();

                    case "Alphabetic":
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                            movies.sort(new AlphabeticSort());
                        }
                            buildRecyclerView();

                    case "Rating":
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                            movies.sort(new RateSort());
                        }
                            buildRecyclerView();
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        categoriesTab.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                Log.d("tab",tab.getTag().toString());
                movies.clear();
                Log.d("movies after clear",movies.toString());
                getMoviesByCategory(token,tab.getTag().toString());

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }

        });

    }

    public void getCategory(String token, TabLayout tabLayout) throws JSONException {

        AndroidNetworking.get("http://10.237.239.2/v4/genre-by-type/{type}")
                .addPathParameter("type", "movies")
                .addHeaders("Authorization", "Bearer " + token)
                .build()
                .getAsJSONArray(new JSONArrayRequestListener() {
                    @Override
                    public void onResponse(JSONArray response) {

                        if (response != null){
                        ArrayList<Category> categories = new ArrayList<Category>();
                            for (int i =0;i<response.length();i++){
                                try {
                                    Category category = new Category();
                                    JSONObject obj = response.getJSONObject(i);
                                    category.setId(obj.getInt("id"));
                                    category.setDisplay_name_ar(obj.getString("display_name_ar"));
                                    category.setDisplay_name_en(obj.getString("display_name_en"));
                                    category.setCreated_at(new SimpleDateFormat("yyyy-mm-dd hh:mm:ss").parse(obj.getString("created_at")));
                                    category.setUpdated_at(new SimpleDateFormat("yyyy-mm-dd hh:mm:ss").parse(obj.getString("updated_at")));

                                    categories.add(category);

                                    tabLayout.addTab(tabLayout.newTab().setText(category.getDisplay_name_en()).setTag(category.getId()));
                                } catch (JSONException | ParseException e) {
                                    e.printStackTrace();
                                }
                            }
                        }

                    }
                    @Override
                    public void onError(ANError error) {
                        // handle error
                        if (error.getErrorCode() != 0) {
                            // received error from server
                            // error.getErrorCode() - the error code from server
                            // error.getErrorBody() - the error body from server
                            // error.getErrorDetail() - just an error detail
                            Log.d("test", "onError errorCode : " + error.getErrorCode());
                            Log.d("test", "onError errorBody : " + error.getErrorBody());
                            Log.d("test", "onError errorDetail : " + error.getErrorDetail());

                        } else {
                            // error.getErrorDetail() : connectionError, parseError, requestCancelledError
                            Log.d("test", "onError errorDetail : " + error.getErrorDetail());
                        }
                    }
                });
    }

    public void getMoviesByCategory(String token,String categoryId){
        AndroidNetworking.get("http://10.237.239.2/v4/content_new/{type}/{category_id}/{skip}")
                .addPathParameter("type", "movies")
                .addPathParameter("category_id", categoryId)
                .addPathParameter("skip", "0")
                .addHeaders("Authorization", "Bearer " + token)
                .build()
                .getAsJSONArray(new JSONArrayRequestListener() {
                    @Override
                    public void onResponse(JSONArray response) {
                        if(response != null){
                            if (response != null) {
                                try {
                                    JSONArray jsonMoviesArray = new JSONArray();
                                    JSONObject jsonMoviesObject = new JSONObject();

                                    jsonMoviesArray = response.getJSONObject(0).getJSONArray("movies_new");


                                    for (int i = 0; i<jsonMoviesArray.length();i++) {
                                    Movie movie = new Movie();
                                        jsonMoviesObject = jsonMoviesArray.getJSONObject(i);
                                        movie.setId(jsonMoviesObject.getInt("id"));
                                        movie.setType(jsonMoviesObject.getString("type"));
                                        movie.setDisplay_name(jsonMoviesObject.getString("display_name"));
                                        movie.setDuration(jsonMoviesObject.getInt("duration"));
                                        movie.setPrice(jsonMoviesObject.getInt("price"));
                                        movie.setRating(jsonMoviesObject.getInt("rating"));
                                        movie.setNumber_of_raters(jsonMoviesObject.getInt("number_of_raters"));
                                        movie.setWriters(jsonMoviesObject.getString("writers"));
                                        movie.setStars(jsonMoviesObject.getString("stars"));
                                        movie.setDirectors(jsonMoviesObject.getString("directors"));
                                        movie.setDisplay_description_en(jsonMoviesObject.getString("display_description_en"));
                                        movie.setDisplay_description_ar(jsonMoviesObject.getString("display_description_ar"));
                                        movie.setMedia_URL(jsonMoviesObject.getString("media_url"));
                                        movie.setPhoto_URL(jsonMoviesObject.getString("photo_url"));
                                        movie.setTrailer_URL(jsonMoviesObject.getString("trailer_url"));
                                        movie.setImdb_URL(jsonMoviesObject.getString("imdb_url"));
                                        movie.setRelease_date(new SimpleDateFormat("yyyy-mm-dd").parse(jsonMoviesObject.getString("release_date")));
                                        movie.setCreated_at(new SimpleDateFormat("yyyy-mm-dd hh:mm:ss").parse(jsonMoviesObject.getString("created_at")));
                                        movie.setFlagSeen(jsonMoviesObject.getBoolean("flagSeen"));
                                        movie.setFlagFavorite(jsonMoviesObject.getBoolean("flagFavorite"));

                                        movies.add(movie);
                                    }
                                    Log.d("Movies",movies.toString());
                                    buildRecyclerView();

                                } catch (JSONException | ParseException e) {
                                    e.printStackTrace();
                                }
                            }
                        }
                    }

                    @Override
                    public void onError(ANError error) {
                        if (error.getErrorCode() != 0) {
                            // received error from server
                            // error.getErrorCode() - the error code from server
                            // error.getErrorBody() - the error body from server
                            // error.getErrorDetail() - just an error detail
                            Log.d("test", "onError errorCode : " + error.getErrorCode()+error.toString());
                            Log.d("test", "onError errorBody : " + error.getErrorBody()+error.toString());
                            Log.d("test", "onError errorDetail : " + error.getErrorDetail()+error.toString());

                        } else {
                            // error.getErrorDetail() : connectionError, parseError, requestCancelledError
                            Log.d("test", "onError errorDetail : " + error.getErrorDetail()+error.toString());
                        }
                    }
                });
    }

    public void buildRecyclerView(){

        Log.d("rc Movies",movies.toString());
        recyclerView.setLayoutManager(new GridLayoutManager(this, 7)); //3 = column count
        RecyclerAdapter Adapter = new RecyclerAdapter(this, movies);
        recyclerView.setAdapter(Adapter);

    }


}