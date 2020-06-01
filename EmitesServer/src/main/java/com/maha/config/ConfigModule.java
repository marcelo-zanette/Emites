package com.maha.config;

import com.google.inject.AbstractModule;
import com.maha.common.EmitesProtocolService;
import com.maha.common.EmitesProtocolServiceImpl;
import com.maha.emitesserver.EmitesProtocol;
import com.maha.emitesserver.EmitesProtocolImpl;
import com.maha.emitesserver.service.EmitesHttpProxy;
import com.maha.emitesserver.service.EmitesIMDBService;
import com.maha.emitesserver.service.EmitesProxy;
import com.maha.emitesserver.service.EmitesService;

/**
 * Setup all the Guice object bindings
 */
public class ConfigModule extends AbstractModule {


    @Override
    protected void configure() {

        bind(EmitesProtocol.class).to(EmitesProtocolImpl.class);

        bind(EmitesService.class).to(EmitesIMDBService.class);

        bind(EmitesProxy.class).to(EmitesHttpProxy.class);

        bind(EmitesProtocolService.class).to(EmitesProtocolServiceImpl.class);

    }
}
