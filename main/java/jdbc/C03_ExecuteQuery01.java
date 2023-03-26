package jdbc;

import java.sql.*;

public class C03_ExecuteQuery01 {
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
        en yuksek IKINCI "numcode" degeri olan
        "name" ve "iso"
        degerlerini cagirin
         */

        String sql1 = "SELECT name, iso " +                             //  OFFSET VE FETCH NEXT ILE
                "FROM country " +
                "ORDER BY numcode DESC " +
                "OFFSET 1 ROW " +
                "FETCH NEXT 1 ROW ONLY";

        String sql2 = "SELECT name, iso FROM country " +                //  SUBQUERY ILE
                "WHERE numcode = " +
                "(" +
                "SELECT MAX (numcode) FROM country " +
                "WHERE numcode < (SELECT MAX (numcode) FROM country) " +
                ")";


        ResultSet result1 = statement.executeQuery(sql1);

        while (result1.next()){

            System.out.println(result1.getString("iso")+"--"+result1.getString("name"));

        }

        connection.close();
        statement.close();
        result1.close();
    }
}
