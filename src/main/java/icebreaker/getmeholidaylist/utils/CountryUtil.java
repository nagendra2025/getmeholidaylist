package icebreaker.getmeholidaylist.utils;

import java.util.Locale;

public final class CountryUtil {

    private CountryUtil() {
        // prevent instantiation
    }

    public static String countryNameFromCode(String code) {
        if (code == null || code.isBlank()) {
            return "";
        }
        return new Locale("", code.toUpperCase()).getDisplayCountry();
    }
}

