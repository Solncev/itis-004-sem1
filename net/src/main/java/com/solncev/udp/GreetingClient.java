package com.solncev.udp;

import java.io.IOException;
import java.net.*;

public class GreetingClient {

    private final DatagramSocket socket;
    private byte[] buffer;
    private final InetAddress address;

    public GreetingClient() throws SocketException, UnknownHostException {
        socket = new DatagramSocket();
        address = InetAddress.getByName("localhost");
    }

    public String sendMessage(String message) throws IOException {
        buffer = message.getBytes();
        DatagramPacket packet = new DatagramPacket(buffer, buffer.length, address, GreetingServer.PORT);
        socket.send(packet);

        packet = new DatagramPacket(buffer, buffer.length);
        socket.receive(packet);
        String response = new String(packet.getData(), 0, packet.getLength());

        return response;
    }

    public void stopClient() {
        socket.close();
    }
}
