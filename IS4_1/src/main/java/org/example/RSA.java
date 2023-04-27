package org.example;

import java.math.BigInteger;
import java.util.Random;

public class RSA {

    static boolean isPrime(int num)
    {
        if(num <= 1)
        {
            return false;
        }
        for(int i = 2; i <= num / 2; i++)
        {
            if((num % i) == 0)
                return false;
        }
        return true;
    }

    public static int GCD(int a, int b, int[] s, int[] t)
    {
        if (a == 0)
        {
            s[0] = 0;
            t[0] = 1;
            return b;
        }

        int[] s1 = new int[1], t1 = new int[1];
        int gcd = GCD(b % a, a, s1, t1);

        s[0] = t1[0] - ((b / a) * s1[0]);
        t[0] = s1[0];

        return gcd;
    }

    public static int[] key_gen(int p, int q){

        int n = p * q;
        int phi = (p - 1) * (q - 1);

        Random random = new Random();
        int e = random.nextInt(phi);
        int[] x = new int[1], y = new int[1];

        while (e < phi) {
            if (GCD(e, phi, x, y) == 1)
                break;
            else if (e + 1 == phi)
                e = random.nextInt(phi);
            else
                e++;
        }
        int d = x[0];

        while (d <= 0 || d == e)
            d += phi;

        return new int[] {e, d, n};
    }


    public static String encrypt(String message, BigInteger e, BigInteger n){
        String encrypted = "";
        for (int i = 0; i < message.length(); i++)
        {
            BigInteger ei = new BigInteger(Integer.toString(message.charAt(i))).modPow(e, n);
            encrypted += ei + " ";
        }
        return encrypted.trim();
    }

}
