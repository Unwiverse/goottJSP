<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%
    request.setCharacterEncoding("UTF-8");
	String user_id = request.getParameter("id").trim(); //맨 앞 맨 뒤 공백 삭제 메서드
	String user_pwd = request.getParameter("pwd");
	String user_name = request.getParameter("name");
	String user_phone = request.getParameter("phone");
	String user_addr = request.getParameter("addr");
	String user_inhabit = request.getParameter("inhabit");
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
			입력받은 비번: <%=user_pwd %><br>
			입력받은 이름: <%=user_name %><br>
			입력받은 전번: <%=user_phone %><br>
			입력받은 주소: <%=user_addr %><br>
			입력받은 거주지: <%=user_inhabit %><br>
			</h2>
		</div>

</body>
</html>