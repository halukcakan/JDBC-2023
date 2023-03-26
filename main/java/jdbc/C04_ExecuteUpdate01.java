package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class C04_ExecuteUpdate01 {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {

        // Driver'a kaydol
        Class.forName("org.postgresql.Driver");

        // Database'e baglan
        Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/DBtest","postgres","811436");

        // Statement olustur
        Statement statement = connection.createStatement();

        // Quert calistir

        /*
        EXAMPLE 1:
        numcode degeri ortalama ulke sayisindan az olan
        numcode degerlerini
        10 olarak update edin
         */

        String sql1 ="UPDATE country " +
                "SET numcode = 100 " +
                "WHERE numcode <(SELECT AVG (numcode) " +
                "FROM country)";

        statement.executeUpdate(sql1);

        connection.close();
        statement.close();


    }
}
