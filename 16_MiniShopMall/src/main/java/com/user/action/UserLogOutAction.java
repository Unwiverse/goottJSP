package com.user.action;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.shop.controller.Action;
import com.shop.controller.ActionForward;

public class UserLogOutAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// 사용자가 로그아웃을 클릭하면 main(맨 처음) 페이지로 넘어가는 그거
		HttpSession session = request.getSession();
		
		session.invalidate(); //현재 쓰는 중인 모든 세션을 종료시키는 메서드
		
		ActionForward forward = new ActionForward();
		
		forward.setPath("main.jsp");
		forward.setRedirect(false);
		
		return forward;
	}

}
