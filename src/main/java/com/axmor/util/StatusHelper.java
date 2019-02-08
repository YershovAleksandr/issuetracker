package com.axmor.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class StatusHelper {
    private static List<String> statusStringList = new ArrayList<>(Arrays.asList("Created", "Resolved", "Closed", "Duplicated", "Reopened"));

    public static List<String> getStatusList(){
        return statusStringList;
    }
}