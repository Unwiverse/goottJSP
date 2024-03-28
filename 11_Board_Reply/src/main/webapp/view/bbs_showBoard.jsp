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
		
		<table border="1">
			<tr>
				<th>게시물 번호</th> <th>작성자</th>
				<th>제목</th> <th>내용</th> <th>비번</th>
				<th>조회수</th> <th>작성일</th> <th>업데이트날짜</th>
				<th>그룹</th> <th>스텝</th> <th>인덴트</th>
			</tr>
			
			<c:set var="board" value="${board }"/>
			<c:if test="${!empty board }">
				<tr>
					<td>${board.getBoard_no() }</td>
					<td>${board.getBoard_writer() }</td>
					<td>${board.getBoard_title() }</td>
					<td>${board.getBoard_cont() }</td>
					<td>${board.getBoard_pwd() }</td>
					<td>${board.getBoard_hit() }</td>
					<td>${board.getBoard_date() }</td>
					<td>${board.getBoard_update() }</td>
					<td>${board.getBoard_group() }</td>
					<td>${board.getBoard_step() }</td>
					<td>${board.getBoard_indent() }</td>
				</tr>
			</c:if>
			<c:if test="${empty board }">
				<tr>
					<td>
					<h3>내용이 음슴 ㅋ</h3></td>
				</tr>
			</c:if>
				
		
		</table>
		<br><br>
		<input type="button" value="글수정" 
		onclick="location.href='bbs_modify.go?no=${board.getBoard_no() }'">
		
		<input type="button" value="글삭제" 
		onclick="if(confirm('진짜?')) {
		location.href='bbs_delete.go?no=${board.getBoard_no() }'
		} else {return;} ">
		
		<input type="button" value="답변글"
		onclick="location.href='bbs_reply.go?no=${board.getBoard_no() }'">
		 
		<input type="button" value="목록으로" onclick="location.href='bbs_list.go'">
		<hr>
	</div>

</body>
</html>