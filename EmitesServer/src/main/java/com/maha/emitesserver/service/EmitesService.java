package com.maha.emitesserver.service;

import com.maha.emitesserver.model.Movie;

import java.util.List;
import java.util.Optional;

public interface EmitesService {

    Optional<List<Movie>> doSearch(String queryValue);

}
