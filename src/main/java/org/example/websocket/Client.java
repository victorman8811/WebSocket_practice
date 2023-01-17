package org.example.websocket;

import javax.websocket.ClientEndpoint;
import javax.websocket.ContainerProvider;
import javax.websocket.DeploymentException;
import javax.websocket.OnMessage;
import javax.websocket.Session;
import java.io.IOException;
import java.net.URI;

@ClientEndpoint
public class Client {
    private Session session;
    public String data;

    public Client(URI uri) {
        try {
            session = ContainerProvider.getWebSocketContainer().connectToServer(this, uri);
        } catch (DeploymentException | IOException e) {
            e.printStackTrace();
        }
    }

    @OnMessage
    public void onMessage(String message) {
        data = message;
    }

    public void sendMessage(String str) {
        session.getAsyncRemote().sendText(str);
    }
}
