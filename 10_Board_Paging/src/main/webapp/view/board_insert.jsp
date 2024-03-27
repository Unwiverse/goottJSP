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
				<h3>추가 게시글 작성</h3>
			<hr>
			<br><br>
		<form method="post" action="<%=request.getContextPath() %>/write_ok.go">
			<table border="1">
				<tr>
					<th>작성자</th>
						<td>
							<input type="text" name="writer">
						</td>
				</tr>
				
				<tr>
					<th>글제목</th>
						<td>
							<input type="text" name="title">
						</td>
				</tr>
				
				<tr> 
					<th>글내용</th>
						<td>
							<textarea rows="7" cols="25" name="cont"></textarea>
						</td>
				</tr>
				
					<tr>
					
					<th>글비번</th>
						<td>
							<input type="password" name="pwd">
						</td>
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