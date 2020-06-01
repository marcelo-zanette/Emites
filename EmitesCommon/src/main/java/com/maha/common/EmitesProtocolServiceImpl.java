package com.maha.common;

import com.maha.exception.EmitesProtocolException;

import java.util.Optional;

public class EmitesProtocolServiceImpl implements EmitesProtocolService {

    public String extractValue(Optional<String> protocolValue) throws Exception, EmitesProtocolException {

        if(!protocolValue.isPresent()) {
            throw new Exception("Value is not present");
        }

        String rawValue = protocolValue.get();

//        if(rawValue.substring(rawValue.length()-1).equals("\n")) {
//            rawValue = rawValue.substring(0, rawValue.length()-1);
//        }

        int splitPos = rawValue.indexOf(":");

        if(splitPos <0) {
            throw new EmitesProtocolException(
                    String.format("Emites Protocol Exception error - QueryLength not found (%s)", rawValue)
            );
        }

        int valueLength = Integer.parseInt(rawValue.substring(0, splitPos));
        String value = rawValue.substring(splitPos + 1);

        if(valueLength != value.length()) {
            throw new EmitesProtocolException(
                    String.format("Emites Protocol Exception error - Value length does not match (%s)", rawValue)
            );
        }

        return value;
    }

    /*


    private String extractValueFromRequest(Optional<String> request) throws EmitesProtocolException {

        int splitPos = response.indexOf(":");
        int responseSize = Integer.parseInt(response.substring(0, splitPos));

        String responseValue = response.substring(splitPos);

        if (request.get().indexOf(':') >= 1) {
            String[] requestQuery = request.get().split(":");

            int len = Integer.parseInt(requestQuery[0].trim());
            String queryValue = requestQuery[1].trim();

            if (queryValue.length() != len) {
                throw new EmitesProtocolException(
                        String.format("Request package not well formed: (%s)", request.get())
                );
            }
        }

        return true;
    }


     */
}
