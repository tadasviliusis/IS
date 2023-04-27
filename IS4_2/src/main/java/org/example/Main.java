package org.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        String message = Server.receiveMessage();
        String[] values = message.split("!NEWLINE!");
        String key = values[0];
        String text = values[1];
        String signature = values[2];

        System.out.println("KEY: " + key);
        System.out.println("TEXT" + text);
        System.out.println("SIGNATURE " + signature);

        String sendMessage = key + "!NEWLINE!" +
                text + "!NEWLINE!" + signature;
        Server.sendMessage(sendMessage);

    }
}