package org.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client {
    static String serverAddress = "127.0.0.1";
    static int port = 8080;

    public static String receiveMessage()
    {
        try (Socket socket = new Socket(serverAddress, port)) {
            System.out.println("Connected to the server");

            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            String receivedMessage = in.readLine();
            System.out.println("Received: " + receivedMessage);
            return receivedMessage;
        } catch (UnknownHostException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
