package com.empik.restservice.model.db;

public class SQLConstans {
    public static final String INSERT_REQUEST_COUNT = "" +
            "INSERT INTO login_requests (LOGIN, REQUEST_COUNT) " +
            "VALUES ('%s', 1)";

    public static final String UPDATE_REQUEST_COUNT = "" +
            "UPDATE login_requests " +
            "SET REQUEST_COUNT = REQUEST_COUNT + 1 " +
            "WHERE LOGIN = '%s'";
}
