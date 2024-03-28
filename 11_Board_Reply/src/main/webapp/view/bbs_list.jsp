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
				<h3>JSP_BBS 답변형 게시판 리스트</h3>
			<hr>
			<br><br>
			<table border="1">
				<tr>
					<th>글 번호</th> <th>글 제목</th> <th>조회수</th>
					<th>작성일자</th> <th>group</th> <th>step</th>
					<th>indent</th>
				</tr>
				
				<c:set var="list" value="${List }" />
				<c:if test="${!empty list }">
					<c:forEach items="${list }" var="dto">
						<tr>
							<td>${dto.getBoard_no()}</td>
			<td>
				<%-- 댓글인 경우 --%>
				<c:if test="${dto.getBoard_indent() !=0}">
					<c:forEach begin="1" end="${dto.getBoard_indent() }">
						&nbsp;&nbsp;&nbsp;
					</c:forEach>
				</c:if>
				<a href="<%=request.getContextPath() %>/bbs_content.go?num=${dto.getBoard_no()}">
				${dto.getBoard_title() }</a>
			</td>
							
							<td>${dto.getBoard_hit() }</td>
							<td>${dto.getBoard_date().substring(0, 10) }</td>
							<td>${dto.getBoard_group() }</td>
							<td>${dto.getBoard_step() }</td>
							<td>${dto.getBoard_indent() }</td>
						</tr>
					</c:forEach>
				</c:if>
				
				<c:if test="${empty list }">
					<tr>
						<td colspan="7" align="center">
							<h3>전체 게시물이 음슴</h3>		
						</td>
					</tr>
				</c:if>	
			</table>
			<br><br>
			
			<input type="button" value="글쓰기" 
					onclick="location.href='bbs_write.go'">
			<br><br>
			
			<%--검색 요청 폼 --%>
			<form method="post" action="<%=request.getContextPath() %>/bbs_search.go">
			
				<select name="filed">
					<option value="1">제목</option>
					<option value="2">내용</option>
					<option value="3">제목+내용</option>
					<option value="4">작성자</option>
				</select>
				
				<input type="text" name="keyword">&nbsp;&nbsp;&nbsp;
				<input type="submit" value="검색">
			</form>
			
 		</div>
		
</body>
</html>