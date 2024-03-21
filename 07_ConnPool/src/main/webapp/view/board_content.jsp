<%@page import="java.util.List"%>
<%@page import="com.board.model.BoardDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%
    	BoardDTO Content = (BoardDTO)request.getAttribute("Content");
   	    
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
					<h3>작성글 상세 페이지</h3>
				<hr>
				<br><br>
			<table border="1">
				<tr>
					<th>게시글 번호</th>
	                <th>글 제목</th>
	                <th>작성자</th>
	                <th>조회수</th>
	                <th>작성일자</th>
				</tr>
				<% 
					if(Content != null) {
				%>
					<tr>
						<td><%=Content.getBoard_no() %></td>
						<td><%=Content.getBoard_title() %></td>
						<td><%=Content.getBoard_writer() %></td>
						<td><%=Content.getBoard_hit() %></td>
						<td><%=Content.getBoard_date() %></td>
					</tr>
				<% } else { %>
					<tr>
						<td align="center">
							<h2>게시글을 가져올 수 없음</h2>
						</td>
					</tr>
				<% } %>
			
			</table>
			<input type="button" value="수정" onclick="location.href='modify?no=<%=Content.getBoard_no() %>'">&nbsp;&nbsp;
			<input type="button" value="게시글목록" onclick="location.href='select.go'">
			<input type="button" value="삭제" onclick="if(confirm('ㄹㅇ?')) { 
	    	location.href='view/board_delete.jsp?no=<%=Content.getBoard_no() %>'
	    	} else {return;}">&nbsp;&nbsp;
			</div>

</body>
</html>