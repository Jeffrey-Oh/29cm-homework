package kr.co._29cm.homework.common.util;

import org.apache.commons.lang3.RandomStringUtils;

public class TokenGenerator {

    public static String randomCharacter(int length) {
        return RandomStringUtils.randomAlphanumeric(length);
    }

}