<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>
<style>
	#shop_title {
		float: right;
		margin-right: 200px;
		word-spacing : 10px;
		clear: both;
	 }
	 a {
	 	text-decoration: none;
	 }
	 ul li {
	 	list-style: none;
	 	display:inline-block;
	 	margin-left: 80px;
	 }
	 td {
	 	text-align: center;
	 }
</style>
</head>
<body>
	<div align="center">
		<hr>
			<h3>관리자 페이지</h3>
		
		
		<div id="shop_title" class="container">
			<a href="#">${adminName}님 일해라</a>
			<a href="<%=request.getContextPath()%>/admin_logout.go">로그아웃</a>
		</div>
		<br>
		<hr>
		<br>
		<table width="800">
			<tr>
				<td>
					<a href="<%=request.getContextPath() %>/admin_main.go">관리자 홈</a>
				</td>
				
				<td>
					<a href="<%=request.getContextPath() %>/admin_category_input.go">카테고리 등록</a>
				</td>
				
				<td>
					<a href="<%=request.getContextPath() %>/admin_category_list.go">카테고리 목록</a>
				</td>
				
				<td>
					<a href="<%=request.getContextPath() %>/admin_product_input.go">상품 등록</a>
				</td>
				
				<td>
					<a href="<%=request.getContextPath() %>/admin_product_list.go">상품 목록</a>
				</td>
			</tr>
		</table>
		<hr>
		<br>
	

</body>
</html>