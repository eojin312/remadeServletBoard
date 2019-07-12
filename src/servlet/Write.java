package servlet;

import dao.WriteDao;
import extraction.ConnectionMaker;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Write extends HttpServlet {
    private ConnectionMaker connectionMaker;

    public Write() {
        connectionMaker =  new ConnectionMaker();
    }
    public void doPost(HttpServletRequest req, HttpServletResponse res) throws UnsupportedEncodingException {
        req.setCharacterEncoding("utf-8");
        res.setCharacterEncoding("utf-8");

        String writer = req.getParameter("writer");
        String title = req.getParameter("title");
        String atTag = req.getParameter("atTag");
        String contents = req.getParameter("contents");

        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            conn = connectionMaker.getConnecton();
            WriteDao writeDao = new WriteDao();
            writeDao.writeInsert(req.getParameter("writer"), req.getParameter("title"),  req.getParameter("atTag"), req.getParameter("contents"));
            int rows = pstmt.executeUpdate();
            if (rows != 1) {
                RequestDispatcher view = req.getRequestDispatcher("/board/write/wt-fail.jsp");
                view.forward(req, res);
            }
            try {
                writeDao.writeSelect();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
            RequestDispatcher view = req.getRequestDispatcher("/board/write/complete.jsp");
                view.forward(req, res);
            } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }
}
