package servlet;

import dao.DetailDao;
import extraction.ConnectionMaker;
import servlet.model.Article;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
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
        long atNo = this.getArticleNoFromRequest(atNoString);

        // DAO한테 데이터를 얻어온다.
        Article article = null;
        try {
            article = this.getArticleByAtNoFromDB(atNo);
        } catch (SQLException e) {
            return;
        } catch (ClassNotFoundException e) {
            return;
        }

        // JSP한테 article객체를 주면서, HTML을 잘만들어서 응답을 내려주도록 떠넘긴다.
        req.setAttribute("article", article);
        RequestDispatcher view = req.getRequestDispatcher("/board/detail/detail.jsp");
        view.forward(req,res);
    }

    private long getArticleNoFromRequest(String articleNoFromRequest) {
        if (articleNoFromRequest == null) {
            // 에러 JSP페이지로 이동
        }
        return Long.parseLong(articleNoFromRequest);
    }

    private Article getArticleByAtNoFromDB(long atNo) throws SQLException, ClassNotFoundException {
        DetailDao detailDao = new DetailDao();
        Article article = detailDao.selectDetail(atNo);
        return article;
    }
}
