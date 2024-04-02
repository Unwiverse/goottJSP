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
			<h3>Are you committed?</h3>
		<hr>
		<br><br>
	<form method="post"
		action="<%=request.getContextPath() %>/upload_delete_ok.go">
		
		<input type="hidden" name="upload_no" value="${param.no }">
		
		<table border="1">
			<tr>
				<th>삭제할 게시글 비번</th>
				<td> <input type="password" name="pwd"></td>
				</tr>
			<tr>
				<td colspan="2" align="center">
					<input type="submit" value="삭제">&nbsp;&nbsp;&nbsp;
					<input type="reset" value="재입력">&nbsp;&nbsp;&nbsp;
				</td>
			</tr>
			
		
		</table>
		
		</form>
	</div>
	

</body>
</html>