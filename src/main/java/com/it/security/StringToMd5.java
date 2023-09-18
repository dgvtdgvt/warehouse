package com.it.security;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class StringToMd5 {

    public static String getMd5Str(String str){
        String keyStr = "";
        MessageDigest md5 = null;

        try {
            md5 = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }

        byte[] strByte = str.getBytes();

        byte key[] = md5.digest(strByte);

        for (byte b:key) {
            int num = b&0xff;
            String hexs = Integer.toHexString(num);
            if(hexs.length()<2){
                hexs = "0" + hexs;
            }
            keyStr += hexs;
        }
        return keyStr;
    }
}
