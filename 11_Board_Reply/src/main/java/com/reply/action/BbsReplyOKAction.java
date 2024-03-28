package com.reply.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.reply.model.BbsDAO;
import com.reply.model.BbsDTO;

public class BbsReplyOKAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// 답변글 폼 페이지에서 넘어온 데이터들을 받아서 jsp_bbs 테이블에 저장시키는 비지니스 로직
		String reply_writer = request.getParameter("reply_writer").trim(); 
		String reply_title = request.getParameter("reply_title").trim(); 
		String reply_cont = request.getParameter("reply_cont").trim(); 
		String reply_pwd = request.getParameter("reply_pwd").trim(); 
		
		//히든으로 넘어온 데잍들도 받자
		int board_no = Integer.parseInt(request.getParameter("no").trim());
		int board_group = Integer.parseInt(request.getParameter("group").trim());
		int board_step = Integer.parseInt(request.getParameter("step").trim());
		int board_indent = Integer.parseInt(request.getParameter("indent").trim());
		
		BbsDTO dto = new BbsDTO();
		
		dto.setBoard_no(board_no);
		dto.setBoard_writer(reply_writer);
		dto.setBoard_title(reply_title);
		dto.setBoard_cont(reply_cont);
		dto.setBoard_pwd(reply_pwd);
		dto.setBoard_group(board_group);
		dto.setBoard_step(board_step);
		dto.setBoard_indent(board_indent);
		
		BbsDAO dao = BbsDAO.getInstance();
		
		//만약 원글 답변에 원글이 있으면 해당 답변글의 step을 1 증가시키는 메서드 호출
		dao.replyUpdate(board_group, board_step);
		
		//답변글을 jsp_bbs 테이블에 저장시키는 메서드 호출
		int check = dao.replyBbs(dto);
		
		PrintWriter out =  response.getWriter();
		
		if(check >0) {
			out.println("<script>");
			out.println("alert('답변 글 등록 성공')");
			out.println("location.href='bbs_list.go'");
			out.println("</script>");
		} else {
			out.println("<script>");
			out.println("alert('답변 글 등록 실패')");
			out.println("history.back()");
			out.println("</script>");
		}
		return null;
	}

}
