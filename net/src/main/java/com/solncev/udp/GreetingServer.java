package com.solncev.udp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

public class GreetingServer extends Thread {

    public static final int PORT = 5556;
    private final DatagramSocket socket;
    private final byte[] buffer = new byte[512];
    private boolean alive;

    public GreetingServer() throws SocketException {
        socket = new DatagramSocket(PORT);
    }

    public void run() {
        alive = true;
        while (alive) {
            try {
                DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
                socket.receive(packet);
                String message = new String(packet.getData(), 0, packet.getLength());
                InetAddress address = packet.getAddress();
                int port = packet.getPort();

                // some logic with message

                byte [] data = message.getBytes();
                packet = new DatagramPacket(data, data.length, address, port);
                socket.send(packet);

                if ("bye".equals(message.toLowerCase())) {
                    alive = false;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        socket.close();
    }
}
