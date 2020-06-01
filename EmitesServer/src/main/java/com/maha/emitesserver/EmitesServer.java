package com.maha.emitesserver;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.maha.config.ConfigModule;

import java.io.IOException;
import java.net.ServerSocket;

/**
 * Main class for EmitesServer
 */
public class EmitesServer {

    private static Injector injector;


    /**
     * Entry point for Dependency Injection structure
     */
    public void start(int listeningPort) {

        boolean listening = true;

        try (ServerSocket serverSocket = new ServerSocket(listeningPort)) {

            while (listening) {
                EmitesServerThread thread = injector.getInstance(EmitesServerThread.class);
                thread.setSocket(serverSocket.accept());
                thread.start();
            }

        } catch (IOException e) {
            System.err.println("Could not listen on port " + listeningPort);
            System.exit(-1);
        }
    }



    /**
     * Main entry point of the application
     */
    public static void main(String[] args) {

        if (args.length != 1) {
            System.err.println("Usage: java EmitesServer <port number>");
            System.exit(1);
        }

        System.out.println("-------------------------");
        System.out.println("  EmitesServer starting");
        System.out.println("-------------------------");
        System.out.println(String.format("Listening on port %s", args[0]));

        int listeningPort = Integer.parseInt(args[0]);

        injector = Guice.createInjector(new ConfigModule());       // start DI module
        injector.getInstance(EmitesServer.class).start(listeningPort);
    }

}
