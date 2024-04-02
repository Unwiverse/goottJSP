<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="http://code.jquery.com/jquery-3.7.1.js"></script>
<script type="text/javascript">
	$(function() {
		$(".submit").on("click", function() {
			let txt = $("form").serialize();  
			alert(txt);
		});
		
	});


</script>

</head>
<body>

		<div align="center">
			<form>
				<table border="1">
					<tr>
						<td colspan="2" align="center">
							<h2>폼 데이터를 쿼리 스트링으로 전달</h2>
						</td>
					</tr>
				
					<tr>
						<th>아이디</th>
						<td> <input name="id"></td>
					</tr>
					
					<tr>
						<th>비번</th>
						<td> <input type="password" name="pwd"></td>
					</tr>
					
					<tr>
						<th>나이</th>
						<td> <input name="age"></td>
					</tr>
					
					<tr>
						<th>연락처</th>
						<td> <input name="phone"></td>
					</tr>
					
					<tr>
						<td colspan="2" align="center">
							<input type="submit" value="전송" class="submit">&nbsp;&nbsp;
							<input type="reset" value="취소">
						</td>
					</tr>
				</table>
			</form>
		</div>
</body>
</html>