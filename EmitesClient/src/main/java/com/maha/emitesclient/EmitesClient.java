package com.maha.emitesclient;

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

    private static boolean connected = false;


    private static String waitForResponse(InputStream inStream) throws IOException {
        String response;

        byte buffer[] = new byte[8 * 1024];
        int size = inStream.read(buffer);

        response = new String(buffer, 0, size, StandardCharsets.UTF_8);

        return response;
    }

    public static void main(String[] args) {

        // check if args were informed
        if (args.length != 2) {
            System.err.println(
                    "Usage: java EmitesClient <host name> <port number>");
            System.exit(1);
        }

        // some user advices
        System.out.println("EmitesClient starting.");
        System.out.println(String.format("Trying to connect to %s on port %s", args[0], args[1]));

        String hostName = args[0];
        int portNumber = Integer.parseInt(args[1]);

        try (
                Socket socket = new Socket(hostName, portNumber);
                InputStream inStream = socket.getInputStream();
                OutputStream outStream = socket.getOutputStream();
        ) {

            BufferedReader stdIn =  new BufferedReader(new InputStreamReader(System.in));

            connected = true;
            System.out.println("EmitesClient connected.");

            String fromServer;
            String fromUser;

            while(connected) {

                fromServer = waitForResponse(inStream);

                if(!connected) break;

                System.out.println("Server = " + fromServer);

                if (fromServer.equalsIgnoreCase("exit")) break;

                fromUser = stdIn.readLine();
                if (fromUser != null) {
                    if (fromUser.equalsIgnoreCase("exit")) break;

                    fromUser = "" + fromUser.length() + ":" + fromUser.trim();

                    System.out.println("Client [" + fromUser + "]");
                    outStream.write(
                        fromUser.getBytes(StandardCharsets.UTF_8)
                    );
                }
            }

        } catch (UnknownHostException e) {
            System.err.println("Don't know about host " + hostName);
            System.exit(1);

        } catch (IOException e) {
            System.err.println("Couldn't get I/O for the connection to " + hostName);
            System.exit(1);
        }

    }

}