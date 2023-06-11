package com.empik.restservice.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import static com.empik.restservice.model.db.SQLConstans.INSERT_REQUEST_COUNT;
import static com.empik.restservice.model.db.SQLConstans.UPDATE_REQUEST_COUNT;

@Service
@Slf4j
public class SQLExecutionService {

    @Autowired
    JdbcTemplate jdbcTemplate;

    public void insertRequestCount(String login) {
        try {
            jdbcTemplate.execute(String.format(INSERT_REQUEST_COUNT, login));
        } catch (Exception e) {
            log.error("Something went wrong while inserting record to the database", e);
        }
    }

    public int updateRequestCount(String login) {
        try {
            return jdbcTemplate.update(String.format(UPDATE_REQUEST_COUNT, login));
        } catch (Exception e) {
            log.error("Something went wrong while updating record in the database", e);
            return 0;
        }
    }
}
