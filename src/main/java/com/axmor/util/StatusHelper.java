package com.axmor.util;

import com.axmor.model.Status;

import java.util.*;

public class StatusHelper {
    private static List<String> statusList = new ArrayList<>();

    static {
        for (Status status :Status.values()){
            statusList.add(status.name());
        }
    }

    public static List<String> getStatusList(){
        return statusList;
    }

    public static Status getCreatedStatus(){
        return Status.valueOf("Created");
    }
}