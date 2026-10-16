package com.urlshortner.urlshortner;

public class Base62 {
    private static final String CHARS =
            "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
    public static String encode(long n) {
        if (n == 0) return "0";
        StringBuilder sb = new StringBuilder();
        while (n > 0) {
            int remainder = (int) (n % 62);
            sb.append(CHARS.charAt(remainder));
            n = n / 62;
        }
        return sb.reverse().toString();
    }
}
