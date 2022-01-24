package com.example.robwarehouse.controller;

import lombok.experimental.UtilityClass;

@UtilityClass
public class RedirectUtil {

    public static final String REDIRECT = "redirect";

    public static String redirect(String url) {
        return REDIRECT + ":/" + url;
    }

    public static String redirect(String urlSuffix, Object arg) {
        return REDIRECT + ":/" + urlSuffix + "/" + arg;
    }
}
