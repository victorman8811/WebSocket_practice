package org.example;

import org.example.trade.TradeClient;
import org.example.websocket.Client;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;

public class GetOrderbook {
    public static void main(String[] args) throws URISyntaxException, IOException, InterruptedException {

        Client client = new Client(new URI("wss://ws.btse.co/ws/spot"));
        client.sendMessage("{\"op\":\"subscribe\",\"args\":[\"orderBookApi:BTC-USD_0\"]}");

        String token = "Your token here";
        TradeClient tradeClient = new TradeClient();
        tradeClient.setToken(token);

        Thread.sleep(1000);

        String lastbuyQuotePrice = "";

        while (true) {
            String[] results = client.data.split("\"");
            ArrayList<String> buyQuotePrice = new ArrayList<>();
            ArrayList<String> sellQuotePrice = new ArrayList<>();
            boolean isBuyQuote = true;

            for (int i = 0; i < results.length; i++) {
                if (results[i].contains("sellQuote")) {
                    isBuyQuote = false;
                }
                if (results[i].contains("price") && isBuyQuote == true) {
                    buyQuotePrice.add(results[i + 2]);
                } else if (results[i].contains("price") && isBuyQuote == false) {
                    sellQuotePrice.add(results[i + 2]);
                }
            }

            System.out.println("BuyQuote is: " + buyQuotePrice);
            System.out.println("SellQuote is: " + sellQuotePrice);

            if(!lastbuyQuotePrice.equals(buyQuotePrice.get(4))){
                tradeClient.doCancell();
                tradeClient.doBuy(buyQuotePrice.get(4));
            }

            lastbuyQuotePrice = buyQuotePrice.get(4);

            //tradeClient.doSell(sellQuotePrice.get(4));

            Thread.sleep(10000);

        }

    }


}