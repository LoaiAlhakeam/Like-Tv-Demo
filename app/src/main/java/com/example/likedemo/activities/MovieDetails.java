package com.example.likedemo.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.likedemo.R;
import com.example.likedemo.models.Movie;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class MovieDetails extends AppCompatActivity {

    TextView day,dayWritten,month,clock;
    TextView movieTitle;
    Button playButton;
    Button trailerButton;
    Button detailsButton;
    ConstraintLayout layout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_details);

        Movie m = getIntent().getParcelableExtra("movie");
        Log.d("The Movie",m.toString());

        layout = findViewById(R.id.movieDetailsLayout);
        movieTitle = (TextView) findViewById(R.id.theTitle);
        playButton = (Button) findViewById(R.id.playButton);
        trailerButton = (Button) findViewById(R.id.trailerButton);
        detailsButton = (Button) findViewById(R.id.detailsButton);

        day = (TextView) findViewById(R.id.dayViewer);
        dayWritten = (TextView) findViewById(R.id.dayWritrViewer);
        month = (TextView) findViewById(R.id.monthViewer);
        clock = (TextView) findViewById(R.id.clockViewer);

        day.setText(new SimpleDateFormat("dd ", Locale.getDefault()).format(new Date()));
        dayWritten.setText(new SimpleDateFormat("E",Locale.getDefault()).format(new Date()));
        month.setText(new SimpleDateFormat("MMMM",Locale.getDefault()).format(new Date()));
        clock.setText(new SimpleDateFormat("hh:mm",Locale.getDefault()).format(new Date()));


        movieTitle.setText(m.getDisplay_name());
        Picasso.get().load(m.getPhoto_URL()).into(new Target() {

            @Override
            public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
                layout.setBackground(new BitmapDrawable(bitmap));
            }

            @Override
            public void onBitmapFailed(Exception e, Drawable errorDrawable) {

            }

            @Override
            public void onPrepareLoad(Drawable placeHolderDrawable) {

            }
        });

        playButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent playerActivity = new Intent(MovieDetails.this,player.class);
                playerActivity.putExtra("videoUri" ,m.getMedia_URL());
                v.getContext().startActivity(playerActivity);
            }
        });

    }
}