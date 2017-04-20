package com.mysqlstuff;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DogsAndCats {
    private Connection connect = null;
    private Statement statement = null;
    private ResultSet resultSet = null;

    public void readDataBasePets() throws Exception {
        try {

            Class.forName("com.mysql.cj.jdbc.Driver");

            connect = DriverManager
                .getConnection("jdbc:mysql://localhost/pets?user=sqluser&password=sqluserpw&useSSL=false&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=PST");
            statement = connect.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM cats");
            writeResultSet(resultSet);
            
            resultSet = statement.executeQuery("SELECT * from dogs RIGHT OUTER JOIN cats on dogs.owner = cats.owner");
            writeResultSet(resultSet);

        } catch (Exception e) {
            throw e;
        } finally {
            close();
        }

    }

    private void writeResultSet(ResultSet resultSet) throws SQLException {

        while (resultSet.next()) {
            String name = resultSet.getString("name");
            String gender = resultSet.getString("gender");
            String owner = resultSet.getString("owner");
            String birth = resultSet.getString("birth");
            System.out.println("name: " + name);
            System.out.println("gender: " + gender);
            System.out.println("owner: " + owner);
            System.out.println("birth: " + birth);
        }
    }

    private void close() {
        try {
            if (resultSet != null) {
                resultSet.close();
            }

            if (statement != null) {
                statement.close();
            }

            if (connect != null) {
                connect.close();
            }
        } catch (Exception e) {

        }
    }
}
