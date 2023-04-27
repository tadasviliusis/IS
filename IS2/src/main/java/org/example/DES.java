package org.example;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

public class DES {

    private static final String ALGORITHM = "DES";
    private static final String ECB_MODE = "ECB";
    private static final String CBC_MODE = "CBC";
    private static final String CFB_MODE = "CFB";

    public static String encrypt(String message, String key, String mode, String iv) throws Exception {
        Cipher cipher = Cipher.getInstance(ALGORITHM + "/" + mode + "/PKCS5Padding");
        SecretKeySpec secretKey = new SecretKeySpec(key.getBytes(StandardCharsets.UTF_8), ALGORITHM);
        IvParameterSpec ivParameterSpec = new IvParameterSpec(iv.getBytes(StandardCharsets.UTF_8));
        if (mode == ECB_MODE)
            cipher.init(Cipher.ENCRYPT_MODE, secretKey);

        else

            cipher.init(Cipher.ENCRYPT_MODE, secretKey, ivParameterSpec);
        byte[] encryptedBytes = cipher.doFinal(message.getBytes(StandardCharsets.UTF_8));

        return Base64.getEncoder().encodeToString(encryptedBytes);
    }

    public static String decrypt(String message, String key, String mode, String iv) throws Exception {
        Cipher cipher = Cipher.getInstance(ALGORITHM + "/" + mode + "/PKCS5Padding");
        SecretKeySpec secretKey = new SecretKeySpec(key.getBytes(StandardCharsets.UTF_8), ALGORITHM);
        IvParameterSpec ivParameterSpec = new IvParameterSpec(iv.getBytes(StandardCharsets.UTF_8));
        if (mode == ECB_MODE)
            cipher.init(Cipher.DECRYPT_MODE, secretKey);

        else

        cipher.init(Cipher.DECRYPT_MODE, secretKey, ivParameterSpec);
        byte[] decryptedBytes = cipher.doFinal(Base64.getDecoder().decode(message));

        return new String(decryptedBytes, StandardCharsets.UTF_8);
    }

}
