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

	<div align="center">
	   <hr width="30%" color="red">
	      <h3>BOARD 테이블 게시물 검색 리스트 페이지</h3>
	   <hr width="30%" color="red">
	   <br> <br>
	   
	   <table border="1" width="700">
	      <tr>
	         <td colspan="4" align="right">
	            전체 게시물 수 : ${totalRecord } 개
	         </td>
	      </tr>
	      
	      <tr>
	         <th>게시글 No.</th> <th>게시글 제목</th>
	         <th>게시글 조회수</th> <th>작성일자</th>
	      </tr>
	      
	      <c:set var="list" value="${List }" />
	      <c:if test="${!empty list }">
	         <c:forEach items="${list }" var="dto">
	            <tr>
	               <td> ${dto.getBoard_no() } </td>
	               <td> ${dto.getBoard_title() } </td>
	               <td> ${dto.getBoard_hit() } </td>
	               <td> ${dto.getBoard_date().substring(0, 10) } </td>
	            </tr>
	         </c:forEach>
	      </c:if>
	      
	      <c:if test="${empty list }">
	         <tr>
	            <td colspan="4" align="center">
	               <h3>전체 게시물 리스트가 없습니다.....</h3>
	            </td>
	         </tr>
	      </c:if>
	   </table>
	   
	   <br>
	   
	   <input type="button" value="게시물목록"
	      		onclick="location.href='select.go'">
	   
	   <br> <br>
	   
	   <%-- 페이징 처리 --%>
	   <c:if test="${page > block }">
	      <a href="search.go?page=1&field=${field }&keyword=${keyword }">[처음으로]</a>
	      <a href="search.go?page=${startBlock - 1 }&field=${field }&keyword=${keyword }">◀</a>
	   </c:if>
	   
	   <c:forEach begin="${startBlock }" end="${endBlock }" var="i">
	   
	      <c:if test="${i == page }">
	      	<b> <a href="search.go?page=${i }&field=${field }&keyword=${keyword }">[${i }]</a> </b>
	      </c:if>
	      
	      <c:if test="${i != page }">
	      	<a href="search.go?page=${i }&field=${field }&keyword=${keyword }">[${i }]</a>
	      </c:if>
	   
	   </c:forEach>
	   
	   <c:if test="${endBlock < allPage }">
	      <a href="search.go?page=${endBlock + 1 }&field=${field }&keyword=${keyword }">▶</a>
	      <a href="search.go?page=${allPage }&field=${field }&keyword=${keyword }">[맨 뒤로]</a>
	   </c:if>
	   
	</div>
</body>
</html>






