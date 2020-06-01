package com.maha.emitesserver.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.google.inject.Inject;
import com.maha.emitesserver.model.Movie;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 * EmitesIMDBService : responsible for imdb search
 */
public class EmitesIMDBService implements EmitesService {

    @Inject
    private EmitesHttpProxy emitesHttpProxy; // = new EmitesHttpProxy();


    public Optional<List<Movie>> doSearch(String queryValue) {

        Optional<List<Movie>> movies = Optional.empty();

        try {

            Optional<String> response = emitesHttpProxy.doRemoteCall(queryValue);

            if(response.isPresent()) {

                JSONObject json = new JSONObject(response.get());
                JSONArray movieListJson = json.getJSONArray("d");

                if (movieListJson.length() > 0) {

                    movies = Optional.of(new ArrayList<>());

                    for (int i = 0; i < movieListJson.length(); i++) {

                        Movie movie = new Movie();

                        JSONObject movieJson = (JSONObject) movieListJson.get(i);

                        movie.setId(getJsonMovieString("id", movieJson));     // id
                        movie.setTitle(getJsonMovieString("l", movieJson));   // title
                        movie.setStars(getJsonMovieString("s", movieJson));   // stars
                        movie.setRank(getJsonMovieInt("rank", movieJson));    // movie rank
                        movie.setYear(getJsonMovieInt("y", movieJson));       // year

                        movies.get().add(movie);
                    }
                }

            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
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
