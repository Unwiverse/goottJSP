<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet">
</head>

<body>
	<jsp:include page="../include/admin_header.jsp" />
	
	 <!-- 모달 버튼 -->
    <button type="button" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#exampleModal">
  		ㅋㅋㅋ
	</button>
	
		<hr>
			<h2>상품 등록 폼 페이지</h2>
		<hr>
		<br><br>
	
	<!-- Modal -->
	<div class="modal fade" id="exampleModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
	  <div class="modal-dialog">
	    <div class="modal-content">
	      <div class="modal-header">
	        <h5 class="modal-title" id="exampleModalLabel">Modal title</h5>
	        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
	      </div>
	      <div class="modal-body">
	      
	        <form method="post" enctype="multipart/form-data"
		action="<%=request.getContextPath() %>/admin_product_input_ok.go">
			<table border ="1">
				<c:set var="clist" value="${categoryList }" />
					<tr>
						<th>상품명</th>
						<td> <input name="p_name"></td>
					</tr>
					
					<tr>
						<th>카테고리 코드</th>
						<td> 
							<select name="p_category">
								<c:if test="${empty clist }">
									<option value="">:::카테고리 코드:::</option>
								</c:if>
								
								<c:if test="${!empty clist }">
									<c:forEach items="${clist }" var="dto">
										<option value="${dto.getCategory_code() }">
											${dto.getCategory_name() }[${dto.getCategory_code() }]
										</option>
									</c:forEach>
								</c:if>
							</select>
						</td>
					</tr>
					
					<tr>
						<th>상품 제조사</th>
							<td> <input name="p_company"> </td>
					</tr>
					
					<tr>
						<th>상품 이미지</th>
							<td> <input type="file" name="p_image"> </td>
					</tr>
					
					<tr>
						<th>상품 수량</th>
							<td> <input type="number" name="p_qty"
								min="1" max="99" value="1"> 
							</td>
					</tr>
					
					<tr>
						<th>상품 가격</th>
							<td> <input name="p_price"> </td>
					</tr>
					
					<tr>
						<th>상품 사양</th>
							<td>  
								<select name="p_spec">
								  <option value="none" selected>일반</option>
								  <option value="hit" >인기</option>
								  <option value="new" >신상</option>
								  <option value="recommend" >추천</option>
								</select>
							</td>
					</tr>
					
					<tr>
						<th>상품 소개</th>
							<td> 
								<textarea rows="7" cols="25" name="p_content"></textarea>
							 </td>
					</tr>
					
					<tr>
						<th>상품 포인트</th>
							<td> <input name="p_point"> </td>
					</tr>
					
					<tr>
						<td colspan="2" align="center">
						
						<input type="submit" value="상품 등록">&nbsp;&nbsp;&nbsp;
						<input type="reset" value="다시작성">
						</td>
					</tr>
			</table>
		</form>
		
	      </div>
	      <div class="modal-footer">
	        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">닫기</button>
	        <button type="button" class="btn btn-primary">완료</button>
	      </div>
	    </div>
	  </div>
	</div>
    
		
		
		
	
	<jsp:include page="../include/admin_footer.jsp" />
</body>
</html>