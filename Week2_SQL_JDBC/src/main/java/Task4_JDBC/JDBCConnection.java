package Task4_JDBC;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JDBCConnection {

    public static void main(String[] args) {

        String url =
                "jdbc:mysql://localhost:3306/week2_db";

        String username = "root";

        String password =
                "root";


        try {

            // Load MySQL Driver
            Class.forName(
                    "com.mysql.cj.jdbc.Driver"
            );


            // Create connection
            Connection connection =
                    DriverManager.getConnection(
                            url,
                            username,
                            password
                    );


            System.out.println(
                    "Database connected successfully!"
            );


            connection.close();


        } catch (ClassNotFoundException e) {

            System.out.println(
                    "MySQL JDBC Driver not found!"
            );

            e.printStackTrace();


        } catch (SQLException e) {

            System.out.println(
                    "Database connection failed!"
            );

            e.printStackTrace();
        }
    }
}