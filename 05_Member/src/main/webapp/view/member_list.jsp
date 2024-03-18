<%@page import="com.member.model.MemberDTO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%
	List<MemberDTO> members = (List<MemberDTO>)request.getAttribute("List");
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
			<h3>Member 테이블 전체 회원 목록 리스트</h3>
		<hr>
		<br><br>
	<table border="1">
	<tr>
		<th>회원 번호</th><th>회원 이름</th>
		<th>회원 직업</th><th>회원 등록일</th>
		<th>상세정보</th>
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
			<td>
				<input type="button" value="상세정보" onclick=
				"location.href='content.go?num=<%=member.getNum()%>'">
				
			</td>
		</tr>
		<% 	} //for문 end  
		}else {  //회원 리스트가 없으면
		
		%> 
			<tr>
				<td colspan="2" align="center">
					<h3>회원 전체 목록이 없습니다</h3>
					
		<% } %>
		
	</table>
	<br><br>
	<input type="button" value="회원추가" 
	onclick="location.href='view/member_insert.jsp'">
	<br>
	
	<%-- 검색처리 --%>
	<form method="post" action="<%=request.getContextPath() %>/search.go">
	<select name="field">
		<option value="id">아이디</option>
		<option value="name">이름</option>
		<option value="job">직업</option>
		<option value="addr">주소</option>
	</select>
	<input type="text" name="keyword">&nbsp;
	<input type="submit" value="검색">
	
	</form> 
	
	</div>

</body>
</html>