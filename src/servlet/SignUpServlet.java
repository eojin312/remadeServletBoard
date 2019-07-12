package servlet;

import dao.SignUpDao;
import extraction.ConnectionMaker;
import servlet.model.Member;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.sql.*;

public class SignUpServlet extends HttpServlet {
    private ConnectionMaker connectionMaker;
    public SignUpServlet() {
        ConnectionMaker connectionMaker = new ConnectionMaker();
    }

    public void doPost(HttpServletRequest req, HttpServletResponse res) throws UnsupportedEncodingException {
        // 한글깨짐으로 utf-8 선언
        req.setCharacterEncoding("utf-8");
        // response 캐릭터셋 설정 + contentType
        res.setCharacterEncoding("UTF-8");
        res.setContentType("text/html");

        // DB 연결
        Connection conn = null;
        PreparedStatement pstmt = null;
        try {
            conn = connectionMaker.getConnecton();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        // DB  sql 문 날리기
        try {
            SignUpDao signUpDao = new SignUpDao();
            signUpDao.signupInsert(req.getParameter("name"), req.getParameter("id"), req.getParameter("pwd"), req.getParameter("gender"), req.getParameter("email"));
            ResultSet rsLastKey = pstmt.getGeneratedKeys();
            long memNo = 0;
            if (rsLastKey.next()){
                memNo = rsLastKey.getLong(1);
            }

            if (memNo > 0) {
                pstmt = conn.prepareStatement("select mem_no, `name`, id, pwd, gender, email from member where mem_no = ?");
                pstmt.setLong(1, memNo);

                ResultSet rs = pstmt.executeQuery();
                if (rs.next()) {
                    // setAttribute 로 객체에 회원정보 등록
                    Member member = new Member(rs.getLong("mem_no"), rs.getString("name"), rs.getString("id"), rs.getString("pwd"), rs.getString("gender"), rs.getString("email"));
                    req.setAttribute("member", member);
                    // jsp 연결
                    RequestDispatcher view = req.getRequestDispatcher("/member/signup/signup-complete.jsp");
                    view.forward(req, res);
                }
            } else {
                // 회원가입 실패
            }


        } catch (SQLException | ServletException | IOException | ClassNotFoundException e) {
            e.printStackTrace();
            // close
        } finally {
            if (pstmt == null) {
                try {
                    pstmt.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            } if (conn == null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

    }
}
