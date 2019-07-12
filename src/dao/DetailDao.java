package dao;

import extraction.ConnectionMaker;
import servlet.model.Article;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DetailDao {
    Connection conn;
    PreparedStatement pstmt;
    long atNo;


    public DetailDao() throws SQLException, ClassNotFoundException {
        ConnectionMaker connectionMaker = new ConnectionMaker();

        this.conn = connectionMaker.getConnecton();
    }

    public String selectDetail() throws SQLException {
        pstmt = conn.prepareStatement("select at_no, title, writer, contents, at_tag, create_time from article where at_no = ?");
        pstmt.setLong(1, atNo);
        ResultSet rs = pstmt.executeQuery();
        if (rs.next()) {
            String title = rs.getString("title");
            String writer = rs.getString("writer");
            long _atNo = rs.getLong("at_no");
            String createTime = rs.getString("create_time");
            String atTag = rs.getString("at_tag");
            String contents = rs.getString("contents");

            Article article = new Article(title, writer, _atNo, createTime, atTag, contents);
        }
        return null;
    }
}
