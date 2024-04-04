<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<jsp:include page="../include/admin_header.jsp" />
		<hr>
			<h3>카테고리 테이블 전체 리스트 페이지</h3>
		<hr>
		<br><br>
		
		<table border="1">
			<tr bgcolor="#eeffee">
				<th>카테고리 번호</th> <th>카테고리 코드</th>
				<th>카테고리 이름</th> <th>카테고리 삭제</th>
			</tr> 
			
			<c:set var="list" value="${categoryList }" />
			<c:if test="${!empty list }">
				<c:forEach items="${list }" var="dto">
					<tr>
						<td> ${dto.getCategory_num() }</td>
						<td> ${dto.getCategory_code() }</td>
						<td> ${dto.getCategory_name() }</td>
						<td>
							<input type="button" value="카테고리 삭제"
								onclick="check(${dto.getCategory_num() })">
						</td>
					</tr>
				</c:forEach>
			</c:if>
			
			<c:if test="${empty list }">
				<tr>
					<td colspan="4" align="center">
						<h3>카테고리 전체 x</h3>
					</td>
				</tr>
			</c:if>
		</table>
</body>
</html>