package com.axmor.util;

public class CommentValidator {
    public static boolean isStatusValid(String status){
        if (StatusHelper.getIdByStatus(status) == -1){
            return false;
        } else {
            return true;
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