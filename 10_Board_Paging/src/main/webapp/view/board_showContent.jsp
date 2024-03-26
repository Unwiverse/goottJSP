<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    <%@	taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
   
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
		<div align="center">
			<hr>
				<h3>게시물 조회 결과</h3>
			<hr>	
			<br><br>
			
			<table>
				<tr>
					<th>게시물 번호</th> <th>작성자</th>
					<th>제목</th> <th>내용</th> <th>비번</th>
					<th>조회수</th> <th>작성일</th>
				</tr>
				
				<c:set var="content" value="${content }"/>
				<c:if test="${!empty content }">
					<c:forEach items="${content }" var="dto">
					<tr>
						<td>${dto.getBoard_no() }</td>
						<td>${dto.getBoard_writer() }</td>
						
						<td>${dto.getBoard_title() }</td>
						<td>${dto.getBoard_cont() }</td>
						<td>${dto.getBoard_pwd() }</td>
						<td>${dto.getBoard_hit() }</td>
						<td>${dto.getBoard_date() }</td>
						<td>${dto.getBoard_update() }</td>
					</tr>
					</c:forEach>
				</c:if>
				<c:if test="${empty content }">
					<tr>
						<td>
						<h3>내용이 음슴 ㅋ</h3></td>
					</tr>
				</c:if>
					
			
			</table>
			<br><br>
			<input type="button" value="목록으로" onclick="location.href='select.go'">
			<hr>
		</div>

</body>
</html>