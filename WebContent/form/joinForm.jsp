<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<%@ include file="../header/header.jsp"%>
<title>회원가입페이지</title>
</head>
<body>
<form action="/sihum/user?cmd=join" method="post">
	<input type="text" name="username" class="form-control form-control-sm"
		placeholder="아이디입력">
	<br>
	<input type="text" name = "password" class="form-control form-control-sm"
		placeholder="비번입력">
	<br>
	<input type="text" name = "email" class="form-control form-control-sm"
		placeholder="이메일 입력">
	<br>
	<button type="submit" class="btn btn-secondary">회원가입</button>
	</form>
</body>
</html>