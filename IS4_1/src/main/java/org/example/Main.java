package org.example;

import java.math.BigInteger;

public class Main {
    public static void main(String[] args) {
        int p = 107;
        int q = 101;


        if(RSA.isPrime(p) && RSA.isPrime(q)) {
            int keys[] = RSA.key_gen(p, q);

            BigInteger e = new BigInteger(Integer.toString(keys[0]));
            BigInteger d = new BigInteger(Integer.toString(keys[1]));
            BigInteger n = new BigInteger(Integer.toString(keys[2]));

            System.out.println("PUBLIC KEY: " + keys[0] + ", " + keys[2]);
            System.out.println("PRIVATE KEY: " + keys[1] + ", " + keys[2]);

            String message = "Hello!";
            System.out.println("ORIGINAL MESSAGE: " + message);
            String encrypted_msg = RSA.encrypt(message, d, n);
            System.out.println("ENCRYPTED MESSAGE: " + encrypted_msg);

            String sendMessage = keys[0] + " " + keys[2] + "!NEWLINE!" +
                    message + "!NEWLINE!" + encrypted_msg;
            Client.sendMessage(sendMessage);

        }
        else
            System.out.println("NOT PRIME");
    }
}