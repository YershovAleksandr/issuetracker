package com.axmor.util;

import com.axmor.model.Status;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CommentValidator {
    private static Logger log = LoggerFactory.getLogger(CommentValidator.class);

    public static boolean isStatusValid(String status){
        try{
            Status.valueOf(status);
        }catch (IllegalArgumentException e){
            log.error("Error Status value");

            return false;
        }

        return true;
    }

    public static boolean isTextValid(String text){
        if (text.trim().length() >= 3){
            return true;
        } else{
            return false;
        }
    }
}