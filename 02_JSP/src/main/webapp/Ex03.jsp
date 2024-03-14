<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%-- 회원 가입 폼 페이지를 만들고, jsp 페이지에서 
	데이터를 받아 화면에 ㄱ 
	가입 정보: 아이디, 비번, 이름, 연락처, 주소, 거주지
	단, 거주지는 select 태그를 쓸 것 --%>
	<div align="center">
	<hr>
		<h2>회원가입 정보 입력란</h2>
	<hr>
	<br><br>
	<form method="post" action="Ex03_01.jsp">
	<table>
	<tr>
		<th>아이디</th>
		<td>
			<input type="text" name="id">
		</td>
		</tr>
	<tr>
		<th>비번</th>
		<td>
			<input type="password" name="pwd">
		</td>
		</tr>
	<tr>
		<th>이름</th>
		<td>
			<input type="text" name="name">
			</td>
		<tr>
		<th>연락처</th>
		<td>
			<input type="text" name="phone">
		</td>
		</tr>
		<tr>
		<th>주소</th>
		<td>
			<input type="text" name="addr">
		</td>
		</tr>
		
		<tr>
		<th>거주지</th>
		<td>
			<select name="inhabit">
		    <option value="option1">Option 1</option>
		    <option value="option2">Option 2</option>
		    <option value="option3">Option 3</option>
		    <option value="option4">Option 4</option>
		    <option value="option5">Option 5</option>
			</select>
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