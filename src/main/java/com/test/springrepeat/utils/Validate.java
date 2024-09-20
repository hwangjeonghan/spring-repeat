package com.test.springrepeat.utils;

public class Validate {

    // 정규 표현식
    private static final String NAME_REGEX = "^[가-힣]{3}$";
    private static final String POST_REGEX = "\\d{5}";

    // 문자열 검증
    public static boolean isValidString(String value) {
        return value != null && !value.isBlank();
    }

    // 정수 검증
    public static boolean isValidInt(Object value) {
        if (value instanceof Integer || value instanceof Long) {
            return value != null && ((Number) value).intValue() > 0;
        }
        return false;
    }

    // 이름 검증 (정규 표현식 사용)
    public static boolean isValidName(String userName) {
        return userName != null && userName.matches(NAME_REGEX);
    }

    // 나이 검증
    public static boolean isValidAge(Integer userAge) {
        return userAge != null && userAge >= 20;
    }

    // 주소 우편번호 검증 (정규 표현식 사용)
    public static boolean isValidAddressPost(String addressPost) {
        return addressPost != null && addressPost.matches(POST_REGEX);
    }
}
