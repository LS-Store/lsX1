package com.ls.x1.database.providers;

import com.ls.x1.X1Plugin;
import com.ls.x1.database.Database;
import org.bukkit.Bukkit;

import java.sql.*;

public class MySQL implements Database {

    private Connection connection;

    private String table, host, user, pass, database;

    public MySQL(String table, String host, String user, String pass, String database) {
        this.table = table;
        this.host = host;
        this.user = user;
        this.pass = pass;
        this.database = database;
    }

    @Override
    public Connection getConnection() {
        return connection;
    }

    @Override
    public void open() {
        String url = "jdbc:mysql://" + host + "/" + database + "?autoReconnect=true";

        try {
            connection = DriverManager.getConnection(url, user, pass);
            createTable();
        } catch (SQLException ex) {
            ex.printStackTrace();
            Bukkit.getPluginManager().disablePlugin(X1Plugin.getPlugin());
        }
    }

    @Override
    public void close() {
        try {
            connection.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void createTable() {
        try {
            PreparedStatement stm = this.connection.prepareStatement(
                    table
            );
            stm.executeUpdate();
            stm.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}