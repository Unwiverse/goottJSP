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
		<h2>입력 폼(Ex01.jsp)에서 넘어온 데이터</h2>
		<%-- 
		<h3>
			입력받은 아이디: <%=request.getParameter("id").trim() %>
			입력받은 비번: <%=request.getParameter("pwd").trim() %>
		</h3> --%>
		<hr>
		
		<h2>세션으로 넘오온 데이터</h2>
		
		<h3>
			세션으로 받은 이름: <%=session.getAttribute("name") %>
			세션으로 받은 연락처: <%=session.getAttribute("phone") %>
			
		</h3>
		<hr>
		<a href="Ex01_04.jsp">다음으로</a>
	
	</div>
</body>
</html>