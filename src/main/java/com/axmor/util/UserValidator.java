package com.axmor.util;

public class UserValidator {
    public static boolean isNameValid(String name){
        //TODO length > 3
        if (name.length() > 0){
            return true;
        } else {
            return false;
        }
    }

    public static boolean isPasswordValid(String password){
        if (password.length() >= 0){
            return true;
        } else {
            return false;
        }
    }
}