package dao;

import extraction.ConnectionMaker;
import servlet.model.Article;

import javax.servlet.http.HttpServletRequest;
import java.sql.*;

public class WriteDao {
    Connection conn;
    PreparedStatement pstmt;
    HttpServletRequest req;


    public WriteDao() throws SQLException, ClassNotFoundException {
        ConnectionMaker connectionMaker = new ConnectionMaker();

        this.conn = connectionMaker.getConnecton();
    }


    public boolean writeInsert(String writer, String title, String atTag, String contents) throws SQLException {

        pstmt = conn.prepareStatement("insert into article (writer, title, at_tag, contents, create_time) value (?, ?, ?, ?, now());",  Statement.RETURN_GENERATED_KEYS);
        pstmt.setString(1, writer);
        pstmt.setString(2, title);
        pstmt.setString(3, atTag);
        pstmt.setString(4, contents);

        int insertedRows = pstmt.executeUpdate();
        boolean result = false;
        if (insertedRows == 1) {
            result = true;
        }
        return result;
    }
    public boolean writeSelect() throws SQLException {
        ResultSet rs4key = pstmt.getGeneratedKeys();
        long lastInsertArticleNo = 0;
        if (rs4key.next()) {
            lastInsertArticleNo = rs4key.getLong(1);
        }

        pstmt = conn.prepareStatement("select at_no, writer, title, at_tag, contents, create_time from article where at_no = ?");
        pstmt.setLong(1, lastInsertArticleNo);
        ResultSet rs = pstmt.executeQuery();
        if (rs.next()) {
            long Atno = rs.getLong("at_no");
            String _writer = rs.getString("writer");
            String _title = rs.getString("title");
            String _atTag = rs.getString("at_tag");
            String _contents = rs.getString("contents");
            String createTime = rs.getString("create_time");
            Article article = new Article(Atno, _writer, _title, _atTag, _contents, createTime);
            req.setAttribute("article", article);
        }
        return pstmt.execute();
    }
}
