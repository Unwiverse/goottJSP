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
			<h3>삭제할 게시글 비번</h3>
		<hr>
		<br><br>
		
		<form method="post" action ="<%=request.getContextPath()%>/bbs_delete_ok.go">
		
		 <input type="hidden" name="bbs_no" value="${param.no }">
		 
		 <table border="1">
		 	<tr>
		 		<th>삭제할 글 비번</th>
		 		<td><input type="password" name="pwd"></td>
		 	</tr>
		 	
		 	<tr>
		 		<td colspan="2" align="center">
		 		<input type="submit" value="확인">
		 		<input type="reset" value="다시"></td>
		 	</tr>
		  </table>
		</form>
	</div>
</body>
</html>