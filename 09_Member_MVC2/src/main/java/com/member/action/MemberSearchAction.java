package com.member.action;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.member.model.MemberDAO;
import com.member.model.MemberDTO;

public class MemberSearchAction implements Action {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
		response.setContentType("text/html; charset=UTF-8");
		
		String search_field = request.getParameter("field").trim();
		String search_keyword = request.getParameter("keyword").trim();
		
		MemberDAO dao = MemberDAO.getInstance();
		
		List<MemberDTO> list = dao.MemberSearch(search_field, search_keyword);
		
		request.setAttribute("List", list);
		
		return "member_search.jsp";
	}

}
