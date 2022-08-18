package com.ls.x1.database;

import java.sql.Connection;

public interface Database {

    void createTable();
    void open();
    void close();

    Connection getConnection();
}
