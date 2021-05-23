package com.example.likedemo.models;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Date;

public class Movie implements Parcelable  {
    Integer id;
    String type;
    String display_name;
    Integer duration;
    Integer price;
    Integer rating;
    Integer number_of_raters;
    String writers;
    String stars;
    String directors;
    String display_description_en;
    String display_description_ar;
    String media_URL;
    String photo_URL;
    String trailer_URL;
    String imdb_URL;
    Date release_date;
    Date created_at;
    Boolean flagSeen;
    Boolean flagFavorite;

    public Movie(Integer id, String type, String display_name, Integer duration, Integer price, Integer rating, Integer number_of_raters, String writers, String stars, String directors, String display_description_en, String display_description_ar, String media_URL, String photo_URL, String trailer_URL, String imdb_URL, Date release_date, Date created_at, Boolean flagSeen, Boolean flagFavorite) {
        this.id = id;
        this.type = type;
        this.display_name = display_name;
        this.duration = duration;
        this.price = price;
        this.rating = rating;
        this.number_of_raters = number_of_raters;
        this.writers = writers;
        this.stars = stars;
        this.directors = directors;
        this.display_description_en = display_description_en;
        this.display_description_ar = display_description_ar;
        this.media_URL = media_URL;
        this.photo_URL = photo_URL;
        this.trailer_URL = trailer_URL;
        this.imdb_URL = imdb_URL;
        this.release_date = release_date;
        this.created_at = created_at;
        this.flagSeen = flagSeen;
        this.flagFavorite = flagFavorite;
    }

    public Movie() {
    }

    protected Movie(Parcel in) {
        if (in.readByte() == 0) {
            id = null;
        } else {
            id = in.readInt();
        }
        type = in.readString();
        display_name = in.readString();
        if (in.readByte() == 0) {
            duration = null;
        } else {
            duration = in.readInt();
        }
        if (in.readByte() == 0) {
            price = null;
        } else {
            price = in.readInt();
        }
        if (in.readByte() == 0) {
            rating = null;
        } else {
            rating = in.readInt();
        }
        if (in.readByte() == 0) {
            number_of_raters = null;
        } else {
            number_of_raters = in.readInt();
        }
        writers = in.readString();
        stars = in.readString();
        directors = in.readString();
        display_description_en = in.readString();
        display_description_ar = in.readString();
        media_URL = in.readString();
        photo_URL = in.readString();
        trailer_URL = in.readString();
        imdb_URL = in.readString();
        byte tmpFlagSeen = in.readByte();
        flagSeen = tmpFlagSeen == 0 ? null : tmpFlagSeen == 1;
        byte tmpFlagFavorite = in.readByte();
        flagFavorite = tmpFlagFavorite == 0 ? null : tmpFlagFavorite == 1;
    }

    public static final Creator<Movie> CREATOR = new Creator<Movie>() {
        @Override
        public Movie createFromParcel(Parcel in) {
            return new Movie(in);
        }

        @Override
        public Movie[] newArray(int size) {
            return new Movie[size];
        }
    };

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDisplay_name() {
        return display_name;
    }

    public void setDisplay_name(String display_name) {
        this.display_name = display_name;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }

    public Integer getNumber_of_raters() {
        return number_of_raters;
    }

    public void setNumber_of_raters(Integer number_of_raters) {
        this.number_of_raters = number_of_raters;
    }

    public String getWriters() {
        return writers;
    }

    public void setWriters(String writers) {
        this.writers = writers;
    }

    public String getStars() {
        return stars;
    }

    public void setStars(String stars) {
        this.stars = stars;
    }

    public String getDirectors() {
        return directors;
    }

    public void setDirectors(String directors) {
        this.directors = directors;
    }

    public String getDisplay_description_en() {
        return display_description_en;
    }

    public void setDisplay_description_en(String display_description_en) {
        this.display_description_en = display_description_en;
    }

    public String getDisplay_description_ar() {
        return display_description_ar;
    }

    public void setDisplay_description_ar(String display_description_ar) {
        this.display_description_ar = display_description_ar;
    }

    public String getMedia_URL() {
        return media_URL;
    }

    public void setMedia_URL(String media_URL) {
        this.media_URL = media_URL;
    }

    public String getPhoto_URL() {
        return photo_URL;
    }

    public void setPhoto_URL(String photo_URL) {
        this.photo_URL = photo_URL;
    }

    public String getTrailer_URL() {
        return trailer_URL;
    }

    public void setTrailer_URL(String trailer_URL) {
        this.trailer_URL = trailer_URL;
    }

    public String getImdb_URL() {
        return imdb_URL;
    }

    public void setImdb_URL(String imdb_URL) {
        this.imdb_URL = imdb_URL;
    }

    public Date getRelease_date() {
        return release_date;
    }

    public void setRelease_date(Date release_date) {
        this.release_date = release_date;
    }

    public Date getCreated_at() {
        return created_at;
    }

    public void setCreated_at(Date created_at) {
        this.created_at = created_at;
    }

    public Boolean getFlagSeen() {
        return flagSeen;
    }

    public void setFlagSeen(Boolean flagSeen) {
        this.flagSeen = flagSeen;
    }

    public Boolean getFlagFavorite() {
        return flagFavorite;
    }

    public void setFlagFavorite(Boolean flagFavorite) {
        this.flagFavorite = flagFavorite;
    }

    @Override
    public String toString() {
        return "Movie{" +
                "id=" + id +
                ", type='" + type + '\'' +
                ", display_name='" + display_name + '\'' +
                ", duration=" + duration +
                ", price=" + price +
                ", rating=" + rating +
                ", number_of_raters=" + number_of_raters +
                ", writers='" + writers + '\'' +
                ", stars='" + stars + '\'' +
                ", directors='" + directors + '\'' +
                ", display_description_en='" + display_description_en + '\'' +
                ", display_description_ar='" + display_description_ar + '\'' +
                ", media_URL=" + media_URL +
                ", photo_URL=" + photo_URL +
                ", trailer_URL=" + trailer_URL +
                ", imdb_URL=" + imdb_URL +
                ", release_date=" + release_date +
                ", created_at=" + created_at +
                ", flagSeen=" + flagSeen +
                ", flagFavorite=" + flagFavorite +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        if (id == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(id);
        }
        dest.writeString(type);
        dest.writeString(display_name);
        if (duration == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(duration);
        }
        if (price == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(price);
        }
        if (rating == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(rating);
        }
        if (number_of_raters == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(number_of_raters);
        }
        dest.writeString(writers);
        dest.writeString(stars);
        dest.writeString(directors);
        dest.writeString(display_description_en);
        dest.writeString(display_description_ar);
        dest.writeString(media_URL);
        dest.writeString(photo_URL);
        dest.writeString(trailer_URL);
        dest.writeString(imdb_URL);
        dest.writeByte((byte) (flagSeen == null ? 0 : flagSeen ? 1 : 2));
        dest.writeByte((byte) (flagFavorite == null ? 0 : flagFavorite ? 1 : 2));
    }
}
