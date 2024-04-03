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
			<h3>tbl_board 전체 목록 페이지</h3>
		<hr>
		<br><br>
		
		<table border="1">
			<tr>
				<th>번호</th> <th>작성자</th> <th>제목</th> <th>내용</th>
				<th>비번</th> <th>등록일</th> <th>수정일</th>
			</tr>
			
			<c:set var="dto" value="${list }" />
				<c:if test="${!empty list }">
					<c:forEach items="${list }" var="dto">
						<tr>
							<td>${dto.getBno() }</td>
							<td>${dto.getWriter() }</td>
							<td>
							<a href="<%=request.getContextPath() %>/tbl_content.go?no=${dto.getBno() }">
							${dto.getTitle() }</a>&nbsp;&nbsp;<b>[${dto.getRegCnt() }]</b>
							</td>
							<td>${dto.getContent() }</td>
							<td>${dto.getPwd() }</td>
							<td>${dto.getRegdate() }</td>
							<td>${dto.getReupdate() }</td>
						</tr>
					</c:forEach>
				</c:if>
				
				<c:if test="${empty list }">
					<tr>
						<td colspan="4" align="center">
							<h1>잉어 붕어</h1>
						</td>
					</tr>
				</c:if>
		
		</table>
		<br><br>
		<input type="button" value="새 글 쓰기" onclick="location.href='view/tbl_write.jsp'">
	
	</div>

</body>
</html>