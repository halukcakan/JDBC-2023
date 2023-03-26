package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class C01_Execute01 {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {

        // Driver'a kaydol
        Class.forName("org.postgresql.Driver");

        // Database'e baglan
        Connection connection =DriverManager.getConnection("jdbc:postgresql://localhost:5432/DBtest","postgres","811436");

        // Statement olustur
        Statement statement = connection.createStatement();

        // Quert calistir


        /*
        EXAMPLE 1:
        "workers" adinda bir table olusturup
        "worker_id, worker_name, worker_salary"
        sutunlari ekleyin
        */

//        String sql1 = "CREATE TABLE workers(worker_id VARCHAR(50), worker_name VARCHAR(50), worker_salary INT)";
//        statement.execute(sql1);



        /*
        EXAMPLE 2:
        table'a worker_address sutunu ekleyerek alter yapin
        */

//        String sql2 = "AlTER TABLE workers ADD worker_address VARCHAR(80)";         // alter: degisiklik yapacam demek
//                                                                                    // add  : kolon ekle
//        statement.execute(sql2);




        /*
        EXAMPLE 3: drop worker tablosunu
         */
//        String sql3 = "DROP TABLE workers";
//        statement.execute(sql3);




        // baglanti ve statement'i kapat
//        connection.close();
//        statement.close();


    }
}
