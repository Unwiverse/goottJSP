<%@page import="com.board.model.BoardDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%
    	BoardDTO cont = (BoardDTO)request.getAttribute("Modify");
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
			<h3>BOARD 테이블 게시판 글 수정 페이지</h3>
		<hr>
		<br><br>
		<form method="post"
		action="<%=request.getContextPath() %>/modify_ok">
		
		<input type="hidden" name="num" value="<%=cont.getBoard_no() %>">
		<input type="hidden" name="db_pwd" value="<%=cont.getBoard_pwd() %>">
		
		<table border="1" width="350">
		<tr>
			<th>작성자</th>
			<td>
				<input type="text" name="writer" readonly value="<%=cont.getBoard_writer()%>">
				</td>
		</tr>
		
		<tr>
			<th>글 제목</th>
			<td>
				<input type="text" name="title" value="<%=cont.getBoard_title()%>">
				</td>
			</tr>
		<tr>
			<th>글 내용</th>
			<td>
			<textarea rows="7" cols="25" name="content"><%=cont.getBoard_cont()%>
			</textarea>
			</td>
		</tr>
		
		<tr>
			<th>글 비번</th>
			<td>
				<input type="password" name="pwd">
				</td>
		</tr>
			
		<tr>
				<td colspan="2" align="center">
					<input type="submit" value="글쓰기">&nbsp;&nbsp;&nbsp;
					<input type="reset" value="다시작성">
					</td>
		</tr>
						
			
		</table>
		
		</form>
	</div>

</body>
</html>