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
            String decrypted_msg = RSA.decrypt(encrypted_msg, e, n);
            System.out.println("DECRYPTED MESSAGE: " + decrypted_msg);

            int pq[] = RSA.PQfinder(n);
            System.out.println("P value: " + pq[0]);
            System.out.println("Q value: " + pq[1]);
        }
        else
            System.out.println("NOT PRIME");



    }
}

