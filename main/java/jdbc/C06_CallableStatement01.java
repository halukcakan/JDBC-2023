package jdbc;

import java.sql.*;

public class C06_CallableStatement01 {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {

        /*
        java'da methodlar return type sahibi olsa da, void olsa da method olarak adlandirilir

        SQL'de ise data return ediyorsa "function" denir
        return yapmiyorsa "procedure" olarak adlandirilir.
         */

        // Driver'a kaydol
        Class.forName("org.postgresql.Driver");

        // Database'e baglan
        Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/DBtest","postgres","811436");

        // Statement olustur
        Statement statement = connection.createStatement();

        // Quert calistir


        /*
        EXAMPLE 1:
        iki parametre ile calisip
        bu parametreleri toplaytran return yapan bir fonksiyon olustur
         */

        //  Fonksiyon kodunu yaz
        String sql1 = "CREATE OR REPLACE FUNCTION toplamafonk (x NUMERIC, y NUMERIC) \n" +
                        "RETURNS NUMERIC \n" +
                        "LANGUAGE plpgsql\n" +
                        "AS \n" +
                        "$$\n" +
                        "BEGIN\n" +
                        "\n" +
                        "RETURN x+y;\n" +
                        "\n" +
                        "END\n" +
                        "$$";


        //  Fonksiyonu calistir
        statement.execute(sql1);

        //  Fonksiyonu cagir
        CallableStatement cst1 = connection.prepareCall("{? = call toplamafonk(?,?)}");

        //  Return icin registerOutParameter() methodunu, parametreler icin set () metodlarindan uygun olanlari kullan
        cst1.registerOutParameter(1, Types.NUMERIC);
        cst1.setInt(2,15);
        cst1.setInt(3,23);

        //  calistirmak icin execute methodunu kullan       --      SQL'de calisti
        cst1.execute();

        //  sonucu cagirmak icin return data tipine gore "get" methodlarindan uygun olani kullan
        System.out.println(cst1.getBigDecimal(1));

//-------------------------------------------------------------------------------------------------------------------------

        /*
        EXAMPLE 2:
        Koninin hacmini hesaplayan bir function yazin
        V=(pi*R^(2)*3)
         */

        String sql2 = "CREATE OR REPLACE FUNCTION konihacmi (r NUMERIC, h NUMERIC) \n" +
                "RETURNS NUMERIC \n" +
                "LANGUAGE plpgsql\n" +
                "AS \n" +
                "$$\n" +
                "BEGIN\n" +
                "\n" +
                "RETURN 3.14 * r * r * h / 3;\n" +
                "\n" +
                "END\n" +
                "$$";


        //  Fonksiyonu calistir
        statement.execute(sql2);

        //  Fonksiyonu cagir
        CallableStatement cst2 = connection.prepareCall("{? = call konihacmi(?,?)}");

        //  Return icin registerOutParameter() methodunu, parametreler icin set () metodlarindan uygun olanlari kullan
        cst2.registerOutParameter(1, Types.NUMERIC);
        cst2.setInt(2,3);
        cst2.setInt(3,5);

        //  calistirmak icin execute methodunu kullan       --      SQL'de calisti
        cst2.execute();

        //  sonucu cagirmak icin return data tipine gore "get" methodlarindan uygun olani kullan
        System.out.println(cst2.getBigDecimal(1));


        connection.close();
        statement.close();


    }
}
