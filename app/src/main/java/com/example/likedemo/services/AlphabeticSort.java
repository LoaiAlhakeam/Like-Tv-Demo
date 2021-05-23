package com.example.likedemo.services;

import com.example.likedemo.models.Movie;

import java.util.Comparator;

public class AlphabeticSort implements Comparator<Movie> {
        @Override
        public int compare(Movie m1, Movie m2) {
            return m2.getDisplay_name().compareToIgnoreCase(m1.getDisplay_name());
        }

}
