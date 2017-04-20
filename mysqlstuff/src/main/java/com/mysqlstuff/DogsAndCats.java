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
            
            System.out.println("");
            System.out.println("SELECT");
            resultSet = statement.executeQuery("SELECT * FROM cats");
            writeResultSet(resultSet);
            
            System.out.println("");
            System.out.println("SELECT");
            resultSet = statement.executeQuery("SELECT * FROM dogs");
            writeResultSet(resultSet);
            
            System.out.println("");
            System.out.println("RIGHT OUTER JOIN:");
            resultSet = statement.executeQuery("SELECT * from dogs RIGHT OUTER JOIN cats on dogs.owner = cats.owner");
            writeResultSet(resultSet);
            
            System.out.println("");
            System.out.println("LEFT OUTER JOIN:");
            resultSet = statement.executeQuery("SELECT * from dogs LEFT OUTER JOIN cats on dogs.owner = cats.owner");
            writeResultSet(resultSet);
            
            System.out.println("");
            System.out.println("UNION:");
            resultSet = statement.executeQuery("select * from dogs LEFT OUTER JOIN cats on dogs.owner = cats.owner UNION select * from dogs RIGHT OUTER JOIN cats on dogs.owner = cats.owner;");
            writeResultSet(resultSet);
            
            System.out.println("");
            System.out.println("UNION ALL:");
            resultSet = statement.executeQuery("select * from dogs LEFT OUTER JOIN cats on dogs.owner = cats.owner UNION ALL select * from dogs RIGHT OUTER JOIN cats on dogs.owner = cats.owner;");
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
            System.out.println("");
            System.out.println("Name: " + name);
            System.out.println("Gender: " + gender);
            System.out.println("Owner: " + owner);
            System.out.println("Birth: " + birth);
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
