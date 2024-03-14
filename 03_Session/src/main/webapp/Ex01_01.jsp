<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
      <%
    	
    String user_id = request.getParameter("id").trim();
    String user_pwd = request.getParameter("pwd").trim();
    
    /*
    	원래는 dB의 회원 관리 테이블에서 폼에서 입력한 아이디와 입력한
    	비번이 일치하는지 확인해서 회원이면 메인 페이지로 이동
    */
    String db_id = "hong";
    String db_pwd = "1234";
    
    if(user_id.equals(db_id)) {
    	//아이디가 존재하는 경우
    	if(user_pwd.equals(db_pwd)) {
    		//회원인 경우 ==> 메인 페이지로 이동 ==> 페이지 이동
    			
    		// 특정 정보를 넘겨주고싶은 경우
    		session.setAttribute("name", "홍길동"); //jsp에선 session
    		session.setAttribute("phone", "010-1111-1234");
    		
    		//페이지 이동
    		RequestDispatcher rd = request.getRequestDispatcher("Ex01_02.jsp");
    		rd.forward(request, response);
    		
    	} else {
    		//아이디는 맞는데 비번이 틀린 경우
    		System.out.println("비번이 틀립니다");
    	}
    	
    } else {
    	//아이디가 없는 경우 or 잘못입력
    	System.out.println("아이디가 틀리거나 회원이 틀립니다");
    }
    	
    
    %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

</body>
</html>