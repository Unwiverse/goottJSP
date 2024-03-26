package com.board.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.board.model.BoardDAO;
import com.board.model.BoardDTO;

public class ShowBoardAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		// 게시판에서 선택한 게시물의 상세내용 출력 요청
		// 응답: 게시판에서 선택한 게시물의 내용을 view page로 이동시키는 비즈니스 로직
		int board_no = Integer.parseInt(request.getParameter("no").trim());
		
		BoardDAO dao = BoardDAO.getInstance();
		
		BoardDTO content = dao.showBoard(board_no);
		
		request.setAttribute("content", content);
		
		ActionForward forward = new ActionForward();
		//System.out.println(forward);
		
		forward.setRedirect(false);
		forward.setPath("view/board_showContent.jsp");
		
		return forward; //주소값 리턴
	}

}
