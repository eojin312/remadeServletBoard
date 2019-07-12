package servlet;

import dao.DetailDao;
import extraction.ConnectionMaker;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

public class DetailServlet extends HttpServlet {
    private ConnectionMaker connectionMaker;

    public DetailServlet() {
        connectionMaker = new ConnectionMaker();
    }

    public void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
        req.setCharacterEncoding("utf-8");
        res.setContentType("text/html");
        String atNoString = req.getParameter("atNo");
        if (atNoString == null) {
            // 에러 JSP페이지로 이동
        }
        long atNo = Long.parseLong(atNoString);

        Connection conn = null;
        String pstmt = null;

        try {
            try {
                conn = connectionMaker.getConnecton();
            } catch (SQLException e1) {
                e1.printStackTrace();
            } catch (ClassNotFoundException e1) {
                e1.printStackTrace();
            }

            try {
                DetailDao detailDao = new DetailDao();
                pstmt = detailDao.selectDetail();
            } catch (SQLException e1) {
                e1.printStackTrace();
            } catch (ClassNotFoundException e1) {
                e1.printStackTrace();
            }
            RequestDispatcher view = req.getRequestDispatcher("/board/detail/detail.jsp");
            view.forward(req, res);
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
