package com.messagex.handler;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.io.OutputStream;

public class AuthoriseFailHandler implements HttpHandler {
    private static ObjectMapper mapper = new ObjectMapper();

    static {
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        Headers responseHeaders = exchange.getResponseHeaders();
        responseHeaders.set("Access-Control-Allow-Origin", "*");
        responseHeaders.set("Content-Type", "application/json");
        OutputStream outputStream = exchange.getResponseBody();
        String responseStr = "{\"success\": false }";
        exchange.sendResponseHeaders(401, responseStr.length());
        outputStream.write(responseStr.getBytes());
        outputStream.flush();
        outputStream.close();
    }
}
