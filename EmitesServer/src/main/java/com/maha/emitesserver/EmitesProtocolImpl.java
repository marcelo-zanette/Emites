package com.maha.emitesserver;

import com.maha.common.EmitesConsts;
import com.maha.common.EmitesProtocolServiceImpl;
import com.maha.emitesserver.model.Movie;
import com.maha.emitesserver.service.EmitesService;
import com.maha.exception.EmitesProtocolException;

import javax.inject.Inject;
import java.util.List;
import java.util.Optional;

/**
 * Emites protocol, that is, processes the queries from multiple clients
 */
public class EmitesProtocolImpl implements EmitesProtocol {

    private EmitesService emitesService;
    private EmitesProtocolServiceImpl emitesProtocolServiceImpl;


    @Inject
    public void EmitesProtocolImpl(EmitesService emitesService, EmitesProtocolServiceImpl emitesProtocolServiceImpl) {
        this.emitesService = emitesService;
        this.emitesProtocolServiceImpl = emitesProtocolServiceImpl;
    }


    public Optional<String> processInput(Optional<String> request) {

        Optional<String> response = Optional.empty();

        try {

            String requestValue = emitesProtocolServiceImpl.extractValue(request);

            if(EmitesConsts.PROTOCOL_HANDSHAKE_REQUEST.startsWith(requestValue)) {

                response = Optional.of(EmitesConsts.PROTOCOL_HANDSHAKE_RESPONSE);

            } else {

                Optional<List<Movie>> imdbMovies = emitesService.doSearch(requestValue);

                response = Optional.of(
                        prepareResponse(imdbMovies.get())
                );

            }

        } catch (EmitesProtocolException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();

        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();

        }

        return response;
    }

    private String prepareResponse(List<Movie> movies) {
        String responseString = "";

        for (Movie movie : movies) {
            responseString +=
                    String.format(
                            "%s - %s - %d\n",
                            movie.getId(),
                            movie.getTitle(),
                            movie.getYear()
                    );
        }

        return responseString;
    }

}
