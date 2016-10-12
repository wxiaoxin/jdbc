package com.xx.jdbc;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by Administrator on 2016/10/12.
 */
public class Demo02 {

    public static void main(String[] args) {

        Connection conn = DBUtil.getConn();

        Statement statement = null;
        ResultSet rs = null;
        try {
            statement = conn.createStatement();
            String sql = "select * from t_user";
            rs = statement.executeQuery(sql);
            while (rs.next()) {
                System.out.println(rs.getString("id") + " " + rs.getString("name") + " " + rs.getString("pwd"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if(rs != null) {
                    System.err.println(conn.hashCode());
                    rs.close();
                }
                if(statement != null) {
                    statement.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }

}
