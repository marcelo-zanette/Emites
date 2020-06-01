package com.maha.common;

import com.maha.exception.EmitesProtocolException;

import java.util.Optional;

public interface EmitesProtocolService {

    String extractValue(Optional<String> protocolValue) throws Exception, EmitesProtocolException;

}
