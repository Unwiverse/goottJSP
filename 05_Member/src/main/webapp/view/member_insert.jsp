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
		<h3>멤버 테이블 회원 등록 페이지</h3>
	
	<hr>
	<br><br>
	
	<form method="post" 
	action="<%=request.getContextPath() %>/insert_ok.go">
		<table border="1">
			<tr>
				<th>회원 아이디</th>
				<td>
					<input type="text" name="id">
				</td>
			</tr>
			
			<tr>
				<th>회원 이름</th>
				<td>
					<input type="text" name="name">
				</td>
			</tr>
			
			<tr>
				<th>회원 비번</th>
				<td>
					<input type="password" name="pwd">
				</td>
			</tr>
			
			<tr>
				<th>회원 나이</th>
				<td>
					<input type="text" name="age">
				</td>
			</tr>
			
			<tr>
				<th>회원 마일리지</th>
				<td>
					<input type="text" name="mileage">
				</td>
			</tr>
			
			<tr>
				<th>회원 직업</th>
				<td>
					<input type="text" name="job">
				</td>
			</tr>
			
			<tr>
				<th>회원 주소</th>
				<td>
					<input type="text" name="addr">
				</td>
			</tr>
			
			<tr>
						
			<td colspan="2" align="center">
				<input type="submit" value="등록">&nbsp;&nbsp;&nbsp;
				<input type="reset" value="취소">
				
			</td>
				</tr>
		
		</table>
		
	</form>


</div>

</body>
</html>