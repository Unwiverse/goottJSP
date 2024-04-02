<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    <%
    	request.setCharacterEncoding("UTF-8");
    
    	response.setContentType("text/html; charset=UTF-8");
    	
    	String req_param = request.getParameter("param").trim();
    	
    	System.out.println("요청 param: "+req_param);
    	
    	//요청에 대해 응답을 해줘야 함.
    	out.println("ㅎㅇㅇ");    
    %>
    
