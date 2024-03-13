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
		<h2>회원 정보 입력 폼 페이지</h2>
		<hr>
		<br><br>
		<form method="post" action="join">
			<table border ="1">
				<tr>
				<th>회원 아이디</th>
				<td>
					<input type="text" name="id">
				</td>
				</tr>
				
				<tr>
				<th>회원 비번</th>
				<td>
					<input type="password" name="pwd">
				</td>
				</tr>
				
				<tr>
				<th>회원 이름</th>
				<td>
					<input type="text" name="name">
				</td>
				</tr>
				
				<tr>
				<th>회원 연락처</th>
				<td>
					<input type="text" name="phone">
				</td>
				</tr>
				
				<tr>
				<th>회원 주소</th>
				<td>
					<input type="text" name="addr">
				</td>
				</tr>
				
				<tr>
				<th>회원 취미</th>
				<td>
					<%-- type="checkbox"인 경우는  name속성에 들어있는 
					hobby라는 이름이 변수명이 아니라 배열명이 된다
					==> 데이터가 여러개가 선택될수있기 때문 --%>
					<input type="checkbox" name="hobby" value="여행">여행&nbsp;&nbsp;&nbsp;
					<input type="checkbox" name="hobby" value="독서">독서&nbsp;&nbsp;&nbsp;
					<input type="checkbox" name="hobby" value="운동">운동<br>
					<input type="checkbox" name="hobby" value="게임">게임&nbsp;&nbsp;&nbsp;
					
					<input type="checkbox" name="hobby" value="잠">잠&nbsp;&nbsp;&nbsp;
				</td>
				</tr>
				
				<tr>
					<td colspan="2" align="center">
						<input type="submit" value="회원가입">&nbsp;&nbsp;&nbsp;
						<input type="reset" value="다시작성">
						
			</td>
			</tr>
			</table>
		
		</form>
		
		</div>
</body>
</html>