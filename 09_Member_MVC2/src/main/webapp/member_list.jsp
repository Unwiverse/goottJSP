<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    <%@
    	taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"
     %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
  <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f8f9fa;
            margin: 20px;
        }
        h3 {
            color: #007bff;
            margin-bottom: 20px;
        }
        table {
            width: 80%;
            margin: 0 auto;
            border-collapse: collapse;
            border: 2px solid #007bff;
            background-color: #fff;
        }
        th, td {
            padding: 10px;
            border: 1px solid #ddd;
            text-align: center;
        }
        th {
            background-color: #007bff;
            color: white;
        }
        tr:nth-child(even) {
            background-color: #f2f2f2;
        }
        tr:hover {
            background-color: #cce5ff;
        }
        input[type="button"] {
            padding: 8px 20px;
            background-color: #007bff;
            color: white;
            border: none;
            cursor: pointer;
        }
        input[type="button"]:hover {
            background-color: #0056b3;
        }
        .search-form {
            margin-top: 20px;
            text-align: center;
        }
        select, input[type="text"], input[type="submit"] {
            padding: 8px;
            border-radius: 5px;
            border: 1px solid #ced4da;
        }
        input[type="submit"] {
            background-color: #007bff;
            color: white;
            border: none;
            cursor: pointer;
        }
        input[type="submit"]:hover {
            background-color: #0056b3;
        }
    </style>
</head>
<body>
	<div align="center">
		<hr>
			<h3>member 테이블 전체 회원 목록 페이지</h3>
		<hr>
		<br><br>
		
		<table border="1" width="500">
			<tr>
				<th>회원 번호</th>		<th>회원 이름</th>		
				<th>회원 직업</th>		<th>회원 가입일</th>	
				<th>상세 내역</th>	
			</tr>
			
			<tr>
			<c:set var="list" value="${List}" />
			<c:if test="${!empty list}" >
			<c:forEach items="${list}" var="dto">
				<tr>
					<td> ${dto.getNum() } </td>
					<td> ${dto.getMemname() }</td>
					<td> ${dto.getJob() }</td>
					<td> ${dto.getRegdate().substring(0, 10) }</td>
					<td> <input type="button" value="상세정보"
							onclick="location.href='info.go?num=${dto.getNum() }'">
					</td>
				</tr>
				</c:forEach>
				</c:if>
				<c:if test="${empty list}">
					<tr>
						<td colspan="5" align="center">
						<h3>ㅋㅋ 등ㅅㅣㄴ</h3>
						</td>
					</tr>
				</c:if>
				</table>
				<br><br> 
				<input type="button" value="회원등록"
									onclick="location.href='insert.go'">
									
				<br><br>
				<%-- 검색 관련 폼 --%>
				<form method="post" action="<%=request.getContextPath() %>/search.go">
					<select name="field">
						<option value="id">아이디</option>
						<option value="name">이름</option>
						<option value="job">직업</option>
						<option value="addr">주소</option>
				
				
			
				</select>
				<input type="text" name="keyword">
				<input type="submit" value="검색">
		
		
		
		</form>
	</div>
		
</body>
</html>