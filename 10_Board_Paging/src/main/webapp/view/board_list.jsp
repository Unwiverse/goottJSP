<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@
    	taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"
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
			<h3>BOARD 테이블 게시물</h3>
		<hr>
		<br><br>
		
		<table border="1" width="800">
			<tr>
				<td colspan="5" align="left">
					전체 게시물 수: ${totalRecord }개 
				</td>
			</tr>
			
			<tr>
				<th>게시물 번호</th> <th>게시글 제목</th>
				<th>게시글 조회수</th> <th>작성일자</th> <th>상세정보</th>
			</tr>
			
			<c:set var="list" value="${List }"></c:set>
			<c:if test="${!empty list }">
			<c:forEach items="${list }" var="dto">
				<tr>
					<td> ${dto.getBoard_no() }</td>
					<td> ${dto.getBoard_title() }</td>
					<td> ${dto.getBoard_hit() }</td>
					<td> ${dto.getBoard_date().substring(0, 10) }</td>
					<td> <input type="button" value="상세정보" 
					onclick="location.href='content.go?num=${dto.getBoard_no()}'">
					</td>
				</tr>
					
				</c:forEach>
			</c:if>
			<c:if test="${empty list }">
				<tr>
					<td colspan="5" align="center">
						<h3>가져올 게시물이 없습니다</h3>
						</td>
				</tr>
			</c:if>
			</table>
			<br><br>
			<input type="button" value="글쓰기" onclick="location.href='write.go'">
			<br><br>
		
		<%-- 페이징 처리 --%>
			<c:if test="${page > block }">
				<a href="select.go?page=1">◀◀</a>
				<a href="select.go?page=${startBlock -1 }">◀</a>
				
			</c:if>
			
			<c:forEach begin="${startBlock}" end="${endBlock}" var="i">
				<c:if test="${i == page}">
					<b><a href="select.go?page=${i}">[${i}]</a></b>
				</c:if>
				
				<c:if test="${i != page}">
					<a href="select.go?page=${i}">[${i}]</a>
				</c:if>
				
			</c:forEach>
			
			<c:if test="${endBlock < allPage}">
				<a href="select.go?page=${page + 1}">▶</a>
				<a href="select.go?page=${allPage}">▶▶</a>
			</c:if>

	</div>

</body>
</html>