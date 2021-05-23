package com.example.likedemo.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.likedemo.R;
import com.example.likedemo.activities.MovieDetails;
import com.example.likedemo.models.Movie;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.RecyclerViewHolder>{
     Context mContext;
     ArrayList<Movie> moviesList;

    public RecyclerAdapter(Context context,ArrayList<Movie> movieArrayList){
        this.mContext = context;
        this.moviesList = movieArrayList;
    }

    @NonNull
    @Override
    public RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.movie_item,parent,false);
        return new RecyclerViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewHolder holder, int position) {
        Movie movie = moviesList.get(position);
        String imageURL = movie.getPhoto_URL();
        String title = movie.getDisplay_name();
        String id = movie.getId().toString();

        holder.mTextView.setText(title);
        Picasso.get().load(imageURL).fit().centerInside().into(holder.mImageView);
        holder.mImageView.setTag(id);

        holder.mImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentPlayer = new Intent(v.getContext(), MovieDetails.class);
                for(Movie movie : moviesList) {
                    if(movie.getId().toString().equals(holder.mImageView.getTag())){
                        Movie m = movie;
                        intentPlayer.putExtra("movie", m);
                        v.getContext().startActivity(intentPlayer);
                        break;
                    }
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return moviesList.size();
    }


    public class RecyclerViewHolder extends RecyclerView.ViewHolder{

        ImageView mImageView;
        TextView mTextView;


        public RecyclerViewHolder(@NonNull View itemView) {
            super(itemView);
            mImageView = itemView.findViewById(R.id.movieImage);
            mTextView = itemView.findViewById(R.id.movieTitle);
        }
    }

}
