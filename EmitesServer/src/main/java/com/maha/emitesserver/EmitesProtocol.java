package com.maha.emitesserver;

import java.util.Optional;

public interface EmitesProtocol {

    Optional<String> processInput(Optional<String> request);

}
