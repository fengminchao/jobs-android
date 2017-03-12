package com.muxistudio.jobs.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by ybao on 16/11/13.
 */

public class MD5Util {

    public static String getMD5(String val) throws NoSuchAlgorithmException {
        MessageDigest md5 = MessageDigest.getInstance("MD5");
        md5.update(val.getBytes());
        byte[] m = md5.digest();
        return getString(m);
    }

    private static String getString(byte[] b) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < b.length; i++) {
            sb.append(b[i]);
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        String s = "123";
        try {
            System.out.println(getMD5(s));
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }
}
