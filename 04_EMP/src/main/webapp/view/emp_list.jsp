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
		
		<table border="1" width="420">
			<tr>
				<th>사원번호</th><th>사원명</th>
				<th>부서명</th><!-- <th>관리자번호</th> -->
				<th>입사일자</th>
		<%
			for(int i=0; i<list.size(); i++) {
				EmpDTO dto = list.get(i);
				System.out.println("view dto: "+dto);
		%>
		<tr>
		    <td><%= dto.getEmpno() %></td>
		    <td>
		        <a href="<%=request.getContextPath() %>/content?no=<%=dto.getEmpno() %>">
		            <%= dto.getEname()%>
		        </a>
		    </td>
		    <td><%= dto.getJob()%></td>
		    <td><%= dto.getMgr()%></td>
		    <td><%= dto.getHiredate() %>
		    <%-- 
		    <td> <%=dto.getDeptno() %> </td>
		     --%>
		    <%-- 
		    <td> <%=dto.getHiredate().substring(0,10) %> </td>
		    --%>
		    
		</tr>

		<% 		} //for문 end
			 
		%>	
				
				
				
		
		</table>
		
		<input type="button" value="사원등록"
			onclick="location.href='insert'">
		
	</div>

</body>
</html>