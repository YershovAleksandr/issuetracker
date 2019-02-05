package com.axmor.dao;

import com.axmor.model.Status;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

public class StatusDAO {
    private Logger log = LoggerFactory.getLogger(StatusDAO.class);

    private List<Status> statusList = new ArrayList<>();

    public List<Status> getAll(){
        return statusList;
    }

    public Status get(int id){
        return statusList.get(id);
    }

    public void create(Status status){
        statusList.add(status);
        status.setId(statusList.indexOf(status));

        log.info("Create status " + status);
    }

    public void update(Status status){
        statusList.add(status);
    }

    public void delete(int id){
        statusList.remove(id);
    }
}

