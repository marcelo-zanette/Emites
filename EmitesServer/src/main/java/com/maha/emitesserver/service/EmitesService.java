package com.maha.emitesserver.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.maha.emitesserver.model.Movie;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


public class EmitesService {

    private EmitesHttpProxy emitesHttpProxy = new EmitesHttpProxy();


    public List<Movie> imdbSearch(String queryField, String queryValue) throws IOException {

        String response = emitesHttpProxy.doIMDBGetCall(queryValue);

        List<Movie> movies = new ArrayList<>();

        JSONObject json = new JSONObject(response);
        JSONArray movieListJson = json.getJSONArray("d");

        for(int i = 0; i < movieListJson.length(); i++) {

            Movie movie = new Movie();

            JSONObject movieJson = (JSONObject) movieListJson.get(i);

            movie.setId(getJsonMovieString("id", movieJson));     // id
            movie.setTitle(getJsonMovieString("l", movieJson));   // title
            movie.setStars(getJsonMovieString("s", movieJson));   // stars
            movie.setRank(getJsonMovieInt("rank", movieJson));    // movie rank
            movie.setYear(getJsonMovieInt("y", movieJson));       // year

            movies.add(movie);

        }

        return movies;
    }

    private String getJsonMovieString(String key, JSONObject movieJson) {
        String result = "";

        if(movieJson.has(key)) {
            result = movieJson.getString(key);
        }

        return result;
    }

    private int getJsonMovieInt(String key, JSONObject movieJson) {
        int result = 0;

        if(movieJson.has(key)) {
            result = movieJson.getInt(key);
        }

        return result;
    }


}
