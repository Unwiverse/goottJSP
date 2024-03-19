<%@page import="com.member.model.MemberDTO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%
	List<MemberDTO> members = (List<MemberDTO>)request.getAttribute("Search");
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
			<h3>Member 테이블 검색 목록 페이지</h3>
		<hr>
		<br><br>
	<table border="1" width="450">
	<tr>
		<th>회원 번호</th><th>회원 이름</th>
		<th>회원 직업</th><th>회원 등록일</th>
		
	</tr>
	<%
		if(members.size() != 0) {
			for(int i=0; i<members.size(); i++) {
				MemberDTO member= 	members.get(i);
	%>
		<tr>
			<td> <%=member.getNum() %> </td>
			<td> <%=member.getMemname() %> </td>
			<td> <%=member.getJob() %> </td>
			<td> <%=member.getRegdate().substring(0, 10) %> </td>
		</tr>
		<% 	} //for문 end  
		}else {  //회원 리스트가 없으면
		
		%> 
			<tr>
				<td colspan="4" align="center">
					<h3>검색된 회원 목록이 없습니다</h3>
					
		<% } %>
		
	</table>
	<br><br>
	
	<input type="button" value="회원목록" onclick="location.href='select.go'">
	<br><br>
	</div>

</body>
</html>