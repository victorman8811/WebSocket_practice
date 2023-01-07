package org.example;

import org.example.websocket.Client;

import java.net.URI;
import java.net.URISyntaxException;

public class GetOrderbook {
    public static void main(String[] args) throws URISyntaxException, InterruptedException {
        sendMessage();
    }

    static void sendMessage() throws InterruptedException, URISyntaxException {
        Client client = new Client(new URI("wss://ws.btse.co/ws/spot"));

        while (true) {
            client.sendMessage("{\"op\":\"subscribe\",\"args\":[\"tradeHistoryApi:BTC-USD\"]}");
            Thread.sleep(1000);
        }
    }
}