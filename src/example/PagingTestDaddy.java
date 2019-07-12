package example;

import java.util.Scanner;

public class PagingTestDaddy {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("페이지번호를 입력해주세요 : ");
        int pageNo = sc.nextInt();

        System.out.print("총 게시글의 수를 입력해주세요 : ");
        int totalCount = sc.nextInt();

        System.out.print("페이지당 보여줄 게시글의 수를 입력해주세요 : ");
        int articalCountPerPage = sc.nextInt();

        int pageCount = (int) Math.floor(totalCount / articalCountPerPage) + 1;

        final int PAGES_PER_BLOCK = 5; //1-5까지 보여주기 위해 지정하는 변수

        int blockNo = (int) Math.floor(pageNo / PAGES_PER_BLOCK);
        int blockCount = (int) Math.floor(pageCount / PAGES_PER_BLOCK);
        int startPosition = blockNo * PAGES_PER_BLOCK + 1;

        if (blockNo > 0) {
            System.out.print("이전 ");
        }

        for (int i = startPosition, j=0; i <= pageCount && j < PAGES_PER_BLOCK; i++, j++) {
            if (i == pageNo) {
                System.out.print("[" + i + "] ");
            } else {
                System.out.print(i + " ");
            }
        }

        if (pageCount > PAGES_PER_BLOCK && blockNo < blockCount) {
            System.out.print(" 다음 ");
        }
    }
}
