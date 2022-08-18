package com.ls.x1.database.providers;

import com.ls.x1.X1Plugin;
import com.ls.x1.database.Database;
import org.bukkit.Bukkit;

import java.io.File;
import java.sql.*;

public class SQLite implements Database {

    private Connection connection;
    private String table;

    public SQLite(String table) {
        this.table = table;
    }

    @Override
    public Connection getConnection() {
        return connection;
    }

    @Override
    public void open() {
        File file = new File(X1Plugin.getPlugin().getDataFolder(), "database.db");
        String url = "jdbc:sqlite:" + file;

        try {
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection(url);
            createTable();
        } catch (Exception ex) {
            ex.printStackTrace();
            Bukkit.getPluginManager().disablePlugin(X1Plugin.getPlugin());
        }
    }

    @Override
    public void close() {
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
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