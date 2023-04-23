package com.restaurantorder.ordertest.MD5;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5 {
    public static String md5(String str) throws NoSuchAlgorithmException
    {
        MessageDigest md5 = MessageDigest.getInstance("MD5");
        byte[] srcBytes = str.getBytes(StandardCharsets.UTF_8);
        md5.update(srcBytes);
        byte[] resultBytes = md5.digest();
        return new String(resultBytes);
    }
}

