package com.example.testrestapi.ui.util;

public class Utils {
    public static String movieImagePathBuilder(String imagePath) {
        return "https://image.tmdb.org/t/p/" +
                "w500" +
                imagePath;
    }

}
