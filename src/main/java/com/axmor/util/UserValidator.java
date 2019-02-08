package com.axmor.util;

public class UserValidator {
    public static boolean isNameValid(String name){
        if (name.trim().length() >= 3){
            return true;
        } else {
            return false;
        }
    }

    public static boolean isPasswordValid(String password){
        if (password.trim().length() >= 3){
            return true;
        } else {
            return false;
        }
    }
}