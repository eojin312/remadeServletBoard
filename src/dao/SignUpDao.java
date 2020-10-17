package dao;

import extraction.ConnectionMaker;

import java.sql.*;

public class SignUpDao {
    Connection conn;
    long memNo = 0;

    public SignUpDao() throws SQLException, ClassNotFoundException {
        ConnectionMaker connectionMaker = new ConnectionMaker();
        this.conn = connectionMaker.getConnecton();
    }

    public boolean signupInsert(String name, String id, String pwd, String gender, String email) throws SQLException {
        PreparedStatement pstmt = conn.prepareStatement("insert into member (`name`, id, pwd, gender, email) values (?, ?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
        pstmt.setString(1, name);
        pstmt.setString(2, id);
        pstmt.setString(3, pwd);
        pstmt.setString(4, gender);
        pstmt.setString(5, email);

        int rows = pstmt.executeUpdate();
        boolean result = false;
        if (rows == 1) {
            result = true;
        }
        ResultSet rsLastKey = pstmt.getGeneratedKeys();
        if (rsLastKey.next()) {
            memNo = rsLastKey.getLong(1);
            return result;
        }
       return result;
    }
}