package extraction;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionMaker {
    public static final String DB_PWD = "dldjwls02";
    public static final String DB_ID = "root";
    public static final String DB_URL = "jdbc:mysql://localhost:3306/alphaleeremind?useSSL=false";

    public Connection getConnecton() throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        return DriverManager.getConnection(DB_URL, DB_ID, DB_PWD);
    }
}
