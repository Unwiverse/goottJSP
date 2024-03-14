<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

		<div align="center">
			<hr width="30%" color="orange">
				<h3>메인 페이지</h3>
			<hr width="30%" color="orange">
			<br><br>
			
			<h3>
			<%=request.getAttribute("name") %>님 어쩌라구요
			연락처: <%=request.getAttribute("phone") %>
			</h3>
			
			<h4>
			<%-- null값이 넘어오기 때문에 error가 남 --%>
			<%-- 입력받은 아이디: <%=request.getParameter("id").trim() %><br>
			입력받은 비번: <%=request.getParameter("pwd").trim() %><br>--%>
			</h4>
		</div>

</body>
</html>