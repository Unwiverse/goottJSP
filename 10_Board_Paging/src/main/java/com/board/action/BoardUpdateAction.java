package com.board.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.board.model.BoardDAO;
import com.board.model.BoardDTO;

public class BoardUpdateAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		response.setContentType("text/html; chaeset=UTF-8");
		int board_no = Integer.parseInt(request.getParameter("num").trim());
		
		BoardDAO dao = BoardDAO.getInstance();
		
		BoardDTO board = dao.showBoard(board_no);
		
		request.setAttribute("board", board);
		
		ActionForward forward = new ActionForward();
		
		forward.setRedirect(false);
		forward.setPath("view/board_update.jsp");
		
		return forward;
	}

}
