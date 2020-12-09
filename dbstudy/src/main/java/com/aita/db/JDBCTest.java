package com.aita.db;

import java.sql.*;

/**
 * Created by Aita on 18/2/3.
 */
public class JDBCTest {
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.jdbc.Driver");
        Connection connection = DriverManager.getConnection("jdbc:mysql://192.168.8.105:3306/book_manager", "root", "123456");
        PreparedStatement pstm = connection.prepareStatement("select * from book");

        Statement statement = connection.createStatement();
            String sql1 = String.format("insert into member(name, mail, passwd) values('zheng', 'zhenglina2011@163.com', '12345678')");
            String sql2 = String.format("insert into member(name, mail, passwd) values('lina', 'lina@163.com', '12345678')");
            String sql3 = String.format("insert into member(name, mail, passwd) values('Rita', 'aita0908@163.com', '12345678')");

        statement.executeUpdate(sql3);
        ResultSet result = pstm.executeQuery("select * from member");

        while (result.next()){
            System.out.println(result.getNString("name"));
        }

    }
}
