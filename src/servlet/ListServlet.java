package servlet;

import common.Paging;
import dao.ListDao;
import extraction.ConnectionMaker;
import servlet.model.Article;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.*;
import java.util.List;

public class ListServlet extends HttpServlet {

    public static final int PAGE_COUNT_PER_BLOCK = 5;
    public static final int ARTICLE_COUNT_PER_PAGE = 5;
    private ConnectionMaker connectionMaker;

    public ListServlet() {
        connectionMaker = new ConnectionMaker();
    }

    public void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
        req.setCharacterEncoding("utf-8");
        res.setCharacterEncoding("utf-8");
        res.setContentType("text/html");
        String pageNoString = req.getParameter("page_no");

        if (pageNoString == null || pageNoString.equals("")) {
            pageNoString = "1";
        }

        int pageNo = 1;
        try {
            pageNo = Integer.parseInt(pageNoString);
        } catch (NumberFormatException nfe) {
            System.out.println("page_no 파라미터가 잘못되었습니다");
            System.out.println(nfe.getMessage());
            return;
        }

        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            conn = connectionMaker.getConnecton();
        } catch (SQLException e) {
            // error 페이지로 넘기면서 적당한 안내를 해준다.
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            // error 페이지로 넘기면서 적당한 안내를 해준다.
            e.printStackTrace();
        }

        ListDao listDao = new ListDao();
        int totalArticleCount = 0;
        try {
            totalArticleCount = listDao.selectCount();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        Paging paging = new Paging(PAGE_COUNT_PER_BLOCK, totalArticleCount, ARTICLE_COUNT_PER_PAGE, res);
        int startLimit = paging.getStartLimit(pageNo);
        int nextBlockStartPageNo = paging.getStartPageByBlockNo(1);

        req.setAttribute("startPageNo", paging.getStartPageNoByPageNo(pageNo));
        req.setAttribute("lastPageNo", paging.getLastPageNo());
        req.setAttribute("PAGE_COUNT_PER_BLOCK", PAGE_COUNT_PER_BLOCK);

        req.setAttribute("paging", paging);

        req.setAttribute("currentPageNo", pageNo);
        req.setAttribute("getStartPageByBlockNo", nextBlockStartPageNo);

        try {
            listDao = new ListDao();
            List<Article> articleList = listDao.selectList(startLimit, ARTICLE_COUNT_PER_PAGE);
            req.setAttribute("articleList", articleList);
            RequestDispatcher view = req.getRequestDispatcher("/board/list/list.jsp");
            view.forward(req, res);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (pstmt != null) {
                try {
                    pstmt.close();
                } catch (SQLException e) {
                    e.printStackTrace();
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

    }
}
