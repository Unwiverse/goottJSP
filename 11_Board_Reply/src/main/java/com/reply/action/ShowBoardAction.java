package com.reply.action;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.reply.model.BbsDAO;
import com.reply.model.BbsDTO;

public class ShowBoardAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
		int Bbs_no = Integer.parseInt(request.getParameter("num").trim());
		BbsDAO dao = BbsDAO.getInstance();
		
		BbsDTO a_bbs = dao.showBoard(Bbs_no);
		
		request.setAttribute("board", a_bbs);
		
		ActionForward forward = new ActionForward();
		
		forward.setRedirect(false);
		forward.setPath("view/bbs_showBoard.jsp");
		
		return forward;
	}

}
