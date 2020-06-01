package com.maha.config;

import com.google.inject.AbstractModule;
import com.maha.common.EmitesProtocolService;
import com.maha.common.EmitesProtocolServiceImpl;

/**
 * Setup all the Guice object bindings
 */

public class ConfigModule extends AbstractModule {


    @Override
    protected void configure() {

//        bind(EmitesProtocol.class).to(EmitesProtocolImpl.class);
//
//        bind(EmitesService.class).to(EmitesIMDBService.class);
//
//        bind(EmitesProxy.class).to(EmitesHttpProxy.class);

        bind(EmitesProtocolService.class).to(EmitesProtocolServiceImpl.class);

    }

}
