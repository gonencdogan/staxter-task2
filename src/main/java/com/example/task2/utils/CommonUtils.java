package com.example.task2.utils;

import org.apache.commons.codec.digest.DigestUtils;

/**
 * this is a utils class
 */
public class CommonUtils {

    /**
     * @param password
     * @return password will be encrypted here
     */
    public static String encrypte(String password) {
        return DigestUtils.sha256Hex(password);
    }

}
