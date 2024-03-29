<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    <%@	taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
   
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
		<div align="center">
			<hr>
				<h3>게시물 조회 결과</h3>
			<hr>	
			<br><br>
			
			<table border="1">
				<tr>
					<th>게시물 번호</th> <th>작성자</th>
					<th>제목</th> <th>내용</th> <th>비번</th>
					<th>조회수</th> <th>작성일</th>
				</tr>
				
				<c:set var="content" value="${content }"/>
				<c:if test="${!empty content }">
					<tr>
						<td>${content.getBoard_no() }</td>
						<td>${content.getBoard_writer() }</td>
						<td>${content.getBoard_title() }</td>
						<td>${content.getBoard_cont() }</td>
						<td>${content.getBoard_pwd() }</td>
						<td>${content.getBoard_hit() }</td>
						<td>${content.getBoard_date() }</td>
					</tr>
				</c:if>
				<c:if test="${empty content }">
					<tr>
						<td>
						<h3>내용이 음슴 ㅋ</h3></td>
					</tr>
				</c:if>
					
			
			</table>
			<br><br>
			
		
			<input type="button" value="수정하기" 
			onclick="location.href='update.go?num=${content.getBoard_no()}'">
			
			<c:url var="deleteURL" value="delete_ok.go">
	       		<c:param name="number" value="${content.getBoard_no()}" />
	   	    </c:url>   
    	      		
	          <input type="button" value="회원삭제"
	          onclick="if(confirm('삭제하시겠습니까?')) {
	           location.href='${deleteURL}' 
	           } else {return;}">
			
			<input type="button" value="목록으로" onclick="location.href='select.go'">
			<hr>
		</div>

</body>
</html>