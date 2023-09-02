package league.common.appaws.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnect {
    public static Connection connect(){
        Connection connection = null;
//        try {
//            Class.forName("org.sqlite.JDBC");
//        } catch (ClassNotFoundException e) {
//            throw new RuntimeException(e);
//        }
        try {
            connection = DriverManager.getConnection("jdbc:sqlite:src/main/resources/buckets");
            return connection;
        } catch (SQLException e) {
            System.out.println("Нет соединения");

            System.out.println(e.getMessage());
        }
        return null;
    }
}
