package com.axmor.util;

public class IssueValidator {
    public static boolean isTitleValid(String title){
        if (title.trim().length() >= 3){
            return true;
        } else{
            return false;
        }
    }

    public static boolean isDescriptionValid(String description){
        if (description.trim().length() >= 3){
            return true;
        } else{
            return false;
        }
    }
}