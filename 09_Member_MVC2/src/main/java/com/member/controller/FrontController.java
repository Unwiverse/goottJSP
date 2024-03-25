package com.member.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.member.action.Action;
import com.member.action.DeleteAction;
import com.member.action.MemberInsertAction;
import com.member.action.MemberInsertOKAction;
import com.member.action.MemberListAction;
import com.member.action.MemberSearchAction;
import com.member.action.MemberUpdateAction;
import com.member.action.MemberUpdateOKAction;
import com.member.action.ShowInfoAction;

public class FrontController extends HttpServlet {
	private static final long serialVersionUID =1L;
	
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//한글 깨짐 방지
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		//getRequestURI(): "/프로젝트명/파일명(*.go)"라는 문자열을 반환해주는 메서드
		String uri = request.getRequestURI();
		System.out.println(uri);
		
		//현재 프로젝트명을 문자열로 반환해주는 메서드
		String path = request.getContextPath();
		System.out.println(path);
		
		String command = uri.substring(path.length()+1);
		System.out.println(command);
		
		Action action = null;
		
		
		if(command.equals("select.go")) { 
			action= new MemberListAction(); 
			
		} else if(command.equals("info.go")) {
			action = new ShowInfoAction();
		} else if(command.equals("delete.go")) {
			action = new DeleteAction();
		} else if(command.equals("insert.go")) {
			action= new MemberInsertAction();
		} else if(command.equals("insert_ok.go")) {
			action= new MemberInsertOKAction();
		} else if(command.equals("search.go")) {
			action = new MemberSearchAction();
		} else if(command.equals("update.go")) {
			action = new MemberUpdateAction();
		} else if(command.equals("update_ok.go")) {
			action = new MemberUpdateOKAction();
		}
			
		String viewpage = action.execute(request, response);
		
		request.getRequestDispatcher(viewpage).forward(request, response);
		
	}

}
