<%@page import="java.util.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%--JSTL 라이브러리를 쓰겟다고 선언 --%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <%-- 포맷팅 라이브러리를 쓰겠다고 선언 --%>
    <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%-- 
		<fmt:formatData> 태그 여러가지 속성
		
		-value: 포매팅될 날짜를 지정 속성.
		-type: 포매팅할 타입을 지정 속성.
		-	   * date: 날짜를 지정.
		-      * time: 시간 지정.
		-      * both: 날짜/시간 지정.
		- datestyle: 날짜의 출력 양식을 지정하는 태그. 값에는 full, long, medium, short 등이 가능함.
		- pattern: 직접 출력 양식을 지정하는 태그.
		- timeZone: 특정 나라 시간대로 시간을 설정하는 속성.
	--%>
	<h2>formatDate 예제</h2>
	
	<c:set var="now" value="<%=new Date() %>" />
	
	<fmt:formatDate value="${now }" type="date" dateStyle="full" /><br>
	
<hr>
	
	<fmt:formatDate value="${now }" type="date" dateStyle="long" /><br>
<hr>
	<fmt:formatDate value="${now }" type="date" dateStyle="medium" /><br>
<hr>
	<fmt:formatDate value="${now }" type="date" dateStyle="short" /><br>
<hr>
	<fmt:formatDate value="${now }" type="time" timeStyle="full" /><br>
<hr>
	<fmt:formatDate value="${now }" type="time" timeStyle="long" /><br>
<hr>
	<fmt:formatDate value="${now }" type="time" timeStyle="medium" /><br>
<hr>
	<fmt:formatDate value="${now }" type="time" timeStyle="short" /><br>
<hr>
	<fmt:formatDate value="${now }" type="both" dateStyle="short" timeStyle="short" /><br>
<hr>
	<fmt:formatDate value="${now }" type="both" dateStyle="medium" timeStyle="short" /><br>
<hr>
	<fmt:formatDate value="${now }" pattern="yyyy-MM-dd hh:mm:ss" /><br>
<hr>
	한국 현재 시간: 
	<fmt:formatDate value="${now }" type="both" dateStyle="full" timeStyle="full" /><br>
<hr>
	<fmt:timeZone value="America/LA">
	미국 현재 시간:
	<fmt:formatDate value="${now }" type="both" dateStyle="full" timeStyle="full" /><br>
	</fmt:timeZone>
<hr>


</body>
</html>