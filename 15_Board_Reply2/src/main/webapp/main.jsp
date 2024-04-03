<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>															
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div align="center">
		<hr>
			<h3>tbl_board 게시판 테이블 메인 페이지</h3>
		<hr>
		<br><br> 
		<%-- request.getContextPath(): 현재 프로젝트명을 문자열로 반환해주는 메서드 --%>
		<a href="<%=request.getContextPath() %>/tbl_list.go">[전체 게시물 목록]</a>
	</div>
	
</body>
</html>