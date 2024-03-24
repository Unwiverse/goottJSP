<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    <%
    	int no = 236;
    	request.setAttribute("no", no);
    %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h2>여러가지 데이터 출력하기</h2>
	
	<h3>JSP 표현식: <%=no %><br>
		EL언어: ${no } <!--//request.setAttribute("no", no); 사용 시 변수 출력 -->
	</h3>
	<hr>
	
	<h3>${123+50 }<br></h3>
	<h3>\${123+50 }: ${123+50 }<br></h3>
	<h3>\${"방가방가"}: ${"방가방가"}<br></h3>
	<h3>${"20"+55 }<br></h3> <!--  "20"이 숫자로 취급됨(문자열 ==> 숫자 자동 변환) -->
	<h3>\${null +45}: ${null +45}<br></h3> <!-- 피연산자가 null 이면 0으로 처리가 됨 -->
	
		
</body>
</html>