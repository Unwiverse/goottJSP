package com.reply2.action;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.reply.model.TblDTO;
import com.reply.model.TblDAO;

public class TblListAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// tbl_board 테이블의 전체 게시물을 조회해 view page로 이동시키는 비지니스 로직.
		TblDAO dao = TblDAO.getInstance();
		
		List<TblDTO> list = dao.showBoard();
		
		request.setAttribute("list", list);
		
		ActionForward forward = new ActionForward();
		
		forward.setRedirect(false);
		forward.setPath("view/tbl_list.jsp");
		
		return forward;
	}

}
