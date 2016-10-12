package com.xx.jdbc;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;

import java.sql.*;
import java.util.Properties;

/**
 * Created by Administrator on 2016/10/12.
 */
public class Demo01 {

    private final static String DRIVE_CLASS_NAME = "com.mysql.jdbc.Driver";

    private final static String URL = "jdbc:mysql://127.0.0.1:3306/jdbc";
    private final static String URL2 = "jdbc:mysql://127.0.0.1:3306/jdbc?user=root&password=123456";
    private final static String USER = "root";
    private final static String PASSWORD = "123456";

    public static void main(String[] args) {

        MysqlDataSource dataSource = new MysqlDataSource();
        dataSource.setUrl(URL);
        dataSource.setUser(USER);
        dataSource.setPassword(PASSWORD);

        Properties dbProperties = new Properties();
        dbProperties.setProperty("user", USER);
        dbProperties.setProperty("password", PASSWORD);

        Connection connection = null;

        Statement statement = null;
        ResultSet rs = null;

        try {
//            1 加载MySQL驱动
            Class.forName(DRIVE_CLASS_NAME);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        try {

//            2 获取数据库连接
//            connection = DriverManager.getConnection(URL, dbProperties);
//            connection = DriverManager.getConnection(URL2);
            connection = dataSource.getConnection();
//            connection = DriverManager.getConnection(URL, USER, PASSWORD);

//            3 创建执行语句
            statement = connection.createStatement();

            String sql = "select * from t_user";

            /*
             * true     sql语句为select
             * false    sql为update insert delete
             */
            if (statement.execute(sql)) {
                // 如果是select查询语句，则获取查询结果集
                rs = statement.getResultSet();
                while (rs.next()) {
                    System.out.println(rs.getString("id") + " " + rs.getString("name") + " " + rs.getString("pwd"));
                }
            } else {
                // 如果是update insert delete则获取影响行数
                int count = statement.getUpdateCount();
            }

            // 执行查询
//            statement.executeQuery(sql);

            // 执行更新
//            statement.executeUpdate(sql);


        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("SQLException: " + e.getMessage());
            System.out.println("SQLState: " + e.getSQLState());
            System.out.println("VendorError: " + e.getErrorCode());
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                rs = null;
            }
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                statement = null;
            }
            if(connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                connection = null;
            }
        }


    }

}
