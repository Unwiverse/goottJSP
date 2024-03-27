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
				<h3>게시물 정보 수정</h3>
			<hr>
			<br><br>
			<form method="post" action="<%=request.getContextPath()%>/update_ok.go">
				<table border="1"> 
					<tr>
						<th>게시물 번호</th> <th>작성자</th> <th>게시물 제목</th>
						<th>게시물 내용</th>
				
					</tr>
					
					<!--<c:set var="board" value="${board }"/>-->
					<tr>
						<td><input type="text" name="board_no" readonly value="${board.board_no }"></td>
						<td><input type="text" name="writer" readonly value="${board.board_writer }"></td>
						<td><input type="text" name="title" value="${board.board_title }"></td>
						<td><input type="text" name="cont" value="${board.board_cont }"></td>
						
					</tr>
					
					<tr>
						<td colspan="8" align="center">
							<input type="submit" value="제출">
						</td>
					</tr>
					</table>
				</form>
			
		</div>
</body>
</html>