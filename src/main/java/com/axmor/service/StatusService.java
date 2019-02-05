package com.axmor.service;

import com.axmor.dao.StatusDAO;
import com.axmor.model.Status;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class StatusService {
    private static Logger log = LoggerFactory.getLogger(StatusService.class);
    private static StatusDAO statusDAO = new StatusDAO();

    public static List<Status> getAll(){
        return statusDAO.getAll();
    }

    public static Status get(int id){
        return statusDAO.get(id);
    }

    public static void create(Status status){
        statusDAO.create(status);
    }

    public static void update(Status status){
        statusDAO.update(status);
    }

    public static void delete(int id){
        statusDAO.delete(id);
    }

}