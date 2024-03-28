package com.reply.action;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.reply.model.BbsDAO;
import com.reply.model.BbsDTO;

public class BbsReplyAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
		/*
		get 방식으로 넘어온 글번호에 해당하는 게시글을 jsp_bbs 테이블에서 조회해 
		게시글 상세내역을 답변글 폼 페이지로 이동시키는 비즈니스 로직.
		*/
		int board_no = Integer.parseInt(request.getParameter("no").trim()); 
		BbsDAO dao = BbsDAO.getInstance();
		
		BbsDTO content = dao.showBoard(board_no);
		
		request.setAttribute("Reply", content);
		
		ActionForward forward = new ActionForward();
		
		forward.setRedirect(false);
		forward.setPath("view/bbs_reply.jsp");
		
		return forward;
	}

}
