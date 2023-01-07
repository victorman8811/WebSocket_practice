package org.example.trade;

import lombok.Getter;
import lombok.Setter;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

@Getter
@Setter
public class TradeClient {

    private String token;

    public void doBuy () throws IOException {

        String json = "{\"side\":\"BUY\",\"type\":\"MARKET\",\"txType\":\"LIMIT\",\"symbol\":\"BTC-USD\",\"size\":\"0.00001\",\"clOrderID\":\"_fdejfgc1673075053849\"}";
        StringEntity entity = new StringEntity(json);

        HttpClient httpClient = HttpClientBuilder.create().build();
        HttpPost httpPost = new HttpPost("https://api.btse.co/spot/api/v3.1/order");
        httpPost.setEntity(entity);
        httpPost.setHeader("Authorization", "Bearer " + token);
        HttpResponse response = httpClient.execute(httpPost);

        System.out.println("Buy successfully! The response is:" + "\n" + EntityUtils.toString(response.getEntity(), "UTF-8"));
    }

    public void doSell () throws IOException {

        String json = "{\"side\":\"SELL\",\"type\":\"MARKET\",\"txType\":\"LIMIT\",\"symbol\":\"BTC-USD\",\"size\":\"0.00001\",\"clOrderID\":\"_qbg1673075797642\"}";
        StringEntity entity = new StringEntity(json);

        HttpClient httpClient = HttpClientBuilder.create().build();
        HttpPost httpPost = new HttpPost("https://api.btse.co/spot/api/v3.1/order");
        httpPost.setEntity(entity);
        httpPost.setHeader("Authorization", "Bearer " + token);
        HttpResponse response = httpClient.execute(httpPost);

        System.out.println("Sell successfully! The response is:" + "\n" + EntityUtils.toString(response.getEntity(), "UTF-8"));
    }
}
