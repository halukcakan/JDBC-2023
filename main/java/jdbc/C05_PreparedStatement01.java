package jdbc;

import java.sql.*;

public class C05_PreparedStatement01 {
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
        prepared statement kullanarak
        "iso"su  "TC" olan 'turks adasi'
        "numcode"u 11111 olarak guncelleyin
         */

        //  prepared statement query olustur
        String sql1 ="UPDATE country  SET numcode = ? WHERE iso = ?";


        //  PreparedStatement objesini olusutur
        PreparedStatement pst1 =  connection.prepareStatement(sql1);


        //  set......() methodlari ile soru isaretleri icin deger gir
        pst1.setInt(1,11111);
        pst1.setString(2,"TC");


        //  query'i execute edelim
        pst1.executeUpdate();





        /*
        EXAMPLE 2:
        "SELECT * FROM <table name>"
        query'sinin prepared statement ile kullanin
         */


        read_data(connection,"country");



        connection.close();
        statement.close();



    }

    //  bir tablonun istenilen datasini prepared statement[gibi gibi] ile cagirmak icin kullanilan method
    public static void read_data (Connection connection, String tableName){

        try{
            String query =  String.format("SELECT * FROM %s",tableName);        //  format methodu dinamik bir String olusturmak icin

            Statement statement = connection.createStatement();

            //  SQL query'i calistir
            ResultSet result = statement.executeQuery(query);                   //  Data'yi cagirip resultSet conteynir'ina koyuyoruz

            while (result.next()){      //  tum datayi cagiralim

                System.out.println(result.getInt(1)+"--"+result.getString(2)+"--"+
                        result.getString(3)+"--"+result.getString(4)+"--"+
                        result.getString(5)+"--"+result.getInt(6)+"--"+
                        result.getInt(7));

            }

        }catch (Exception e ){
            System.out.println(e);
        }

    }

}
