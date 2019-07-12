<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 09/06/2019
  Time: 1:55 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>복습 게시판</title>
    <style>
        body {
            margin:20%;
        }
        ul {
            list-style: none;
        }
    </style>
</head>
<body>
<table border="1">
    <tr>
        <td>회원번호</td>
        <td>${member.memNo}</td>
    </tr>
    <tr>
    <td>이름</td>
    <td>${member.name}</td>
    </tr>
    <tr>
        <td>아이디</td>
        <td>${member.id}</td>
    </tr>
    <tr>
        <td>성별</td>
        <td>${member.gender}</td>
    </tr>
</table>
<ul>
    <li><a href="/alphalee">홈으로 가기</a></li>
</ul>
</body>
</html>
