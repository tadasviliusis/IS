package org.example;

import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client {
    static String serverAddress = "127.0.0.1";
    static int port = 8080;

    public static void sendMessage(String sendMessage)
    {
        try (Socket socket = new Socket(serverAddress, port)) {
            System.out.println("Connected to the server");

            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);

            out.println(sendMessage);
            System.out.println("Sent: " + sendMessage);

        } catch (UnknownHostException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
