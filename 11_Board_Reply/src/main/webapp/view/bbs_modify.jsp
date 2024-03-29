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
		<hr width="30%" color="marmoon">
	      <h3>JSP_BBS 답변형 게시판 게시글 수정 폼 페이지</h3>
	    <hr width="30%" color="marmoon">
	<form method="post" action="<%=request.getContextPath() %>/bbs_modify_ok.go">
		<c:set var="dto" value="${Modify }" />
		
		<input type="hidden" name="bbs_no" value="${dto.getBoard_no() }">
		<table border="1">
			<tr>
	            <th>작성자</th>
	            <td> <input type="text" name="writer" readonly
	                     value="${dto.getBoard_writer() }"> </td>
	         </tr>
	         
	         <tr>
	            <th>글제목</th>
	            <td> <input type="text" name="title"
	                     value="${dto.getBoard_title() }"> </td>
	         </tr>
	         
	         <tr>
	            <th>글내용</th>
	            <td> 
	               <textarea rows="7" cols="25" name="cont">${dto.getBoard_cont() }</textarea>
	            </td>
	         </tr>
	         
	         <tr>
	            <th>비밀번호</th>
	            <td> <input type="password" name="pwd"> </td>
	         </tr>
	         
	         <tr>
	            <td colspan="2" align="center">
	               <input type="submit" value="글수정">&nbsp;&nbsp;&nbsp;
	               <input type="reset" value="다시작성">
	            </td>
	         </tr>
			
		</table>
	</form>
	</div>

</body>
</html>