<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="../header/header.jsp"%>

<div class="container">


	<c:forEach var="user" items="${users}">
		<div class="card col-md-12 m-2">
			<form action="/sihum/user?cmd=delete" method="post">
				<div class="card-body" name="${user.id}">
					<h4 class="card-title">${user.id}</h4>
					<h4 class="card-title">${user.username}</h4>
					<h4 class="card-title">${user.password}</h4>
					<h4 class="card-title">${user.email}</h4>
					<h4 class="card-title">${user.role}</h4>
					<button type="submit" class="btn btn-secondary">삭제</button>

				</div>
			</form>
		</div>
	</c:forEach>
	<br />

</div>

</body>
</html>


