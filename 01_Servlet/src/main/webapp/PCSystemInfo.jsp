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
		<h2>PC 정보 입력란</h2>
		<hr>
		<br><br>
		<form method="post" action="PC">
			<table border="1">
			<tr>
			<th>CPU 제품명</th>
			<td>
				<input type="text" name="CPU"> 
			</td>
			</tr>
			
			<tr>
			<th>MB 제품명</th>
			<td> 
				<input type="text" name="M/B">
			</td>
			</tr>
			
			<tr>
			<th>VGA 제품명</th>
			<td>
				<input type="text" name="VGA">
			</td>
			</tr>
			
			<tr>
			<th>PSU 제품명</th>
			<td>
				<input type="text" name="PSU">
			</td>
			</tr>
			
			<tr>
			<th>SSD 제품명</th>
			<td>
				<input type="text" name="SSD">
			</td>
			</tr>
			
			<tr>
			<th>HDD 장착 여부</th>
			<td>
			<input type="radio" name="HDD" value="yes">
            <label for="yes">ㅇㅇ</label><br>
            
            <input type="radio" name="HDD" value="no">
            <label for="no">ㄴㄴ</label><br>
			</td>
			</tr>
			
			<tr>
					<td colspan="2" align="center">
						<input type="submit" value="완료">&nbsp;&nbsp;&nbsp;
						<input type="reset" value="리셋">
						
			</td>
			</tr>
			
			</table>
			
			
		</form>
		
		</div>
</body>
</html>