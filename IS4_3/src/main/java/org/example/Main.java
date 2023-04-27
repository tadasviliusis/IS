package org.example;

import java.math.BigInteger;

public class Main {
    public static void main(String[] args) {
        String receivedMessage = Client.receiveMessage();

        String[] values = receivedMessage.split("!NEWLINE!");
        String key = values[0];
        String text = values[1];
        String signature = values[2];
        System.out.println("KEY: " + key);
        System.out.println("TEXT" + text);
        System.out.println("SIGNATURE " + signature);

        String[] keys = key.split(" ");
        String decryptedMessage = RSA.decrypt(signature, new BigInteger(keys[0]), new BigInteger(keys[1]));

        if(decryptedMessage.equals(text))
            System.out.println("SIGNATURE IS VALID");
        else
            System.out.println("SIGNATURE IS NOT VALID");
    }
}