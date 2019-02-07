package com.axmor.util;

public class CommentValidator {
    public static boolean isStatusValid(String status){
        //TODO Check for empty string!!
        switch (status){
            case "created" :
            case "resolved" :
            case "closed" :
            case "duplicated" :
            case "reopened" : return true;
            default: return false;
        }
    }

    public static boolean isTextValid(String text){
        //TODO Check for empty string!!
        if (text.length() >= 3){
            return true;
        } else{
            return false;
        }
    }
}
