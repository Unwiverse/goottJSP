<%@page import="com.member.model.MemberDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%
    	String param_id= request.getParameter("paramId").trim();
    	MemberDAO dao = MemberDAO.getInstance();
    	int check = dao.checkMemberId(param_id);
    	//ajax로 응답해주기
    	out.println(check);
    %>
