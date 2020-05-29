package com.maha.emitesserver;

import java.io.*;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

/**
 * Allows multiple socket client connection
 */
public class EmitesServerThread extends Thread {

    private Socket socket = null;
    private boolean connected = false;



    private String waitForRequest(InputStream inStream) throws IOException {
        String request;

        byte buffer[] = new byte[8 * 1024];
        int size = inStream.read(buffer);

        request = new String(buffer, 0, size, StandardCharsets.UTF_8) + '\n';

        return request;
    }

    public EmitesServerThread(Socket socket) {
        super("EmitesServerThread");
        this.socket = socket;
    }

    public void run() {

        try (
                InputStream inStream = socket.getInputStream();
                OutputStream outStream = socket.getOutputStream();
        ) {
            String request;
            String response;

            connected = true;
            System.out.println(String.format("EmitesServer thread %s started ", this.getId()));

            EmitesProtocol emitesProtocol = new EmitesProtocol();

            // first response
            response = emitesProtocol.processInput(null);
            outStream.write(
                response.getBytes(StandardCharsets.UTF_8)
            );


            // keep listening
            //while ((request = bufferIn.readUTF()) != null) {
            while(connected) {

                request = waitForRequest(inStream);
                System.out.println("request = " + request);


                response = emitesProtocol.processInput(request);

                response = "" + response.length() + ":" + response;

                outStream.write(
                    response.getBytes(StandardCharsets.UTF_8)
                );

                if (response.equalsIgnoreCase("exit")) {
                    break;
                }

            }

            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
