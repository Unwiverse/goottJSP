<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>insert title</title>
</head>
<body>
	<div align="center">
		<hr>
			<h3>미니 쇼핑몰 관리자 로그인 페이지</h3>
		<hr>
		<br><br>
		
		<form method="post" action="<%=request.getContextPath() %>/admin_login_ok.go">
			<table border="1">
				<tr>
					<th>관리자 id</th>
					<td> <input type="text" name="admin_id"></td>
				</tr>
				
				<tr>
					<th>관리자 pwd</th>
					<td> <input type="password" name="admin_pwd"></td>
				</tr>
				
				<tr>
					<td colspan="2" align="center">
						<input type="submit" value="로그인">&nbsp;&nbsp;
						<input type="reset" value="다시작성">
					</td>
				</tr>
			</table>
		</form>
	
	</div>

</body>
</html>