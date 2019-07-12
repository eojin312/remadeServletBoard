<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 12/06/2019
  Time: 1:21 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>알파리 게시판 복습</title>
    <style>
        h1 {
            margin-top: 30px;
            text-align: center;
        }
        .write_table {
            margin-left: 28%;
            margin-top: 50px;
        }
        .atTag {
            margin-top: 30px;
        }
    </style>
</head>
<h1>글쓰기</h1>
<hr />
<body>
<form action="/board/write" method="POST" id="article_form" name="article_form">
    <table class="atTag">
        <tr><td>글 종류 :</td><td><input type="checkbox" name="atTag" value="연애">연애</td>
            <td><input type="checkbox" name="atTag" value="운동">운동</td>
            <td><input type="checkbox" name="atTag" value="재미">재미</td>
            <td><input type="checkbox" name="atTag" value="상담">상담</td>
        </tr>
    </table>
    <table border="1" class="write_table">
        <tr><td>작성자 :</td><td><input type="text" id="writer" name="writer"></td></tr>
        <tr><td>제목 :</td><td><input type="text" name="title" id="title"></td></tr>
        <tr><td>글 :</td><td><textarea style="width: 500px; height: 500px;" name="contents" id="contents"></textarea></td></tr>
    </table>


    <input type="submit" value="완료">
</form>
</body>
</html>
