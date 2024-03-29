<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%-- 
	★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★
	★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★
	
	# JSP 페이지 이동 : forward, redirect
	  - 웹 애플리케이션은 여러 기능을 합쳐 하나의 프로그램을 실행하는
	    구조로 되어 있음.
	    회원관리 기능, 게시판 관리 기능, 주문 관리 등에 대해 각각의
	    서블릿이 기능을 수행하게 되어 있음.
	  - 이 때 프로그램을 실행하다 보면 서블릿끼리 또는 서블릿과 JSP를
	    연동해서 작업을 해야 하는 경우가 생길수 밖에 없음. 예를
	    든다면 쇼핑몰의 경우 상품 관리 서블릿에서 조회된 상품을 화면에
	    표시하는 JSP 페이지는 각각 따로 존재하게 되어 있음.
	    따라서 사용자가 상품 조회를 요청하면 상품 관리 서블릿은 
	    데이터베이스에서 해당 상품에 대한 상품 정보를 조회한 후
	    다시 JSP에게 해당 상품 정보를 전달하여 상품 정보를 표시해
	    주어야 함. - 매우 중요함.
	    이러한 페이지 이동은 필수적이라고 보면 됨.
	    * 요청에 대한 추가 작업을 다른 서블릿에게 수행하게 함.
	    * 요청에 포함된 정보를 다른 서블릿이나 JSP와 공유할 수 있음.
	    * 요청에 정보를 포함시켜 다른 서블릿에게 전달 할 수 있음.
	    * 서블릿에서 JSP 페이지로 데이터를 전달하는 데 사용이 됨. - 아주 중요함.          
	          
	    1. forward 이동 방식 - 아주 중요함.
	       - request의 영역(scope)에 담긴 값이 유효함.
	       - 이동된 화면이 url 창에 안 보인다.
	         (사용자는 이동했는지 알 수 없음)
	       - 주로 정보(키)를 넘겨줄 때 사용함(중요).
	       - RequestDispathcer 객체를 사용함.
	         ==> forward() 메서드를 사용.
	         형식) RequestDispatcher rd = 
	         		request.getRequestDispatcher("이동위치");
	              rd.forward(request, response);
	    
	    2. redirect 이동 방식
	       - 클라이언트가 새로 페이지를 요청하는 것과 같은 방식으로 
	         페이지가 이동이 됨.
	       - 웹 브라우저에게 재요청하는 방식임.
	       - 일반적으로 변수 값을 넘겨줄 때 사용함(중요).
	       - request, response 값이 유효하지 않음.
	       - 이동된 페이지가 url 창에 나타남.
	       - 형식) response.sendRedirect("이동위치");
		
		 --%>
		 
		 <div align="center">
        <hr width="30%" color="red">
            <h2>페이지 이동 관련 내용</h2>
        <hr width="30%" color="red">
        <br><br>
        
        <h3>페이지 이동(forward)방식</h3>
        <form method="post" action="Ex06_01.jsp">
            <p>아이디 : <input type="text" name="id"></p>
            <p>비밀번호 : <input type="password" name="pwd"></p>
            
            <input type="submit" value="로그인">
        </form>

        <h3>페이지 이동(redirect)방식</h3>
        <form method="post" action="Ex06_02.jsp">
            <p>아이디 : <input type="text" name="id"></p>
            <p>비밀번호 : <input type="password" name="pwd"></p>

            <input type="submit" value="로그인">
        </form>
    </div>

		 	
					
				
	
	
</body>
</html>