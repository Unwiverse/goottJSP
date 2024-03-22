<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
		<h2>JSP 표현식으로 scope 내용 출력 </h2>
		<h3>
			pageContext: <%=pageContext.getAttribute("Res") %><br>
			
			request: <%=request.getAttribute("R") %><br> <%-- 한 번 받아준 페이지에서 전달이 끝남 --%>
			
			session: <%=session.getAttribute("S") %><br>
			
			application: <%=application.getAttribute("A") %>
		</h3>
		
		<hr>
		<h2>표현언어(EL)로 scope 내용 출력</h2>
		
		<h3>
			pageContext: ${pageScope.Res } == ${Res }<br>
			request: ${requestScope.R } == ${R }<br>
			session: ${sessionScope.S } == ${S }<br>
			application: ${applicationScope.A } == ${A }<br>
		</h3>
		<hr>
		<script type="text/javascript">
		location.href="Ex07.jsp";
		
		</script>
		

</body>
</html>