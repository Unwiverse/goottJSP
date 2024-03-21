<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%
    	int no = Integer.parseInt(request.getParameter("no")); //MVC1 방식이라나 뭐라나
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
			<h3>BOARD 테이블 게시글 삭제 폼 페이지</h3>
		<hr>
		<br><br>
		<form method="post" action="<%=request.getContextPath() %>/delete_ok.go">
		
		<input type="hidden" name="no" value="<%=no%>">
		
		<table border="1" width="400">
			<tr>
				<th>삭제할 게시글 비번</th>
				<td>
					<input type="password" name="pwd">
				</td>
			</tr>
			
			<tr>
				<td colspan="2" align="center">
					<input type="submit" value="글삭제">&nbsp;&nbsp;&nbsp;
					<input type="reset" value="다시작성">
					</td>
					</tr>
		</table>
		
		</form>
	</div>

</body>
</html>