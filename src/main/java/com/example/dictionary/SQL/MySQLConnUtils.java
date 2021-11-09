package com.example.dictionary.SQL;

import java.sql.*;

public class MySQLConnUtils {
    public static Connection getMySQlConnection() throws SQLException, ClassNotFoundException {
        final String hostName = "localhost";
        final String dbName = "edict";
        final String userName = "root";
        final String password = "haydenshimada1804"; //enter your password
        return getMySQlConnection(hostName, dbName, userName, password);
    }

    public static Connection getMySQlConnection(String hostName, String dbName, String userName, String password) throws SQLException, ClassNotFoundException {
        final String connectionURL = "jdbc:mysql://" + hostName + ":3306/" + dbName + "?autoReconnect=true&verifyServerCertificate=false&useSSL=true";
        Connection connect = DriverManager.getConnection(connectionURL, userName, password);
        return connect;
    }

}
