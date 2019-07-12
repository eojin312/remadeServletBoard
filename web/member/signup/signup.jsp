<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!doctype html>
<html>
<head>
    <title>알파리 복습 게시판</title>
    <style>
        table {
            margin-left: 38%;
            margin-top: 5%;
        }
        h1 {
            width: 100%;
            text-align: center;
        }
    </style>
</head>
<body>
<h1>회원가입</h1>
<hr />
<form action="/member/signup" name="signup_form" id="signup_form" method="POST">
    <table border="1">
        <tr>
        <td>이름:</td> <td><input type="text" name="name" size="10"></td>
        </tr>
        <tr>
            <td>아이디 :</td> <td><input type="text" name="id" size="10"></td>
        </tr>
        <tr>
            <td>비밀번호 :</td> <td><input type="password" name="pwd" size="10"></td>
        </tr>
        <tr>
            <td>성별 :</td>
            <td>
                <input type="radio" name="gender" value="M" size="10"> 남
                <input type="radio" name="gender" value="F" size="10">여
            </td>
            <tr>
                <td>이메일 :</td> <td><input type="text" name="email" id="email">@<input type="text" name="domain" id="domain" disabled value="naver.com">
                <select name="selectEmail" id="selectEmail">
                    <option value="1">직접입력</option>
                    <option value="naver.com">naver.com</option>
                    <option value="hanmail.com">hanmail.com</option>
                    <option value="nate.com">nate.com</option>
                    <option value="gmail.com">gmail.com</option>
                </select>
                </td>
            </tr>
        </tr>
        <input type="button" value="회원가입 onclick" onclick="signup()">
        <input type="submit" value="회원가입 submit" id="buttonsubmit">
    </table>
</form>
<script type="text/javascript" src="http://code.jquery.com/jquery-latest.min.js"></script>
    <script type="text/javascript">
            $('#selectEmail').change(function x(){

                $("#selectEmail option:selected").each(function () {
                    if ($(this).val() == '1') { //직접 입력할 땐
                        $("#domain").val(''); //빈칸으로
                        $("#domain").attr("disabled", false) //활성화
                    } else {
                        $('#domain').val($(this).text());
                        $('#domain').attr("disabled", true);
                    }
                });
            });
    </script>


<script>
    function signup() {
        document.getElementById('signup').method = 'post';
        document.getElementById('signup').submit();
    }
</script>
</body>
</html>
