<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
	function del(pnum) {
		let res = confirm("ㄹㅇ?");
		if(res) {
			location.href ="admin_product_delete.go?pnum="+pnum;
		}
		
	}

</script>
</head>
<body>
		<jsp:include page="../include/admin_header.jsp" />
			<hr>
				<h3>SHOP_PRODUCTS 테이블 상품 전체 목록</h3>
			<hr>
			<br><br>
			
			<table border="1">
				<tr bgcolor="#ffcc00">
					<th>제품 번호</th> <th>카테고리 코드</th> <th>제품명</th>
					<th>사진</th> <th>가격</th> <th>개수</th> <th>회사</th>
					<th>등록일</th> <th>수정&nbsp;&nbsp;&nbsp; | &nbsp;&nbsp;&nbsp;삭제</th>
				</tr>
				
				<c:set var="list" value="${ProductList }" />
				<c:if test="${!empty list }">
					<c:forEach items="${list }" var="dto">
						<tr>
							<td>${dto.getPnum() }</td>
							<td>${dto.getPcategory_fk() }</td>
							<td>${dto.getPname() }</td>
							<td>
								<img src="<%=request.getContextPath() %>/upload/${dto.getPimage() }"
									width="60" height="60">
							</td>
							<td>
								<fmt:formatNumber value="${dto.getPrice() }" />
							</td>
							
							<td> ${dto.getPqty() }</td>
							<td> ${dto.getPcompany() }</td>
							<td> ${dto.getPinputdate().substring(0, 10) }</td>
							<td> <a href="<%=request.getContextPath() %>/admin_product_modify.go?num=${dto.getPnum() }">
								수정&nbsp;&nbsp;&nbsp; | &nbsp;&nbsp;&nbsp;</a>
								 <a href="javascript:del(${dto.getPnum() })">삭제</a>
							</td>
						</tr>
					</c:forEach>
				</c:if>
				<c:if test="${empty list }">
					<tr>
						<td colspan="9" align="center">
							<h3>ㄴㄴ</h3>
						</td>
					</tr>
				</c:if>
						
			</table>
		
		<jsp:include page="../include/admin_footer.jsp" />

</body>
</html>