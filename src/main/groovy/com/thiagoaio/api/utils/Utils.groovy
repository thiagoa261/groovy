package com.thiagoaio.api.utils

import at.favre.lib.crypto.bcrypt.BCrypt

class Utils {

    public static String hashString(String password) {
        return BCrypt.withDefaults().hashToString(12, password.toCharArray());
    }

    public static boolean compareHash(String password, String hash) {
        return BCrypt.verifyer().verify(password.toCharArray(), hash).verified;
    }
}
