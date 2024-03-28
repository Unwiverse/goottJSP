package com.reply.action;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.reply.model.BbsDAO;
import com.reply.model.BbsDTO;

public class BbsListAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// jsp_bbs 테이블에 있는 전체 게시물을 조회해 view page로 이동시키는 비지니스 로직
		
		BbsDAO dao = BbsDAO.getInstance();
		
		List<BbsDTO> list = dao.getBbsList();
		
		request.setAttribute("List", list);
		
		ActionForward forward = new ActionForward();
		
		forward.setRedirect(false); //view page로 이동
		forward.setPath("view/bbs_list.jsp");
		
		return forward;
	}

}
