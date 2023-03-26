package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class DB_work {

    //  Postgresql baglantisi methodu

    public Connection connection_to_database (String dbname, String user, String password){

        Connection connection = null;

        try {
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/" + dbname,user,password);

            if (connection != null){
                System.out.println("Database'e baglanti saglandi");
            } else {
                System.out.println("Database'e baglanti saglanamadi");
            }

        } catch (Exception e) {
            System.out.println(e);
        }


        return connection;
    }



    // Yeni tablo olusturma methodu
    public void create_table (Connection connection, String tableName){            // bize return yapmayacak sadece Postgresql'de islem yapacak o yuzden VOID

        //statement objesi olustur
        Statement statement;

        try {

            String query = "CREATE TABLE "+tableName+" (empID SERIAL, name VARCHAR(50), email VARCHAR(50), salary INTEGER, PRIMARY KEY (empID))";
            statement = connection.createStatement();

            statement.executeUpdate(query);
            System.out.println("Table olusturuldu");


        }catch (Exception e){
            System.out.println(e);
        }

    }


}
