package com.maha.emitesserver;

import javax.inject.Inject;
import java.io.*;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.Optional;

/**
 * Allows multiple socket client connection
 */
public class EmitesServerThread extends Thread {

    private Socket socket = null;
    private InputStream inStream;
    private OutputStream outStream;

    private boolean connected = false;

    private EmitesProtocol emitesProtocol;


    public void setSocket(Socket socket) {
        this.socket = socket;
    }


    @Inject
    public EmitesServerThread(EmitesProtocol emitesProtocol) {
        super("EmitesServerThread");
        this.emitesProtocol = emitesProtocol;
    }


    private Optional<String> waitForRequest(InputStream inStream) throws IOException {
        Optional<String> request = Optional.empty();

        byte buffer[] = new byte[8 * 1024];
        int size = inStream.read(buffer);

        if(size > 0) {
            request = Optional.ofNullable(new String(buffer, 0, size, StandardCharsets.UTF_8));

            System.out.println(
                    String.format("[Thread %d] Request: %s", this.getId(), request.get())
            );
        }

        return request;
    }

    private void doResponse(Optional<String> value) throws IOException {

        Optional<String> response = Optional.of("" + value.get().length() + ":" + value.get());

        if(response.isPresent()) {
            outStream.write(
                    response.get().getBytes(StandardCharsets.UTF_8)
            );

            System.out.println(
                    String.format("[Thread %d] Response: %s", this.getId(), response.get())
            );
        }

    }

    public void run() {

        try {

            inStream = socket.getInputStream();
            outStream = socket.getOutputStream();

            Optional<String> request;
            Optional<String> response;

            connected = true;
            System.out.println(
                    String.format("[Thread %d] EmitesServer started.", this.getId())
            );

            while(connected) {

                request = waitForRequest(inStream);

                if(request.isPresent()) {

                    response = emitesProtocol.processInput(request);

                    doResponse(response);

                    if (response.get().equalsIgnoreCase("exit")) {
                        connected = false;
                    }

                } else {
                    connected = false;

                    System.out.println(
                            String.format("[Thread %d] Client disconnected.", this.getId())
                    );
                }

            }

            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
