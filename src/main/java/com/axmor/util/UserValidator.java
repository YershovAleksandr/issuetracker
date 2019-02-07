package com.axmor.util;

public class UserValidator {
    public static boolean isNameValid(String name){
        if (name.length() > 2){
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
        //return true;
    }
}
