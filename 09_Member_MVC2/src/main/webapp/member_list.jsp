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
	</div>
		
</body>
</html>