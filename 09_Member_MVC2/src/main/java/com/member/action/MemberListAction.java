package com.member.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.member.model.MemberDAO;
import com.member.model.MemberDTO;

public class MemberListAction implements Action {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) {
		// 요청: 회원 전체 리스트를 보여달라고 요청
		// 응답: member 테이블에서 전체 회원 목록을 조회해 view 페이지로 이동시키는 비즈니스 로직.
		
		MemberDAO dao = MemberDAO.getInstance();
		
		//전체 회원 목록을 조회하는 메서드를 호출
		List<MemberDTO> memberList = dao.getMemberList();
		//member 테이블에서 가져온 전체 회원 목록을
		//view page로 이동시키기(바인딩)
		request.setAttribute("List", memberList);
		
		return "member_list.jsp";
	}

}
