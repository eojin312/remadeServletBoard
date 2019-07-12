package dao;

import extraction.ConnectionMaker;
import servlet.model.Article;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ListDao {

    Connection conn;
    PreparedStatement pstmt;

    public ListDao() {
        ConnectionMaker connectionMaker = new ConnectionMaker();
        try {
            this.conn = connectionMaker.getConnecton();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    int totalArticleCount = -1;
    public int selectCount() throws SQLException {
        pstmt = conn.prepareStatement("select count(*) as cnt from article");
        ResultSet rs = pstmt.executeQuery();
        rs.next();
        totalArticleCount = rs.getInt(1);
        return totalArticleCount;
    }

    public List<Article> selectList(int startLimit, int articleCountPerPage) throws SQLException {

        pstmt = conn.prepareStatement("select at_no, title, writer, at_tag, create_time from article order by at_no desc limit ?, ?");
        pstmt.setInt(1, startLimit);
        pstmt.setInt(2, articleCountPerPage);
        ResultSet rs = pstmt.executeQuery();
        List<Article> articleList = new ArrayList<Article>();
        while(rs.next()){
            long _atNo = rs.getLong("at_no");
            String title = rs.getString("title");
            String writer = rs.getString("writer");
            String atTag = rs.getString("at_tag");
            String createTime = rs.getString("create_time");
            Article article = new Article(_atNo, writer, title, atTag, createTime);
            articleList.add(article);
        }

        return articleList;
    }
}
