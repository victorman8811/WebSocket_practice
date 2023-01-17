package org.example.trade;

import lombok.Getter;
import lombok.Setter;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

@Getter
@Setter
public class TradeClient {

    private String token;

    public void doBuy (String limitPrice) throws IOException {

        String json = "{\n" +
                "  \"symbol\": \"BTC-USD\",\n" +
                "  \"size\": 0.001,\n" +
                "  \"price\": "+ limitPrice +",\n" +
                "  \"side\": \"BUY\",\n" +
                "  \"type\": \"LIMIT\"\n" +
                "}";
        StringEntity entity = new StringEntity(json);

        HttpClient httpClient = HttpClientBuilder.create().build();
        HttpPost httpPost = new HttpPost("https://api.btse.co/spot/api/v3.1/order");
        httpPost.setEntity(entity);
        httpPost.setHeader("Authorization", "Bearer " + token);
        HttpResponse response = httpClient.execute(httpPost);

        System.out.println("Buy successfully! The response is:" + "\n" + EntityUtils.toString(response.getEntity(), "UTF-8"));
    }

    public void doSell (String limitPrice) throws IOException {

        String json = "{\n" +
                "  \"symbol\": \"BTC-USD\",\n" +
                "  \"size\": 0.001,\n" +
                "  \"price\": "+ limitPrice +",\n" +
                "  \"side\": \"SELL\",\n" +
                "  \"type\": \"LIMIT\"\n" +
                "}";
        StringEntity entity = new StringEntity(json);

        HttpClient httpClient = HttpClientBuilder.create().build();
        HttpPost httpPost = new HttpPost("https://api.btse.co/spot/api/v3.1/order");
        httpPost.setEntity(entity);
        httpPost.setHeader("Authorization", "Bearer " + token);
        HttpResponse response = httpClient.execute(httpPost);

        System.out.println("Sell successfully! The response is:" + "\n" + EntityUtils.toString(response.getEntity(), "UTF-8"));
    }

    public void doCancell () throws IOException {

        HttpClient httpClient = HttpClientBuilder.create().build();
        HttpDelete httpDelete = new HttpDelete("https://api.btse.co/spot/api/v3.1/order?symbol=BTC-USD");
        httpDelete.setHeader("Authorization", "Bearer " + token);
        HttpResponse response = httpClient.execute(httpDelete);

        System.out.println("Delete successfully! The response is:" + "\n" + EntityUtils.toString(response.getEntity(), "UTF-8"));
    }
}
