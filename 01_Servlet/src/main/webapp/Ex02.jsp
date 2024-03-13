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
		<hr width="30%" color="blue">
			<h2>로그인페이지</h2>
		<hr width="30%" color="blue">
		<br><br>
		<form method="get" action="LoginServlet">
		<table border="1">
		<tr>
			<th>아이디</th>
			<td>
				<input type="text" name="id">
				</td>
				</tr>
				
		<tr>
			<th>비밀번호</th>
			<td>
				<input type="password" name="pwd">
				</td>
				</tr>
				
				<tr>
				<td colspan="2" align="center">
				<input type="submit" value="로그인">&nbsp;&nbsp;
				<input type="reset" value="취소">
				</td>
				</tr>
				
		
		</table>
		<br>
		
		
		
		
		</form>
		<br>
		<hr>
		<br>
		<form method="get" action="Login">
		<table border="1">
		<tr>
			<th>아이디</th>
			<td>
				<input type="text" name="id">
				</td>
				</tr>
				
		<tr>
			<th>비밀번호</th>
			<td>
				<input type="password" name="pwd">
				</td>
				</tr>
				
				<tr>
				<td colspan="2" align="center">
				<input type="submit" value="로그인">&nbsp;&nbsp;
				<input type="reset" value="취소">
				</td>
				</tr>
				
		
		</table>
		<br>
		
		
		
		
		</form>
		</div>
</body>
</html>