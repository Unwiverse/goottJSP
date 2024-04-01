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
        max-width: 600px;
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

    td input, textarea {
        width: 100%;
        padding: 8px;
        box-sizing: border-box;
    }

    td input[type="file"] {
        padding: 5px;
    }

    td input[type="submit"], td input[type="reset"] {
        padding: 8px 20px;
        background-color: #007bff;
        color: #fff;
        border: none;
        cursor: pointer;
    }

    td input[type="submit"]:hover, td input[type="reset"]:hover {
        background-color: #0056b3;
    }
</style>
</head>
<body>
		<div align="center">
			<c:set var="dto" value="${content }" />
			<hr>
				<h3>${dto.getUpload_writer() }의 게시판 수정 폼 페이지</h3>
			<hr>
			<br><br>
			
			<%-- 
				enctype="multipart/form-data"
				- 파일을 업로드 할 때는 꼭 해당 속성과 그 값을 넣어줘야 함.
				- 이 속성과 값을 넣으면 파일이 바이너리 정보로 해서 올바르게 처리(action페이지로 전송이 됨.
				- 이 속성과 속성값을 쓰기 위해선 method는 꼭 post 방식을 써야됨.
			--%>
			<form method="post" enctype="multipart/form-data"
			action="<%=request.getContextPath() %>/upload_modify_ok.go">
			
			<input type="hidden" name="upNo" value="${dto.getUpload_no() }">
			
			<table border="1">
				<tr>
					<th>작성자</th>
					<td> <input name="upload_writer" readonly value="${dto.getUpload_writer() }"></td> 
					<!-- 타입이 text인 경우 type 속성 생략 가능 -->
				</tr>
				
				<tr>
					<th>글 제목</th>
					<td> 
					<input name="upload_title" value="${dto.getUpload_title() }">
					</td>
				</tr>
			
				<tr>
					<th>글 내용</th>
					<td> 
					<textarea rows="7" cols="25" name="upload_cont">${dto.getUpload_cont() }
					</textarea>
					</td>
				</tr>
				
				<tr>
					<th>첨부 파일</th>
						<td> <input type="file" name="upload_file"></td>
				</tr>
				
				<tr>
					<th>글 비번</th>
						<td> <input type="password" name="upload_pwd"> </td>
				</tr>
				
				<tr>
				  <td colspan="2" align="center">
					<input type="submit" value="완료">&nbsp;&nbsp;&nbsp;
					<input type="reset" value="다시작성">
				  </td>
				</tr>
					
			</table>
				
			
			</form>
		</div>

</body>
</html>