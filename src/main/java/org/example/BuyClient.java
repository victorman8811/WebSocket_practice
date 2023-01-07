package org.example;

import org.example.trade.TradeClient;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class BuyClient {
    public static void main(String[] args) throws InterruptedException, IOException {

        String token = "Your Token Here";

        TradeClient buyClient = new TradeClient();
        buyClient.setToken(token);

        while (true) {
            buyClient.doBuy();
            TimeUnit.SECONDS.sleep(5);
        }

    }
}