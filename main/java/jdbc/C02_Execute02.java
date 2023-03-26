package jdbc;

import java.sql.*;

public class C02_Execute02 {
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
        id'si 10'dan kucuk olan name'i cahir
        */

        String sql1 = "SELECT  name FROM country WHERE id<10";
        statement.execute(sql1);

        // record'lari gormek icin executeQuery methodu'nu kullaniriz
        ResultSet result1 = statement.executeQuery(sql1);

        while (result1.next()){

            System.out.println(result1.getString("name"));

        }

        /*
        EXAMPLE 2:
        id degerlerinin 5'ten kucuk oldugu
        "iso" ve "name" degerlerinin cagirin
         */

        String sql2 = "SELECT iso, name FROM country WHERE id<5";
        ResultSet result2 = statement.executeQuery(sql2);

        while (result2.next()){

            System.out.println(result2.getString("iso")+"-->"+result2.getString("name"));

        }

        /*
        EXAMPLE 3:
        "phonecode" u en buyuk olan satirin tum degerlerini cagirin
         */

        String sql3 ="SELECT * FROM country WHERE phonecode = (SELECT MAX(phonecode) FROM country)";
        ResultSet result3 = statement.executeQuery(sql3);

        while (result3.next()){

            System.out.println(result3.getInt(1)+"--"+result3.getString(2)+"--"+
                    result3.getString(3)+"--"+result3.getString(4)+"--"+
                    result3.getString(5)+"--"+result3.getInt(6)+"--"+
                    result3.getInt(7));

        }

        connection.close();
        statement.close();


    }
}
