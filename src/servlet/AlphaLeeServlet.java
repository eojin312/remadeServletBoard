package servlet;
/**
 * AlphaLee 홈페이지 Servlet 기본적인 틀 짜기
 */

import dao.AlphaLeeDao;
import extraction.ConnectionMaker;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class AlphaLeeServlet extends HttpServlet {
    private ConnectionMaker connectionMaker;

    public AlphaLeeServlet() {
        connectionMaker = new ConnectionMaker();
    }

    public void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
        Connection conn = null;
        PreparedStatement pstmt = null;
        // 한글 선언
        req.setCharacterEncoding("utf-8");

        try {
            AlphaLeeDao alphaLeeDao = new AlphaLeeDao();
            conn = connectionMaker.getConnecton();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        // jsp 연결
        RequestDispatcher view = req.getRequestDispatcher("/board/index.jsp");
        view.forward(req, res);
    }
}