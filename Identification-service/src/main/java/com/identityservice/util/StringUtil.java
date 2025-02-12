package com.identityservice.util;

public class StringUtil {
    
    // Utility method to convert a string to Title Case
    public static String toTitleCase(String input) {
        if (input == null || input.isEmpty()) {
            return input;
        }
        String[] words = input.split("\\s+");
        StringBuilder titleCaseName = new StringBuilder();
        for (String word : words) {
            if (!word.isEmpty()) {
                titleCaseName.append(Character.toUpperCase(word.charAt(0)))
                             .append(word.substring(1).toLowerCase())
                             .append(" ");
            }
        }
        return titleCaseName.toString().trim();
    }
}
