package com.axmor.util;

import java.util.*;

public class StatusHelper {
    private static List<String> statusList = new ArrayList<>(Arrays.asList("Created", "Resolved", "Closed", "Duplicated", "Reopened"));

    public static List<String> getStatusList(){
        return statusList;
    }

    public static String getStatusById(int id){
        String ret = null;

        try {
            ret = statusList.get(id);
        }catch (IndexOutOfBoundsException e){
            e.printStackTrace();
        }

        return ret;
    }

    public static int getIdByStatus(String status){
        return statusList.indexOf(status);
    }

    public static String getCreatedStatus(){
        return "Created";
    }
}