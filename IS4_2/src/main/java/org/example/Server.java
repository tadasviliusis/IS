package org.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    static int port = 8080;

    public static String receiveMessage() {
        try (ServerSocket serverSocket = new ServerSocket(port)) {
        String receivedMessage = "";
        System.out.println("Server is listening on port " + port);
        while (true) {
            try (Socket socket = serverSocket.accept()) {
                System.out.println("Client connected");

                BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
               // PrintWriter out = new PrintWriter(socket.getOutputStream(), true);

                receivedMessage = in.readLine();
            }
            return receivedMessage;
        }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void sendMessage(String sendMessage) {
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("Server is listening on port " + port);

            while (true) {
                try (Socket socket = serverSocket.accept()) {
                    System.out.println("Client connected");

                    PrintWriter out = new PrintWriter(socket.getOutputStream(), true);

                    out.println(sendMessage);
                }
                finally {
                    serverSocket.close();
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

}
