package com.application.billing.Utils;

import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class CommonUtils {

    public String generateRandomString(int limit) {
        final char[] idchars = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789".toCharArray();
        char[] id = new char[limit];
        Random r = new Random(System.nanoTime());
        for (int i = 0; i < limit; i++) {
            id[i] = idchars[r.nextInt(idchars.length)];
        }
        return new String(id);
    }


}
