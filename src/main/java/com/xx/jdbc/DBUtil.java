package com.xx.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by Administrator on 2016/10/12.
 */
public class DBUtil {

    public static Connection conn;

    private final static String DRIVE_CLASS_NAME = "com.mysql.jdbc.Driver";

    private final static String URL = "jdbc:mysql://127.0.0.1:3306/jdbc";
    private final static String URL2 = "jdbc:mysql://127.0.0.1:3306/jdbc?user=root&password=123456";
    private final static String USER = "root";
    private final static String PASSWORD = "123456";

    /**
     * 获取数据库连接
     */
    public static Connection getConn() {
        if (conn == null) {
            try {
                Class.forName(DRIVE_CLASS_NAME);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }

            try {
                conn = DriverManager.getConnection(URL, USER, PASSWORD);
                System.err.println(conn.hashCode());
            } catch (SQLException e) {
                e.printStackTrace();
                return null;
            }
        }
        return conn;
    }

    /**
     * 关闭数据库连接
     */
    public static void close() {
        if (conn != null) {
            try {
                System.err.println(conn.hashCode());
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        conn = null;
    }

}
