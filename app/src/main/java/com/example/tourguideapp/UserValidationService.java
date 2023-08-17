package com.example.tourguideapp;


import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UserValidationService {

    public static boolean isValidName(String name){
        // Define a regular expression pattern for basic name validation
        String namePattern = "^[a-zA-Z'\\-\\s]+$";
        // Compile the pattern
        Pattern pattern = Pattern.compile(namePattern);
        // Match the name against the pattern
        Matcher matcher = pattern.matcher(name);
        return matcher.matches();

    }public static boolean isValidUserId(String name){
        // Define a regular expression pattern for basic name validation
        String namePattern = "^[a-zA-Z'\\-]+$";
        // Compile the pattern
        Pattern pattern = Pattern.compile(namePattern);
        // Match the name against the pattern
        Matcher matcher = pattern.matcher(name);
        return matcher.matches();

    }public static boolean isValidPassword(String password){
        boolean containsCapital = false;
        boolean containsNumber = false;
        for (char c : password.toCharArray()) {
            if (Character.isUpperCase(c)) { containsCapital = true;
            } else if (Character.isDigit(c)) { containsNumber = true;
            } // Break early if both conditions are met
            if (containsCapital && containsNumber) { break;
            }
        } return containsCapital && containsNumber;
    }
}