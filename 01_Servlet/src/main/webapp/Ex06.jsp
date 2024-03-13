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
	<h2>학생 점수 표기 페이지</h2>
	<hr>
	<br><br>
	<form method="post" action="score">
	<table border="1">
	
	<tr>
	<th>이름</th>
	<td>
		<input type="text" name="name">
	</td>
	</tr>
	
	<tr>
	<th>국어점수</th>
	<td>
		<input type="text" name="korean">
	</td>
	</tr>
	
	<tr>
	<th>영어점수</th>
	<td>
		<input type="text" name="english">
	</td>
	</tr>
	
	<tr>
	<th>수학점수</th>
	<td>
		<input type="text" name="math">
	</td>
	</tr>
	
	 <tr>
        <td colspan="2" align="center">
            <input type="submit" value="계산">
            <input type="reset" value="취소">
        </td>
    </tr>

	
	
	
	</table>
	
	</form>
	
	</div>
</body>
</html>