package com.axmor.dao;

import com.axmor.model.Status;
import com.axmor.util.DataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StatusDAO {
    private Logger log = LoggerFactory.getLogger(StatusDAO.class);

    public List<Status> getAll(){
        List<Status> statusList = new ArrayList<>();

        try(Connection con = DataSource.getConnection()){
            PreparedStatement ps = con.prepareStatement("SELECT * FROM status");
            ResultSet rs = ps.executeQuery();

            while(rs.next()) {
                Status status = new Status();
                status.setId(rs.getInt("status_id"));
                status.setStatus(rs.getString("status_status"));

                statusList.add(status);
            }
        }catch(SQLException e){
            log.error("Error", e);
        }

        return statusList;
    }

    public Status get(String status){
        Status st = null;

        try(Connection con = DataSource.getConnection()){
            PreparedStatement ps = con.prepareStatement("SELECT * FROM status WHERE status_status = ?");
            ps.setString(1, status);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                st = new Status();
                st.setId(rs.getInt("status_id"));
                st.setStatus(rs.getString("status_status"));
            }
        }catch(SQLException e){
            log.error("Error", e);
        }

        return st;
    }

    public int create(Status status){
        int rez = 0;

        try(Connection con = DataSource.getConnection()){
            PreparedStatement ps = con.prepareStatement("INSERT INTO status(status_status) values(?)");

            ps.setString(1, status.getStatus());

            rez = ps.executeUpdate();
        }catch(SQLException e){
            log.error("Error", e);
        }

        log.info("Created status " + status);

        return rez;
    }
}