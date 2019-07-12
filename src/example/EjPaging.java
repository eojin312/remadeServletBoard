package example;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * 테스트 페이징
 * 기능들 선언 후 게터세터 선언
 */
public class EjPaging extends HttpServlet {

    public static final String DB_PWD = "dldjwls02";
    public static final String DB_ID = "root";
    public static final String DB_URL = "jdbc:mysql://localhost:3306/alphaleeremind?useSSL=false";

    private int pageSize; // 게시글 수
    private int firstPageNo; //첫번째 페이지번호
    private int prevPageNo; //이전 페이지 번호
    private int pageNo; // 페이지 번호
    private int startPageNo; // 시작 페이지 번호
    private int endPageNo; //끝페이지
    private int nextPageNo; //다음 페이지 번호
    private int finalPageNo; //마지막페이지
    private int totalCount; //게시 글 전체 수

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getFirstPageNo() {
        return firstPageNo;
    }

    public void setFirstPageNo(int firstPageNo) {
        this.firstPageNo = firstPageNo;
    }

    public int getPrevPageNo() {
        return prevPageNo;
    }

    public void setPrevPageNo(int prevPageNo) {
        this.prevPageNo = prevPageNo;
    }

    public int getPageNo() {
        return pageNo;
    }

    public void setPageNo(int pageNo) {
        this.pageNo = pageNo;
    }

    public int getStartPageNo() {
        return startPageNo;
    }

    public void setStartPageNo(int startPageNo) {
        this.startPageNo = startPageNo;
    }

    public int getEndPageNo() {
        return endPageNo;
    }

    public void setEndPageNo(int endPageNo) {
        this.endPageNo = endPageNo;
    }

    public int getNextPageNo() {
        return nextPageNo;
    }

    public void setNextPageNo(int nextPageNo) {
        this.nextPageNo = nextPageNo;
    }

    public int getFinalPageNo() {
        return finalPageNo;
    }

    public void setFinalPageNo(int finalPageNo) {
        this.finalPageNo = finalPageNo;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
        this.makingPaging();
    }


    /**
     * 페이징 생성
     */
    private void makingPaging() {
        if (this.totalCount == 0) {return;} //게시글 전체가 없는 경우
        if (this.pageNo == 0) {this.setPageNo(1);}
        if (this.pageSize == 0){this.setPageSize(10);}

        int finalPage = (totalCount + (pageSize - 1)/pageSize);
        if (this.pageNo > finalPage) this.setPageNo(finalPageNo);
        if (this.pageNo < 0 || this.pageNo > finalPageNo) this.pageNo = 1;

        boolean isNowFirst = pageNo == 1 ? true : false;
        boolean isNowFinal = pageNo == finalPage ? true : false;

        int startPage = ((pageNo - 1)/10) * 10 - 1;
        int endPage = startPage + 10 - 1;
        if (endPage > finalPage) {
            endPage = finalPage;
        }
        this.setPrevPageNo(1);
        if (isNowFirst) {
            this.setPrevPageNo(1);
        } else {
            this.setPrevPageNo(((pageNo - 1) < 1 ? 1:(pageNo - 1)));
        }
        this.setStartPageNo(startPage);
        this.setEndPageNo(endPage);
        if (isNowFirst) {
            this.setNextPageNo(finalPageNo);
        } else {
            this.setNextPageNo(((pageNo + 1) > finalPage ? finalPage : (pageNo + 1)));
        }
        this.setFinalPageNo(finalPage);
    }

    public void doGet (HttpServletRequest req, HttpServletResponse res) throws UnsupportedEncodingException {
        req.setCharacterEncoding("utf-8");
        res.setCharacterEncoding("utf-8");
        res.setContentType("text/html");
        String atNoString = req.getParameter("atNo");
        if (atNoString == null) {

        }
        long atNo = Long.parseLong(atNoString);

        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            conn = DriverManager.getConnection(DB_URL, DB_ID, DB_PWD);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}

