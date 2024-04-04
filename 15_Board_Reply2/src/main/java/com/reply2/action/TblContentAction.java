package com.reply2.action;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.reply.model.TblDAO;
import com.reply.model.TblDTO;

public class TblContentAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// get 방식으로 넘어온 글번호에 해당하는 ㅔㄱ시글을 tbl_board 테이블에서 조회해 내역을 view 페이지로
		// 넘기는 지비스니 직로
		int board_no = Integer.parseInt(request.getParameter("no").trim());
		
		TblDAO dao = TblDAO.getInstance();
		
		TblDTO content = dao.getBoardContent(board_no); //참조변수 주소값 생성
		
		request.setAttribute("content", content); //참조변수 주소값
		
		ActionForward forward =new ActionForward();
		
		forward.setRedirect(false);
		forward.setPath("view/tbl_content.jsp");
		
		return forward;
	}

}
