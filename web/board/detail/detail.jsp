<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!doctype html>
<html>
<head>
    <title></title>
    <style>
        table, td {
            border: 1px solid red;
        }
        table {
            width: 30%;
            height: 60px;
            margin-right: auto;
            text-align: center;
        }

    </style>
</head>
<body>
<p>${article.title}</p>
<table>
    <tr>
        <th>글번호</th>
        <th>작성자</th>
        <th>올린 시간</th>
        <th>글 종류</th>
    </tr>
    <tr>
        <td>${article.atNo}</td>
        <td>${article.writer}</td>
        <td>${article.createTime}</td>
        <td>${article.atTag}</td>
    </tr>
</table>
<p>${article.contents}</p>

<a href="/alphalee">홈으로 가기</a>
<a href="/board/list">목록으로</a>
<input type="button" onclick="history.back()" value="뒤로가기" style="width: 60px; height: 100px;">
</body>
</html>

