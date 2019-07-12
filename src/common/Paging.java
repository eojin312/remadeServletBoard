package common;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class Paging {
    private int pageCountPerBlock; //페이지 구간 내 페이지 갯수
    private long totalCount; //전체게시글 수
    private int articleCountPerPage; //
    private PrintWriter out;

    public Paging(int pageCountPerBlock, long totalCount) {
        this.pageCountPerBlock = pageCountPerBlock;
        this.totalCount = totalCount;
        this.articleCountPerPage = 10;
    }

    public Paging(int pageCountPerBlock, long totalCount, int articleCountPerPage) {
        this.pageCountPerBlock = pageCountPerBlock;
        this.totalCount = totalCount;
        this.articleCountPerPage = articleCountPerPage;
    }

    public Paging(int pageCountPerBlock, long totalCount, int articleCountPerPage, HttpServletResponse res) {
        this.pageCountPerBlock = pageCountPerBlock;
        this.totalCount = totalCount;
        this.articleCountPerPage = articleCountPerPage;
        try {
            this.out = res.getWriter();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public int getBlockNo(int pageNo) {
        return (int) Math.floor((pageNo - 1)/pageCountPerBlock);
    }

    public int getStartPageNoByPageNo(int pageNo){
        return (this.getBlockNo(pageNo) * this.pageCountPerBlock) + 1;
    }

    public int getStartPageByBlockNo(int blockNo) {
        //구간번호를 받아서 해당 구간의 첫번째 페이지 번호를 알아내서 리턴해준다.
        return blockNo * pageCountPerBlock + 1;
    }

    public int getLastPageNo () {
        double temp = (double) this.totalCount / (double) this.pageCountPerBlock;
        return (int) Math.ceil(temp);
    }

    public int getStartLimit (int pageNo) {
        return (pageNo - 1) * this.articleCountPerPage;
    }


    public void print(int pageNo) {
        int startPageNo = this.getStartPageNoByPageNo(pageNo);
        int lastPageNo = this.getLastPageNo();

        // 구간은 0부터 시작한다. 즉 첫번째 구간은 0구간이다.
        // 구간이 0초과이면 무조건 [이전]이 존재
        int currentBlockNo = this.getBlockNo(pageNo);
        if (currentBlockNo >= 1) { // 2구간부터는 이전이 존재해야만한다.
            System.out.print("[이전]");
        }

        // 1 2 3 4 ... 구간 내 페이지들을 찍는다
        for(int i = 0, j = startPageNo; i < pageCountPerBlock && j <= lastPageNo; i++, j++) {
            System.out.print(j + " ");
        }

        // 마지막 구간을 마지막 페이지번호로 알아낸다
        int lastBlockNo = this.getBlockNo(this.getLastPageNo());

        // 마지막 구간이 첫번째(즉 0번 index)구간이 아니고, 현재 구간이 마지막 구간보다 작으면(미만)
        if (lastBlockNo != 0 && currentBlockNo < lastBlockNo) {
            System.out.print("[다음]");
        }

        System.out.println();
    }

    public void printWeb(int pageNo) throws IOException {
        out.println("================");
        int startPageNo = this.getStartPageNoByPageNo(pageNo);
        int lastPageNo = this.getLastPageNo();

        // 구간은 0부터 시작한다. 즉 첫번째 구간은 0구간이다.
        // 구간이 0초과이면 무조건 [이전]이 존재
        int currentBlockNo = this.getBlockNo(pageNo);
        if (currentBlockNo >= 1) { // 2구간부터는 이전이 존재해야만한다.
            out.print("[이전]");
        }

        // 1 2 3 4 ... 구간 내 페이지들을 찍는다
        for(int i = 0, j = startPageNo; i < pageCountPerBlock && j <= lastPageNo; i++, j++) {
            out.print(j + " ");
        }

        // 마지막 구간을 마지막 페이지번호로 알아낸다
        int lastBlockNo = this.getBlockNo(this.getLastPageNo());

        // 마지막 구간이 첫번째(즉 0번 index)구간이 아니고, 현재 구간이 마지막 구간보다 작으면(미만)
        if (lastBlockNo != 0 && currentBlockNo < lastBlockNo) {
            out.print("[다음]");
        }

        out.println("+++++");
    }
}
