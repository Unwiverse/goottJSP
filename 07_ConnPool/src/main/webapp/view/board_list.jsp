<%@page import="com.board.model.BoardDTO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%
    	List<BoardDTO> boards = (List<BoardDTO>)request.getAttribute("List");
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
			<h3>게시판 전체 목록</h3>
			<hr>
			<br><br>
			<table border="1" width="500">
				<tr>
					<td colspan="5" align="left">
						전체 게시물 수: <b><%=boards.size() %></b>개
					</td>
				</tr>
			
			
			<tr>
				<th>게시글 번호</th><th>글 제목</th><th>작성자</th>
				<th>조회수</th><th>작성일자</th>
			</tr>
			
				<%
					if(boards.size() != 0) {
						for(BoardDTO dto: boards) {
				%>			
					<tr>
						<td> <%=dto.getBoard_no() %> </td>
						<td> <a href="<%=request.getContextPath() %>/content.go?no=<%=dto.getBoard_no() %>">
							<%=dto.getBoard_title() %>
							</a> 
						</td>
						<td> <%=dto.getBoard_writer() %> </td>
						<td> <%=dto.getBoard_hit() %> </td>
						<td> <%=dto.getBoard_date().substring(0, 10) %> </td>
						
					</tr>
						<% } //for문 end
					} else {
						//게시물 전체 목록이 없음
					%>
						<tr>
							<td colspan="5" align="center">
								<h3>전체 게시물 목록이 없음</h3>
							</td>
						</tr>
					<%} %>
				</table>
				<br><br>
				<input type="button" value="글쓰기" onclick="location.href='view/board_write.jsp'">
				<br><br>
				
				<%-- 검색 관련 요청 태그 --%>
				<form method="post" 
						action="<%=request.getContextPath() %>/search.go">
				<select name="field">
					<option value="title">제목</option>
					<option value="cont">내용</option>
					<option value="title_cont">제목+내용</option>
					<option value="writer">작성자</option>
				
				</select>
				<input type="text" name="keyword">&nbsp;&nbsp;&nbsp;
				<input type="submit" value="검색">
				</form>
		</div>
</body>
</html>