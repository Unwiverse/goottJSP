package com.admin.action;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.shop.controller.Action;
import com.shop.controller.ActionForward;

public class AdminLogoutAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// 상단의 로그아웃을 클릭하면 현재 모든 정보를(session) 종료시키는 비지니스 로직
		HttpSession session = request.getSession();
		
		session.invalidate(); //현재 쓰는 중인 모든 세션을 종료시키는 메서드
		
		ActionForward forward = new ActionForward();
		
		forward.setPath("main.jsp");
		forward.setRedirect(false);
		
		return forward;
		
	}

}
