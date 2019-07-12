package example;

import common.Paging;

public class PagingTest {

    public static final int PAGE_COUNT_PER_BLOCK = 10;
    public static final int TOTAL_COUNT = 286;

    public static void main(String[] args) {
        Paging paging = new Paging(PAGE_COUNT_PER_BLOCK, TOTAL_COUNT);

        int pageNo = 3;
//        System.out.println("pageNo = " + pageNo);
//        System.out.println(paging.getBlockNo(pageNo) + " = 0 구간");
//        System.out.println(paging.getStartPageNoByPageNo(pageNo) + " = 1 번페이지");
//        paging.print(pageNo);
//        System.out.println("------------------------------");
//
//
//        pageNo = 7;
//        System.out.println("pageNo = " + pageNo);
//        System.out.println(paging.getBlockNo(pageNo) + " = 1 구간");
//        System.out.println(paging.getStartPageNoByPageNo(pageNo) + " = 6 번페이지");
//        paging.print(pageNo);
//        System.out.println("------------------------------");

        pageNo = 17;
        System.out.println("pageNo = " + pageNo);
        System.out.println(paging.getBlockNo(pageNo) + " = 3 구간");
        System.out.println(paging.getStartPageNoByPageNo(pageNo) + " = 16 번페이지");
        paging.print(pageNo);
        System.out.println("------------------------------");

        pageNo = 5;
        System.out.println("pageNo = " + pageNo);
        System.out.println(paging.getBlockNo(pageNo) + " = 0 구간");
        System.out.println(paging.getStartPageNoByPageNo(pageNo) + " = 1 번페이지");
        paging.print(pageNo);
        System.out.println("------------------------------");

        System.out.println("마지막 페이지 번호 = " + paging.getLastPageNo());
    }
}
