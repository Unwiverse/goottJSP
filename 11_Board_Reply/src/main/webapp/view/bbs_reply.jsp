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
	
	<c:set var="dto" value="${Reply }" />
	<form method="post" action="<%=request.getContextPath() %>/bbs_reply_ok.go">
		<input type="hidden" name="no" value="${dto.getBoard_no() }">
		<input type="hidden" name="group" value="${dto.getBoard_group() }">
		<input type="hidden" name="step" value="${dto.getBoard_step() }">
		<input type="hidden" name="indent" value="${dto.getBoard_indent() }">
		<table border="1">
			<tr>
				<th>작성자</th> <th>제목</th> <th>내용</th>
				<th>비번</th> 
			</tr>
			
			<tr>
				<td><input type="text" name="reply_writer" value="${dto.getBoard_writer() }"></td>
				<td><input type="text" name="reply_title" value="(Re)${dto.getBoard_title() }"></td>
				<td><input type="text" name="reply_cont" value="${dto.getBoard_cont() }"></td>
				<td><input type="password" name="reply_pwd"></td>
			</tr>
			
			<tr>
				<td colspan="2" align="center">
					<input type="submit" value="답변글">&nbsp;&nbsp;&nbsp;
					<input type="reset" value="다시작성">
				</td>
			</tr>
			
		</table>
	</form>
	</div>

</body>
</html>