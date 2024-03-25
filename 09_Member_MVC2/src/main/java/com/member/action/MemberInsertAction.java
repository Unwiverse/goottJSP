package com.member.action;

import java.lang.reflect.Member;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.member.model.MemberDAO;

public class MemberInsertAction implements Action {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) {
		// 회원 등록 폼 페이지로 이동시키는 비지니스 로직
		
		
		return "member_insert.jsp";
	}

}
