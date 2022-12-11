package sample;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataBase {
    Connection conn;
    public Connection getConn() throws ClassNotFoundException, SQLException
    {String connectionUrl =
            "jdbc:sqlserver://127.0.0.1:1433;"
                    + "database=kyrs;"
                    + "user=kyrs;"
                    + "password=123;"
                    + "encrypt=false;";

        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        conn = DriverManager.getConnection(connectionUrl);
        return conn;
    }
}
