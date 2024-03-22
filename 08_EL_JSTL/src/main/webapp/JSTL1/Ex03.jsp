<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    <%--JSTL 라이브러리를 쓰겟다고 선언 --%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <%-- 포맷팅 라이브러리를 쓰겠다고 선언 --%>
    <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
		<c:set var="coffee" value="${param.coffee_str }" />
		<c:set var="amount" value="${param.coffee_amount }" />
		<c:choose>
			<c:when test="${coffee == 1 }">
				<c:set var="str" value="아메리카노" />
				<c:set var="price" value="3000" />
			</c:when>
			
			<c:when test="${coffee == 2 }">
				<c:set var="str" value="렜비" />
				<c:set var="price" value="40000" />
			</c:when>
			
			<c:when test="${coffee == 3 }">
				<c:set var="str" value="고티카" />
				<c:set var="price" value="600000" />
			</c:when>
			
			<c:when test="${coffee == 4 }">
				<c:set var="str" value="팟쿠찌" />
				<c:set var="price" value="55000" />
			</c:when>
			
		</c:choose>
		<div align="center">
			<h2>커피 주문 내역</h2>
			<table border="1">
				<tr>
					<th>커피 종류</th>
					<td> ${str }</td>
				</tr>
			
				<tr>
					<th>커피 단가</th>
					<td> <fmt:formatNumber value="${price }" />원 </td>
				</tr>
				
				<tr>
					<th>커피 개수</th>
					<td> ${amount } </td>
				</tr>
				
				<tr>
					<th>공급가액</th>
					<td>
						<fmt:formatNumber value="${price *amount }" />원
					</td>
				</tr>
				
				<tr>
					<th>부가세액</th>
					<td>
						<fmt:formatNumber value="${(price*amount)*0.1 }"/>원
					</td>
				</tr>
				
				<tr>
					<th>총 금액</th>
					<td>
						<fmt:formatNumber value="${(price*amount)+(price*amount)*0.1}"/>
					</td>
				</tr>
			</table>
		
		</div>
		

		
		
</body>
</html>