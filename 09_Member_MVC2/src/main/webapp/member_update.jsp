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
		<hr>
			<h3>ㅋㅋ</h3>
		<hr>
		
		<form method="post" action="<%=request.getContextPath() %>/update_ok.go">
		
		<table>
			 <tr>
            <td>번호</td>
            <td><input type="text" name="memno" readonly value="${member.num}"></td>
        </tr>
        <tr>
            <td>아이디</td>
            <td><input type="text" name="memid" readonly value="${member.memid}"></td>
        </tr>
        <tr>
            <td>이름</td>
            <td><input type="text" name ="name" readonly value="${member.memname}"></td>
        </tr>
        <tr>
            <td>나이</td>
            <td><input type="text" name ="age" value="${member.age}"></td>
        </tr>
        <tr>
            <td>비번</td>
            <td><input type="password" name ="pwd" value="${member.pwd}"></td>
        </tr>
        <tr>
            <td>마일리지</td>
            <td><input type="text" name ="mileage" value="${member.mileage}"></td>
        </tr>
        <tr>
            <td>직업</td>
            <td><input type="text" name ="job" value="${member.job}"></td>
        </tr>
        <tr>
            <td>주소</td>
            <td><input type="text" name ="addr" value="${member.addr}"></td>
        </tr>
        <tr>
            <td>가입날짜</td>
            <td><input type="text" name ="regdate" value="${member.regdate}"></td>
        </tr>
		
		<tr>
			<td colspan="8" align="center">
				<input type="submit" value="제출">
			</td>
		</tr>
		</table>
		</form>
		
	</div>

</body>
</html>