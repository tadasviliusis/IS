package org.example;

import java.math.BigInteger;
import java.util.Random;

public class RSA {

    public static String decrypt(String message, BigInteger d, BigInteger n){
        String decrypted = "";
        String[] values = message.split(" ");
        for (int i = 0; i < values.length; i++)
        {
            BigInteger mi = new BigInteger(values[i]).modPow(d, n);
            decrypted += (char)mi.intValue();
        }
        return decrypted;
    }

}
