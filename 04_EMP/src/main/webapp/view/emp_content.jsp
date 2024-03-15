<%@page import="com.emp.model.EmpDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
   <%
   		EmpDTO cont = (EmpDTO)request.getAttribute("Content");
   %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
		<div align="center">
		<hr>
			<h3>EMP 테이블</h3>
		<hr>
		<br><br>
		
		<table border="1">
		<%
			if(cont != null) {
				//데이터가 있으면
		%>
			<tr>
				<th>사원 번호</th>
				<th> <%=cont.getEmpno() %></th>
			</tr>
			<tr>
				<th>사원 이름</th>
				<th> <%=cont.getEmpno() %></th>
			</tr>
			<tr>
				<th>담당 업무</th>
				<th> <%=cont.getEname() %></th>
			</tr>
			<tr>
				<th>관리자번호</th>
				<th> <%=cont.getMgr() %></th>
			</tr>
			<tr>
				<th>사원 급여</th>
				<th> <%=cont.getSal() %></th>
			</tr>
			<tr>
				<th>사원 보너스</th>
				<th> <%=cont.getComm() %></th>
			</tr>
			<tr>
				<th>부서번호</th>
				<th> <%=cont.getDeptno() %></th>
			</tr>
			<tr>
				<th>사원 입사일</th>
				<th> <%=cont.getHiredate() %></th>
			</tr>
			
			<%	
			} else {
				//사원 정보 없으면 
			
			
			%>
				<tr>
					<td colspan="2" align="center">
						<h3>해당 사원의 정보 x</h3>
						</td>
						</tr>
						<%} %>
			
		
		
		
		</table>
		
		</div>

</body>
</html>