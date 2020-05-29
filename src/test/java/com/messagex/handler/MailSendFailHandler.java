package com.messagex.handler;

import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.io.OutputStream;

public class MailSendFailHandler implements HttpHandler {
    @Override
    public void handle(HttpExchange exchange) throws IOException {
        Headers responseHeaders = exchange.getResponseHeaders();
        responseHeaders.set("Access-Control-Allow-Origin", "*");
        responseHeaders.set("Content-Type", "application/json");
        OutputStream outputStream = exchange.getResponseBody();
        String responseStr = "{\"success\": false, \"errors\": \"Something went wrong\" }";
        exchange.sendResponseHeaders(200, responseStr.length());
        outputStream.write(responseStr.getBytes());
        outputStream.flush();
        outputStream.close();
    }
}
