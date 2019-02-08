package com.axmor.util;

import java.util.*;

public class StatusHelper {
    private static List<String> statusList = new ArrayList<>(Arrays.asList("Created", "Resolved", "Closed", "Duplicated", "Reopened"));

    public static List<String> getStatusList(){
        return statusList;
    }

    public static int getIdByStatus(String status){
        return statusList.indexOf(status);
    }

    public static String getCreatedStatus(){
        return "Created";
    }
}