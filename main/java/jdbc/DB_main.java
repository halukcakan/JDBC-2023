package jdbc;

import javax.crypto.spec.DESedeKeySpec;
import java.sql.Connection;
import java.sql.SQLException;

public class DB_main {
    public static void main(String[] args) throws SQLException {

        // DB_work objesini olustur
        DB_work db = new DB_work();

        // connection fonksiyonunu cagir
        Connection connection = db.connection_to_database("DBtest","postgres","811436");

        // yeni table olusturma methodunu cagir
        db.create_table(connection,"employees");





    }
}
