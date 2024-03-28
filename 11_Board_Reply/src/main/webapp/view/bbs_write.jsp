<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
        body {
            background-color: lightgreen; /* 배경색 설정 */
            text-align: center; /* 텍스트 가운데 정렬 */
        }
        table {
            margin: 20px auto; /* 표를 수평 가운데 정렬 */
            border-collapse: collapse; /* 테두리 겹치도록 설정 */
            width: 80%; /* 표의 너비 설정 */
        }
        th, td {
            padding: 10px; /* 셀 안의 여백 설정 */
            border: 1px solid black; /* 테두리 설정 */
        }
        img {
            display: block; /* 이미지를 블록 요소로 설정 */
            margin: 20px auto; /* 이미지를 수평 가운데 정렬 */
            max-width: 100%; /* 이미지 최대 너비 설정 */
            height: auto; /* 이미지 높이 자동으로 조정 */
        }
    </style>
</head>
<body>
	<div align="center">
	<form method="post" action="<%=request.getContextPath() %>/bbs_insert.go">
		<table border="1">
			<tr>
				<th>작성자</th> <th>제목</th> <th>내용</th>
				<th>비번</th> 
			</tr>
			
			<tr>
				<td><input type="text" name="writer"></td>
				<td><input type="text" name="title"></td>
				<td><input type="text" name="cont"></td>
				<td><input type="password" name="pwd"></td>
			</tr>
			
			<tr>
				<td colspan="2" align="center">
					<input type="submit" value="완료">&nbsp;&nbsp;&nbsp;
					<input type="reset" value="다시작성">
				</td>
			</tr>
			
		</table>
	</form>
	<img src="https://cdn.i-scmp.com/sites/default/files/d8/images/canvas/2022/09/15/04964401-037d-434c-88d4-765f2e8ddd1f_5b91181c.jpg"
		 width="1000" height="1000" alt="alt">
		
	
	</div>

</body>
</html>