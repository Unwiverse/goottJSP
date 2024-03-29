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
	<form method="post" action="<%=request.getContextPath() %>/bbs_insert.go">
		<table border="1">
			<tr>
				<th>작성자</th> <th>제목</th> <th>내용</th>
				<th>비번</th> 
			</tr>
			
			<tr>
				<td><input type="text" name="writer"></td>
				<td><input type="text" name="title"></td>
				<td><input type="text" name="cont"></td>
				<td><input type="password" name="pwd"></td>
			</tr>
			
			<tr>
				<td colspan="2" align="center">
					<input type="submit" value="완료">&nbsp;&nbsp;&nbsp;
					<input type="reset" value="다시작성">
				</td>
			</tr>
			
		</table>
	</form>
	
		
	
	</div>

</body>
</html>