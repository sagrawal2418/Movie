package com.mm.movie.model;

import java.util.List;

public class MovieDetail {
    public String imdbId;
    public String title;
    public int year;
    public String rated;
    public float imdbRating;
    public int imdbVotes;
    public String poster;
    public String released;
    public String runtime;
    public List<String> genre;
    public String director;
    public String writer;
    public List<String> actors;
    public String plot;
    public List<String> language;
    public String country;
    public String awards;
    public String metascore;

    @Override
    public String toString() {
        return "MovieDetail{" +
                "imdbId='" + imdbId + '\'' +
                ", title='" + title + '\'' +
                ", year=" + year +
                ", rated='" + rated + '\'' +
                ", imdbRating=" + imdbRating +
                ", imdbVotes=" + imdbVotes +
                ", poster='" + poster + '\'' +
                ", released='" + released + '\'' +
                ", runtime='" + runtime + '\'' +
                ", genre=" + genre +
                ", director='" + director + '\'' +
                ", writer='" + writer + '\'' +
                ", actors=" + actors +
                ", plot='" + plot + '\'' +
                ", language=" + language +
                ", country='" + country + '\'' +
                ", awards='" + awards + '\'' +
                ", metascore='" + metascore + '\'' +
                '}';
    }
}
