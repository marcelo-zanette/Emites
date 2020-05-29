package com.maha.emitesserver;

import com.maha.emitesserver.model.Movie;
import com.maha.emitesserver.service.EmitesService;

import java.io.IOException;
import java.util.List;

/**
 * Emites protocol, that is, processes the queries from multiple clients
 */
public class EmitesProtocol {

    private EmitesService emitesService = new EmitesService();


    public String processInput(String request) {

        String response = "auto-response";

        // process request here
        if(request != null) {

            if (request.indexOf(':') >= 1) {
                String[] requestQuery = request.split(":");

                String queryField = requestQuery[0].trim();
                String queryValue = requestQuery[1].trim();


                List<Movie> imdbMovies = null;
                try {
                    imdbMovies = emitesService.imdbSearch(queryField, queryValue);
                } catch (IOException e) {
                    e.printStackTrace();
                }

                response = packResponse(imdbMovies);
            }

        }

        return response;
    }

    private String packResponse(List<Movie> movies) {
        String responseString = "";

        for (Movie movie : movies) {
            responseString +=
                    String.format("[%s - %s - %d]\n",
                            movie.getId(),
                            movie.getTitle(),
                            movie.getYear()
                    );
        }

        return responseString;
    }

}
