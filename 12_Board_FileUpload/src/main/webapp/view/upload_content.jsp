<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
    body {
        font-family: Arial, sans-serif;
        background-color: #f7f7f7;
        margin: 0;
        padding: 20px;
    }

    .container {
        max-width: 800px;
        margin: 0 auto;
        background-color: #fff;
        padding: 20px;
        border-radius: 8px;
        box-shadow: 0 2px 5px rgba(0, 0, 0, 0.1);
    }

    table {
        width: 100%;
        margin-top: 20px;
        border-collapse: collapse;
    }

    th, td {
        padding: 10px;
        border: 1px solid #ccc;
    }

    th {
        background-color: #f7f7f7;
        font-weight: normal;
        text-align: left;
        width: 30%;
    }

    td {
        vertical-align: top;
    }

    textarea {
        width: 100%;
        padding: 8px;
        box-sizing: border-box;
    }

    input[type="button"] {
        padding: 8px 20px;
        background-color: #007bff;
        color: #fff;
        border: none;
        cursor: pointer;
        margin-top: 20px;
    }

    input[type="button"]:hover {
        background-color: #0056b3;
    }
</style>
</head>
<body>

		<div align="center">
			<c:set var="dto" value="${cont }" />
			<hr>
				<h3>${dto.getUpload_writer() }의 게시글 조회 페이지</h3>
			<hr>
			<br><br>
			<table border="1">
				<tr>
					<th>게시글 번호</th> 
					<td>${dto.getUpload_no() }</td>
				</tr>
				
				<tr>
					<th>작성자</th> 
					<td>${dto.getUpload_writer() }</td>
				</tr>
			
				<tr>
					<th>제목</th> 
					<td>${dto.getUpload_title() }</td>
				</tr>
				
				<tr>
					<th>내용</th> 
					<td>
						<textarea rows="7" cols="25">${dto.getUpload_cont() }</textarea>
					</td>
				</tr>
				
				<tr>
					<th>글 비번</th> 
					<td>
						<c:if test="${!empty dto.getUpload_pwd() }">
							<c:forEach begin="1" end="${dto.getUpload_pwd().length() }">
								*
							</c:forEach>
						</c:if>
					</td>
				
				</tr>
				<tr>
					<th>첨부파일</th>
					<c:if test="${!empty dto.getUpload_file() }">
						<td>
							<a href="<%=request.getContextPath() %>/upload${dto.getUpload_file() }"
								target="_blank">${dto.getUpload_file() }</a> 
						</td>
					</c:if>
				</tr>
				
				<tr>
					<th>글 조회수</th>
					<td>${dto.getUpload_hit() }</td>
				</tr>
				
				<tr>
					<c:if test="${!empty dto.getUpload_date() }">
						<th>작성일자</th>
						<td>${dto.getUpload_date() }</td>
					</c:if>
				</tr>
				
				<tr>
					<c:if test="${!empty dto.getUpload_update() }">
						<th>수정일자</th>
						<td>${dto.getUpload_update() }</td>
					</c:if>
				</tr>
				
				<c:if test="${empty dto }">
					<tr>
						<td colspan="2" align="center">
						<h3>내용없음</h3>
						</td>
					</tr>
					</c:if> 
			</table>
			
			<br><br>
			<input type="button" value="수정하기" onclick="location.href='upload_modify.go?no=${dto.getUpload_no() }'">
			
			<input type="button" value="삭제하기" 
					onclick="if(confirm('정말로 게시글을 없애겠음?')) {
					location.href='upload_delete.go?no=${dto.getUpload_no() }'
					}else {return;}">
					
			<input type="button" value="목록으로" onclick="location.href='upload_list.go'">
			
		</div>

</body>
</html>