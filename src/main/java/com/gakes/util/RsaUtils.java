package com.gakes.util;

import android.content.Context;
import android.util.Base64;
import android.util.Log;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.security.Key;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.RSAPublicKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.crypto.Cipher;

public class RsaUtils {
    public static final String PUBLIC_KEY = "public_key";
    public static final String PRIVATE_KEY = "private_key";

    public static Map<String, Key> geration() {
        KeyPairGenerator keyPairGenerator;
        try {
            Map<String, Key> map = new HashMap<>();
            keyPairGenerator = KeyPairGenerator.getInstance("RSA");
            SecureRandom secureRandom = new SecureRandom(new Date().toString().getBytes());
            keyPairGenerator.initialize(1024, secureRandom);
            KeyPair keyPair = keyPairGenerator.genKeyPair();

            byte[] publicKeyBytes = keyPair.getPublic().getEncoded();
            X509EncodedKeySpec xks = new X509EncodedKeySpec(publicKeyBytes);
            KeyFactory kf = KeyFactory.getInstance("RSA");
            PublicKey publicKey = kf.generatePublic(xks);
            map.put(PUBLIC_KEY, publicKey);

            byte[] privateKeyBytes = keyPair.getPrivate().getEncoded();
            PKCS8EncodedKeySpec pks = new PKCS8EncodedKeySpec(privateKeyBytes);
            PrivateKey privateKey = kf.generatePrivate(pks);
            map.put(PRIVATE_KEY, privateKey);
            return map;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String decode(Key privKey, String content) {
        //开始解密
        try {
            Cipher cipher = Cipher.getInstance("RSA");
            cipher.init(Cipher.DECRYPT_MODE, privKey);
            byte[] cipherText = cipher.doFinal(content.getBytes());
            byte[] plainText = cipher.doFinal(cipherText);
            return new String(plainText);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void test() {
        Map<String, Key> keyMap = geration();
        try {
            Cipher cipher = Cipher.getInstance("RSA");
            Key pubKey = keyMap.get(PUBLIC_KEY);
            Key privKey = keyMap.get(PRIVATE_KEY);

            String input = "!!!hello world!!!";
            Log.d("cipher: ", "原始:" + input);
            cipher.init(Cipher.ENCRYPT_MODE, pubKey);
            byte[] cipherText = cipher.doFinal(input.getBytes());
            //加密后的东西
            Log.d("cipher: ", "加密后:" + new String(cipherText));
            //开始解密
            cipher.init(Cipher.DECRYPT_MODE, privKey);
            byte[] plainText = cipher.doFinal(cipherText);
            Log.d("cipher: ", "解密后:" + new String(plainText));
        } catch (Exception e1) {
            e1.printStackTrace();
        }
    }



















    /**
     *  使用私钥解密
     * @param content
     * @param private_key
     * @param input_charset
     * @return
     * @throws Exception
     */
    public static String decrypt(String content, String private_key, String input_charset) throws Exception {
        PrivateKey prikey = getPrivateKey(private_key);

        Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
        cipher.init(Cipher.DECRYPT_MODE, prikey);

        InputStream ins = new ByteArrayInputStream(Base64Utils.decode(content));
        ByteArrayOutputStream writer = new ByteArrayOutputStream();
        byte[] buf = new byte[128];
        int bufl;

        while ((bufl = ins.read(buf)) != -1) {
            byte[] block = null;

            if (buf.length == bufl) {
                block = buf;
            } else {
                block = new byte[bufl];
                for (int i = 0; i < bufl; i++) {
                    block[i] = buf[i];
                }
            }

            writer.write(cipher.doFinal(block));
        }

        return new String(writer.toByteArray(), input_charset);
    }

    /**
     * 获得私钥
     *
     * @param key
     *            私钥
     * @throws Exception
     */
    public static PrivateKey getPrivateKey(String key) throws Exception {

        byte[] keyBytes;

        keyBytes = Base64Utils.decode(key);

        PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(keyBytes);

        KeyFactory keyFactory = KeyFactory.getInstance("RSA");

        PrivateKey privateKey = keyFactory.generatePrivate(keySpec);

        return privateKey;
    }

    /**
     * 得到公钥
     *
     * @param bysKey
     * @return
     */
    private static PublicKey getPublicKeyFromX509(String bysKey) throws  Exception {
        byte[] decodedKey = Base64Utils.decode(bysKey);
        X509EncodedKeySpec x509 = new X509EncodedKeySpec(decodedKey);

        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        return keyFactory.generatePublic(x509);
    }

    /**
     * 使用公钥加密
     *
     * @param content 密文
     * @param pub_key 公钥
     * @return
     */
    public static String encryptByPublic(String content, String pub_key) {
        try {
            PublicKey pubkey = getPublicKeyFromX509(pub_key);

            Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
            cipher.init(Cipher.ENCRYPT_MODE, pubkey);

            byte plaintext[] = content.getBytes("UTF-8");
            byte[] output = cipher.doFinal(plaintext);


            return Base64Utils.encode(output);

        } catch (Exception e) {
            Log.i("logs","e====="+e.getMessage());
            return null;
        }
    }






}
