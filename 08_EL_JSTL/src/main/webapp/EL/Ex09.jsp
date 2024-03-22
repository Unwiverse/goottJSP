<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    <%
    	request.setCharacterEncoding("UTF-8");
    	//String user_id = request.getParameter("id").trim();
    	//String user_name = request.getParameter("name").trim();
    	//int user_age = Integer.parseInt(request.getParameter("age").trim());
    %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div align="center">
		<h1>표현언어를 써서 파라미터 값을 출력하는법</h1>
		
		<table border="1" width="505">
			<tr>
				<th>아이디</th>
				<td>	${param.id }</td>
			</tr>
			
			<tr>
				<th>이름</th>
				<td>	${param.name }</td>
			</tr>
			
			<tr>
				<th>나이</th>
				<td>	${param.age }</td>
			</tr>
		
		</table>
	
	</div>

</body>
</html>