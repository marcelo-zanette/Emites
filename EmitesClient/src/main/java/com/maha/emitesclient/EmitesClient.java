package com.maha.emitesclient;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.maha.config.ConfigModule;

import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;
import java.nio.CharBuffer;
import java.nio.charset.StandardCharsets;
import java.util.Optional;

/**
 * Main class for EmitesClient
 */

public class EmitesClient {

    private static Injector injector;


    public static void main(String[] args) {

        // check if args were informed
        if (args.length != 2) {
            System.err.println("Use: java EmitesClient <host name> <port number>");
            System.exit(1);
        }

        // some user advices
        System.out.println("-------------------------");
        System.out.println("  EmitesClient starting");
        System.out.println("-------------------------");
        System.out.println(String.format("Trying to connect to %s on port %s", args[0], args[1]));


        try {
            String hostname = args[0];
            int portNumber = Integer.parseInt(args[1]);


            injector = Guice.createInjector(new ConfigModule());       // start DI module
            injector.getInstance(EmitesCommandline.class).start(hostname, portNumber);

        } catch (Exception e) {
            e.printStackTrace();
            System.exit(1);
        }
    }

}