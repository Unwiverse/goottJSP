<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%
	String user_id = request.getParameter("id");
	//String user_pwd = request.getParameter("pwd");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
		<div align="center">
			<h2>
			입력받은 아이디: <%=user_id %><br>
			입력받은 비번: <%=request.getParameter("pwd") %>
			</h2>
		</div>
		
	
</body>
</html>