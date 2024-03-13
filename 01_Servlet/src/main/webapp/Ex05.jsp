<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

	</head>
	<body>
		<div align="center">
			<h2>두 수 더하기(annotation(1:1) 등록)</h2>
			<form action="adder">
			<p>첫번째 수: <input type="text" name="num1"></p>
			<p>두번째 수: <input type="text" name="num2"></p>
			
			<input type="submit" value="더하기">
	
			</form>
			<br>
			<hr width="30%">
			<br>
			
			<h2>두 수 더하기(web.xml 등록)</h2>
			<form action="adder1">
				<p>첫번째 수: <input type="text" name="num1"></p>
				<p>두번째 수: <input type="text" name="num2"></p>
				
				<input type="submit" value="더하기">
			</from>
		</div>
		<%-- 서블릿 매핑 방법 2가지
			 1. annotation 등록
			 	-서블릿 클래스의 url-mapping에 등록.
			 	-1:1 매핑인 경우 씀.
			 	- 설정 파일이 필요없음.
			 	
			 2. 배포서술자(web.xml) 등록
			 	- 매핑할 서블릿이 많을 때 일괄처리에 유용함.
			 	- N:1 매핑인 경우에 씀.
			 	- web.xml 파일에 설정.
			 	- 향후 배울 MVC패턴에서 많이 쓰는 매핑방법.
			 	
			 ※ Servlet 매핑 목적
			 	- 처리 프로그램을 url에서 숨기는게 목적(보안)
			 	==> 파일명과 폴더멍(패키지)까지 숨김.
			 	- 보안을 목적으로 숨김.
		--%>
	</body>
	</html>