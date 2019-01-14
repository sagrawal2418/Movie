package com.mm.movie.model;

import android.os.Parcel;
import android.os.Parcelable;

public class Movie implements Parcelable {
    public static final Parcelable.Creator<Movie> CREATOR = new Parcelable.Creator<Movie>() {
        @Override
        public Movie createFromParcel(Parcel source) {
            return new Movie(source);
        }

        @Override
        public Movie[] newArray(int size) {
            return new Movie[size];
        }
    };
    public String imdbId;
    public int rank;
    public String title;
    public int year;
    public float imdbRating;
    public int imdbVotes;
    public String poster;
    public String imdbLink;

    public Movie() {
    }

    protected Movie(Parcel in) {
        this.imdbId = in.readString();
        this.rank = in.readInt();
        this.title = in.readString();
        this.year = in.readInt();
        this.imdbRating = in.readFloat();
        this.imdbVotes = in.readInt();
        this.poster = in.readString();
        this.imdbLink = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.imdbId);
        dest.writeInt(this.rank);
        dest.writeString(this.title);
        dest.writeInt(this.year);
        dest.writeFloat(this.imdbRating);
        dest.writeInt(this.imdbVotes);
        dest.writeString(this.poster);
        dest.writeString(this.imdbLink);
    }

    @Override
    public String toString() {
        return "Movie{" +
                "imdbId='" + imdbId + '\'' +
                ", rank=" + rank +
                ", title='" + title + '\'' +
                ", year=" + year +
                ", imdbRating=" + imdbRating +
                ", imdbVotes=" + imdbVotes +
                ", poster='" + poster + '\'' +
                ", imdbLink='" + imdbLink + '\'' +
                '}';
    }
}
