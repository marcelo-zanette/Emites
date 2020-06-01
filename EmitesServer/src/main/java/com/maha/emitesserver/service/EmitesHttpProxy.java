package com.maha.emitesserver.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Optional;

public class EmitesHttpProxy implements EmitesProxy {

    private static final String USER_AGENT = "Mozilla/5.0";

    private static final String GET_URL = "https://v2.sg.media-imdb.com/suggestion/";

    public Optional<String> doRemoteCall(String searchValue) {

        Optional<String> response = Optional.empty();

        try {
            System.out.println("Calling IMDb service...");

            URL urlObject = new URL(GET_URL + searchValue.substring(0, 1) + "/" + searchValue.replace(' ', '_') + ".json");

            HttpURLConnection con = (HttpURLConnection) urlObject.openConnection();

            con.setRequestMethod("GET");
            con.setRequestProperty("User-Agent", USER_AGENT);
            int responseCode = con.getResponseCode();

            System.out.println("GET Response Code :: " + responseCode);

            if (responseCode == HttpURLConnection.HTTP_OK) { // success

                BufferedReader in =
                        new BufferedReader(
                                new InputStreamReader(con.getInputStream())
                        );

                String inputLine;
                StringBuffer responseBuf = new StringBuffer();

                while ((inputLine = in.readLine()) != null) {
                    responseBuf.append(inputLine);
                }
                in.close();

                response = Optional.of(responseBuf.toString());

                // print result
                System.out.println(responseBuf.toString());

            } else {
                System.out.println("GET request not worked");

            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }

        return response;
    }

}
