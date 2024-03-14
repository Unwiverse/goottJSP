<%@page import="com.emp.model.EmpDTO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%
    	List<EmpDTO> list = (List<EmpDTO>)request.getAttribute("List");
    %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

		<div align="center">
			<hr width="30%" color="red">
				<h3>EMP 테이블 전체 사원 리스트 페이지</h3>
			<hr width="30%" color="red">
			<br><br>
			
			<table border="1" width="400">
			<%
				for(int i=0; i<list.size(); i++) {
					EmpDTO dto = list.get(i);
			%>
			<tr>
					<td><%= dto.getEmpno()%></td>
					<td><%= dto.getEname()%></td>
					<td><%= dto.getJob()%></td>
					<td><%= dto.getMgr()%></td>
			</tr>
			<% 
				}
			%>
				
				
				
			</table>
		</div>

</body>
</html>