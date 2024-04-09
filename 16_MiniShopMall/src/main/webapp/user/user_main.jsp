<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
    
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
    .product-container {
        max-width: 900px;
        margin: 0 auto;
    }
    .product-list {
        display: flex;
        flex-wrap: wrap;
        justify-content: center;
        gap: 20px;
    }
    .product-card {
        text-align: center;
        width: 250px;
        padding: 15px;
        border: 1px solid #ccc;
        border-radius: 5px;
        background-color: #fff;
    }
    .product-card img {
        width: 120px;
        height: 120px;
        object-fit: cover;
        border-radius: 5px;
    }
    .product-title {
        margin-top: 10px;
        font-weight: bold;
    }
    .product-price {
        color: #007bff;
        font-weight: bold;
    }
    .product-point {
        color: #28a745;
    }
</style>
</head>
<body>
		<jsp:include page="../include/user_header.jsp" />
		
		   <div align="center">
		   	<h3>쇼핑몰입니다</h3>
		   	<c:set var="list" value="${productlist }" />
		   	
		   	<c:if test="${empty list }">
		   		<h4>제품 목록이 없습니다</h4>
		   	</c:if>
		   	
		   	<c:if test="${!empty list}">
		   		<hr>
		   			<h3>제품 목록</h3>
		   		<hr>
		   		<br>
		   		<table border="1">
		   			<tr>
		   				<c:forEach items="${list }" var="dto">
		   					<c:set var="count" value="${count+1 }" />
		   					<td align="center">
		   						<a href="<%=request.getContextPath() %>/user_product_view.go?pnum=${dto.getPnum() }">
		   							<img style="width: 60px; height: 60px"
		   							 src="<%=request.getContextPath() %>/upload/${dto.getPimage() }">
		   						</a>
		   						<br>
		   						${dto.getPname() }
		   						<br>
		   						<fmt:formatNumber value="${dto.getPrice() }" />원 <br>
		   						<fmt:formatNumber value="${dto.getPoint() }" var="commaPoint"/>
		   							 ${commaPoint } 포인트
		   					</td>
		   					
		   					<c:if test="${count %3 ==0 }">
		   						</tr>
		   						<tr>
		   					</c:if>
		   				</c:forEach>
		   			</tr>
		   		</table>
		   		
		   </c:if>
		   
		   </div>
		
		<jsp:include page="../include/user_footer.jsp" />

</body>
</html>