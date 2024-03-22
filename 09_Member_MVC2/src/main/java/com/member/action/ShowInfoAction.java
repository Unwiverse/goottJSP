package com.member.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.member.model.MemberDAO;
import com.member.model.MemberDTO;

public class ShowInfoAction implements Action {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) {
		// 요청: 멤버 목록에서 클릭한 회원의 정보를 보여달라
		// 응답: member 테이블에서 클릭한 멤버 한 명 정보를 조회해 view 페이지로 이동시키는 비즈니스 로직.
		
		int memno = Integer.parseInt(request.getParameter("num").trim());
		
		MemberDAO dao = MemberDAO.getInstance();
		
		//member 테이블에서 선택한 멤버의 정보를 조회하는 메서드 호출
		MemberDTO member = dao.showInfo(memno);
		
		//member 테이블에서 클릭한 회원 정보를 view page로 이동시키기(바인딩)
		request.setAttribute("Member", member);
		
		
		return "member_showInfo.jsp";
	}

}
