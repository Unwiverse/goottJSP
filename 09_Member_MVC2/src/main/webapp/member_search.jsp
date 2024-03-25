<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div align="center">
		<hr>
			<h2>검색 결과</h2>
		<hr>
		<br><br>
		
		<table border="1">
			<tr>
				<th>회원 번호</th><th>회원 이름</th>
				<th>회원 직업</th><th>회원 등록일</th>
			</tr>
			<c:set var="list" value="${List}" />
			<c:if test="${!empty list}" >
			<c:forEach items="${list}" var="dto">
				<tr>
					<td><c:out value="${dto.num}" /></td>
					<td><c:out value="${dto.memname}" /></td>
					<td><c:out value="${dto.job}" /></td>
					<td><c:out value="${dto.regdate.substring(0, 10)}" /></td>
				</tr>
			
			</c:forEach>
			</c:if>
			<c:if test="${empty list}">
				<tr>
					<td colspan="4" align="center">
					<h3> 검색 회원 x </h3></td>
				</tr>
			</c:if>
		
		</table>
		<br><br>
		<input type="button" value="회원목록" onclick="location.href='select.go'">
		<br><br>
		
		
	
	
	</div>
		
	
</body>
</html>