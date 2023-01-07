package org.example;

import org.example.trade.TradeClient;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class SellClient {
    public static void main(String[] args) throws InterruptedException, IOException {

        String token = "Your Token Here";

        TradeClient sellClient = new TradeClient();
        sellClient.setToken(token);

        while (true) {
            sellClient.doSell();
            TimeUnit.SECONDS.sleep(5);
        }

    }
}
