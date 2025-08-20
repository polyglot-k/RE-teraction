package com.re_teraction.backend.global.util;

import lombok.experimental.UtilityClass;

@UtilityClass
public class PasswordEncoder {
    public static String encode(String plain){
        return plain;
    }
    public static boolean matches(String plain, String encoded) {
        return plain.equals(encoded);
    }
}
