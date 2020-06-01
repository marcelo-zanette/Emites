package com.maha.emitesclient;

import com.maha.common.EmitesConsts;
import com.maha.common.EmitesProtocolService;
import com.maha.exception.EmitesProtocolException;

import javax.inject.Inject;
import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;
import java.nio.charset.StandardCharsets;
import java.util.Optional;

/**
 * Manage the user interaction at command line
 */
public class EmitesCommandline {

    private Socket socket;
    private InputStream inStream;
    private OutputStream outStream;

    private EmitesProtocolService emitesProtocolService;

    private boolean connected;

    @Inject
    public EmitesCommandline(EmitesProtocolService emitesProtocolService) {
        this.emitesProtocolService = emitesProtocolService;
    }

    public void start(String hostname, int port) {
        try {

            socket = new Socket(hostname, port);
            inStream = socket.getInputStream();
            outStream = socket.getOutputStream();

            BufferedReader stdIn =  new BufferedReader(new InputStreamReader(System.in));

            String fromServer;
            String fromUser;

            // wait for the first response
            doRequest(EmitesConsts.PROTOCOL_HANDSHAKE_REQUEST);
//            fromServer = waitForResponse(inStream);
//            if(!EmitesConsts.PROTOCOL_HANDSHAKE_RESPONSE.equalsIgnoreCase(fromServer)) {
//                throw new EmitesProtocolException("Protocol Error - handshake was not closed.");
//            }

            connected = true;

            while(connected) {

                fromServer = waitForResponse(inStream);

                if(!connected) break;

                if (fromServer.equalsIgnoreCase("exit")) {
                    connected = false;
                }

                fromUser = stdIn.readLine();
                if (fromUser != null) {
                    if (fromUser.equalsIgnoreCase("exit")) {
                        connected = false;

                    } else {
                        doRequest(fromUser);
                    }
                }
            }

        } catch (UnknownHostException e) {
            System.err.println(String.format("Error connecting to host %s:%d - host unknown.", hostname, port));
            System.exit(1);

        } catch (EmitesProtocolException e) {
            System.err.println(String.format("Error connecting to host %s:%d", hostname, port));
            e.printStackTrace();
            System.exit(1);

        } catch (Exception e) {
            System.err.println(String.format("Error connecting to host %s:%d", hostname, port));
            System.exit(1);
        }

    }

    private void doRequest(String valueSearch) throws IOException {

        System.out.println("Client search for [" + valueSearch + "]");

        valueSearch = "" + valueSearch.length() + ":" + valueSearch.trim();

        outStream.write(
                valueSearch.getBytes(StandardCharsets.UTF_8)
        );
    }

    private String waitForResponse(InputStream inStream) throws EmitesProtocolException, Exception {
        Optional<String> response;

        byte buffer[] = new byte[8 * 1024];
        int size = inStream.read(buffer);

        response = Optional.of(new String(buffer, 0, size, StandardCharsets.UTF_8));

        String responseValue = emitesProtocolService.extractValue(response);

        if(EmitesConsts.PROTOCOL_HANDSHAKE_RESPONSE.startsWith(responseValue)) {
            System.out.println("EmitesClient connected.");
        }

        System.out.println("Response from server:\n" + responseValue);

        return responseValue;
    }

}
