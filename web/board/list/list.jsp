<%@ page import="common.Paging" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Title</title>
    <style>
        h1 {
            margin-top: 30px;
            text-align: center;
        }
        a:visited {
            color: lightslategray;
        }
        table, td {
            border: 1px solid red;
        }
        table {
            width: 60%;
            height: 100px;
            margin: auto;
            text-align: center;
        }


    </style>
</head>
<body>
<h1>게시물 리스트</h1>
<hr />
<table border="1">
    <tr>
        <th>글 번호</th>
        <th>작성자</th>
        <th>제목</th>
        <th>종류</th>
        <th>작성시간</th>
    </tr>
    <c:forEach var="article" items="${articleList}">
        <tr>
            <td>${article.atNo}</td>
            <td>${article.writer}</td>
            <td><a href="/board/detail?atNo=${article.atNo}">${article.title}</a></td>
            <td>${article.atTag}</td>
            <td>${article.createTime}</td>
        </tr>
    </c:forEach>
</table>

<%
    int startPageNo = (Integer) request.getAttribute("startPageNo");
    int lastPageNo = (Integer) request.getAttribute("lastPageNo");
    int PAGE_COUNT_PER_BLOCK = (Integer) request.getAttribute("PAGE_COUNT_PER_BLOCK");
    Paging paging = (Paging) request.getAttribute("paging");
    int currentPageNo = (Integer) request.getAttribute("currentPageNo");

    int currentBlockNo = paging.getBlockNo(currentPageNo);
    if (currentBlockNo >= 1) { // 2구간부터는 이전이 존재해야만한다.
        out.print("[이전]");
    }

    for(int i = 0, j = startPageNo; i < PAGE_COUNT_PER_BLOCK && j <= lastPageNo; i++, j++) {
        out.print("<a href=\"./list?page_no=" + j + "\">" + j + "</a>&nbsp;");
    }

    int lastBlockNo = paging.getBlockNo(lastPageNo);

    // 마지막 구간이 첫번째(즉 0번 index)구간이 아니고, 현재 구간이 마지막 구간보다 작으면(미만)
    if (lastBlockNo != 0 && currentBlockNo < lastBlockNo) {

        out.print("<a href=\"</a>&nbsp;");
    }

%>


<a href="/alphalee">홈으로 가기</a>
</body>
</html>
