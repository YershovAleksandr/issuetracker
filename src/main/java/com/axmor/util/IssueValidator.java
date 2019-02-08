package com.axmor.util;

public class IssueValidator {
    public static boolean isTitleValid(String title){
        //TODO Check for empty string!!
        if (title.length() >= 3){
            return true;
        } else{
            return false;
        }
    }

    public static boolean isDescriptionValid(String description){
        //TODO Check for empty string!!
        if (description.length() >= 3){
            return true;
        } else{
            return false;
        }
    }
}