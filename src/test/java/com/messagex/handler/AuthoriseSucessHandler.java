package com.messagex.handler;

import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.io.OutputStream;

public class AuthoriseSucessHandler implements HttpHandler {
    @Override
    public void handle(HttpExchange exchange) throws IOException {
        Headers responseHeaders = exchange.getResponseHeaders();
        responseHeaders.set("Access-Control-Allow-Origin", "*");
        responseHeaders.set("Content-Type", "application/json");
        OutputStream outputStream = exchange.getResponseBody();
        String responseStr = "{\"data\": { \"id\": 1234, \"apiKeyId\": 1234, \"bearerToken\": \"testBearerToken123456\", " +
                "\"refreshToken\": \"testRefreshToken1234\", \"expiresAt\": \"2020-12-12 23:59:59\"," +
                "\"createdAt\": \"2020-12-12 23:59:59\", \"updatedAt\": \"2020-12-12 23:59:59\"," +
                "\"createdAtEpoch\": 1234567890, \"updatedAtEpoch\": 1234567890 } }";
        exchange.sendResponseHeaders(200, responseStr.length());
        outputStream.write(responseStr.getBytes());
        outputStream.flush();
        outputStream.close();
    }
}
