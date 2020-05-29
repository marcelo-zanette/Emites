package com.maha.emitesserver.model;

/**
 * Represents each movie from IMDb response search
 */
public class Movie {

    private String id;
    private String title;
    private String stars;
    private int rank;
    private int year;

    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }

    public String getStars() {
        return stars;
    }
    public void setStars(String stars) {
        this.stars = stars;
    }

    public int getRank() {
        return rank;
    }
    public void setRank(int rank) {
        this.rank = rank;
    }

    public int getYear() {
        return year;
    }
    public void setYear(int year) {
        this.year = year;
    }

}
