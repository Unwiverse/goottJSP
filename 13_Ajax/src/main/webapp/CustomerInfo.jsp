<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="http://code.jquery.com/jquery-3.7.1.js"></script>
<script type="text/javascript" src="js/customer.js">
	

</script>
<style type="text/css">
	span {
		width: 150px;
		color:red;
	}
	input {
		border: 1px solid gray;
	}
	table {
		width: 85%;
	}
	.table_bg {
		background-color: pink;
	}
	th, td {
		border: 1px solid gray;
		text-align: center;
		padding: 3px;
	}

</style>
</head>
<body>
		<div align="center">
			<hr>
				<h2>CUSTMOMER 테이블 회원 정보 페이지</h2>
			<hr>
			<br><br>
			<form method="post" name="inForm" id="inForm">
			
				<table>
					<tr class="table_bg">
						<th>아이디</th> <th>이름</th>
						<th>나이</th> <th>연락처</th> <th>주소</th>
					</tr>
					
					<tr>
						<td>
							<input type="text" name="unn" id="unn" size="10">
							<span>중복여부</span>
						</td>
						
						<td>
							<input type="text" name="name" id="name" size="10">
						</td>
						
						<td>
							<input type="text" name="age" id="age" size="3">
						</td>
						
						<td>
							<input type="text" name="phone" id="phone">
						</td>
						
						<td>
							<input type="text" name="addr" id="addr" size="20">
						</td>
					</tr>
					
					<tr>
						<td colspan="5" align="center">
							<input type="button" value="등록하기" id="btn">
						</td>
					</tr>
				</table>
			</form>
			<br><br>
			<hr>
			<br><br>
			
			<h2>고객 리스트</h2>
				<table border="1" id="listTable">
					<tr>
						<th>번호</th> <th>아이디</th> <th>이름</th>
						<th>나이</th> <th>연락처</th> <th>주소</th> <th>삭제</th>
					</tr>
				</table>
		</div>

</body>
</html>