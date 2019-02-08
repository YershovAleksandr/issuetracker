package com.axmor.service;

import com.axmor.dao.StatusDAO;
import com.axmor.model.Status;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class StatusService {
    private static Logger log = LoggerFactory.getLogger(StatusService.class);
    private static StatusDAO statusDAO = new StatusDAO();

    /*public static List<Status> getAllStatus(){
        return statusDAO.getAll();
    }*/

    public static void create(Status status){
        statusDAO.create(status);
    }

    public static Status getStatusByStatus(String status){
        return statusDAO.get(status);
    }
}