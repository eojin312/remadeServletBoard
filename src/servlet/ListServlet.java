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

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
        req.setCharacterEncoding("utf-8");
        res.setCharacterEncoding("utf-8");
        res.setContentType("text/html");

        // 요청을 분석해서 파라미터를 적당한 변수에 담아놓는다.
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

        // DAO객체를 만들어낸다
        ListDao listDao = null;
        try {
            listDao = new ListDao();
        } catch (SQLException e) {
            return;
        } catch (ClassNotFoundException e) {
            return;
        }

        // 총게시물수를 먼저 알아온다
        int totalArticleCount;
        try {
            totalArticleCount = listDao.selectCount();
        } catch (SQLException e) {
            return;
        }

        // 페이징클래스를 통해 가지고 올 범위 정보를 알아내야한다.
        Paging paging = new Paging(PAGE_COUNT_PER_BLOCK, totalArticleCount, ARTICLE_COUNT_PER_PAGE, res);
        int startLimit = paging.getStartLimit(pageNo);

        // 게시물리스트를 가지고온다.
        List<Article> articleList;
        try {
             articleList = listDao.selectList(startLimit, ARTICLE_COUNT_PER_PAGE);
        } catch (SQLException e) {
            return;
        }

        // servlet으로써의 할일을 다 했으니 articleList를 JSP한테 주면서, 남은 업무를 떠넘기면 된다.
        req.setAttribute("articleList", articleList);
        int startPageNo = paging.getStartPageNoByPageNo(pageNo);
        req.setAttribute("startPageNo", startPageNo);
        int lastPageNo = paging.getLastPageNo();
        req.setAttribute("lastPageNo", lastPageNo);
        req.setAttribute("PAGE_COUNT_PER_BLOCK", PAGE_COUNT_PER_BLOCK);
        req.setAttribute("currentPageNo", pageNo);
        RequestDispatcher view = req.getRequestDispatcher("/board/list/list.jsp");
        view.forward(req, res);
    }
}
