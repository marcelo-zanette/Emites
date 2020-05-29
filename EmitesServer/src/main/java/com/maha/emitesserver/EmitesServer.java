package com.maha.emitesserver;

import java.io.IOException;
import java.net.ServerSocket;

/**
 * Main class for EmitesServer
 */
public class EmitesServer {

    public static void main(String[] args) {


        if (args.length != 1) {
            System.err.println("Usage: java EmitesServer <port number>");
            System.exit(1);
        }

        System.out.println("EmitesServer starting.");
        System.out.println(String.format("Listening on port %s", args[0]));

        int portNumber = Integer.parseInt(args[0]);
        boolean listening = true;

        try (ServerSocket serverSocket = new ServerSocket(portNumber)) {

            while (listening) {
                new EmitesServerThread(serverSocket.accept()).start();
            }

        } catch (IOException e) {
            System.err.println("Could not listen on port " + portNumber);
            System.exit(-1);
        }
    }

}
