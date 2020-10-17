package dao;

import extraction.ConnectionMaker;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class AlphaLeeDao {
    Connection conn;

    public AlphaLeeDao() throws SQLException, ClassNotFoundException {
        ConnectionMaker connectionMaker = new ConnectionMaker();
        this.conn = connectionMaker.getConnecton();
    }
}
