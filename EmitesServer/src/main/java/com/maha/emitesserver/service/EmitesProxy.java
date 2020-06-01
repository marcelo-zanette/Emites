package com.maha.emitesserver.service;

import java.util.Optional;

public interface EmitesProxy {

    Optional<String> doRemoteCall(String value);

}
