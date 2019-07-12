<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 12/06/2019
  Time: 2:16 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>알파리 게시판 복습</title>
    <style>
        h1 {
            text-align: center;
        }
        a {
            list-style: none;
        }
        a:hover {
            color:red;
        }
        .at_clear {
            margin: 100px;
        }
    </style>
</head>
<body>
<h1>글을 다 적었습니다</h1>
<hr />
<table border="1" class="at_clear">
    <tr>
        <td>글번호</td>
        <td>${article.atNo}</td>
    </tr>
    <tr>
        <td>글제목</td>
        <td>${article.title}</td>
    </tr>
    <tr>
        <td>종류</td>
        <td>${article.atTag}</td>
    </tr>
    <tr>
        <td>글내용</td>
        <td>${article.contents}</td>
    </tr>
    <tr>
        <td>글 작성 시간</td>
        <td>${article.createTime}</td>
    </tr>
</table>
<a href="/alphalee">홈으로 가기</a>
</body>
</html>
