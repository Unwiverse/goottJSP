package com.reply2.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.reply.model.ReplyDTO;
import com.reply.model.TblDAO;

public class ReplyInsertOkAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// 답변글 폼 페이지에서 넘어온 데이터들을 tbl_reply 테이블에 저장하는 비지니스 로직.
		
		String reply_writer = request.getParameter("writer").trim();
		String reply_content = request.getParameter("content").trim();
		int board_no = Integer.parseInt(request.getParameter("bno").trim());
		
		ReplyDTO dto = new ReplyDTO();
		
		dto.setBno(board_no);
		dto.setRewriter(reply_writer);
		dto.setRecont(reply_content);
		
		TblDAO dao = TblDAO.getInstance();
		
		int check = dao.replyInsert(dto);
		
		PrintWriter out = response.getWriter();
		
		//결과값을 Ajax에 보내
		out.println(check);
		
		return null;
	}

}
