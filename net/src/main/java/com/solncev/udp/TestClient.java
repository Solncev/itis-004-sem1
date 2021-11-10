package com.solncev.udp;

import java.io.IOException;

public class TestClient {
    public static void main(String[] args) throws IOException {

        new GreetingServer().start();
        GreetingClient client = new GreetingClient();

        System.out.println(client.sendMessage("hello"));

        System.out.println(client.sendMessage("bye"));

        client.stopClient();
    }
}
