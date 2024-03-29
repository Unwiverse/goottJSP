package com.reply.action;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.reply.model.BbsDAO;
import com.reply.model.BbsDTO;

public class BbsModifyAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// get 방식으로 넘어온 글 번호에 해당하는 게시글을 jsp_bbs 테이블에서 조회해서 상세 내역을
		// 수정 폼 페이지로 넘기는 비지니스 로직
		int bbs_no = Integer.parseInt(request.getParameter("no").trim()) ;
		
		BbsDAO dao = BbsDAO.getInstance();
		
		BbsDTO content = dao.showBoard(bbs_no);
		
		request.setAttribute("Modify", content);
		
		ActionForward forward = new ActionForward();
		
		forward.setRedirect(false);
		forward.setPath("view/bbs_modify.jsp");
		
		return forward;
	}

}
