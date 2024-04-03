<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>준아TV 화이팅</title>
</head>
<body>
	<div align="center">
		<hr>
			<h2>새 글 작성 페이지</h2>
		<hr>
		<br><br>
		<form method="post" action="<%=request.getContextPath() %>/tbl_write_ok.go">
			<table border="1">
				<tr>
					<th>작성자</th> <th>제목</th> <th>내용</th>
					<th>비번</th>
				</tr>
				
				<tr>
					<td>
						<input type="text" name="writer">
					</td>
					<td>
						<input type="text" name="title">	
					</td>
					<td>
						<textarea name="content">
						</textarea>
					</td>
					<td>
						<input type="password" name="pwd">
					</td>
				</tr>
			</table>
			<br><br>
			
			<input type="submit" value="ㄱㄱ">&nbsp;&nbsp;&nbsp;
			<input type="reset" value="다시">
		</form>
	</div>

</body>
</html>