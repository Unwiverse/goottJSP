package com.reply.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.reply.model.BbsDAO;
import com.reply.model.BbsDTO;

public class BbsInsertAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		// 요청: 글쓰기 폼 페이지에서 넘어온 데이터들을 board 테이블에 저장해라
		String board_writer = request.getParameter("writer").trim();
		String board_title = request.getParameter("title").trim();
		String board_cont = request.getParameter("cont").trim();
		String board_pwd = request.getParameter("pwd").trim();
		
		BbsDTO dto = new BbsDTO();
		dto.setBoard_writer(board_writer);
		dto.setBoard_title(board_title);
		dto.setBoard_cont(board_cont);
		dto.setBoard_pwd(board_pwd);
		
		BbsDAO dao = BbsDAO.getInstance();
		
		int check = dao.insertBoard(dto);
		
		try {
			PrintWriter out = response.getWriter();
			
			if(check >0) {
				out.println("<script>");
				out.println("alert('게시글 추가 성공')");
				out.println("location.href='bbs_list.go'");
				out.println("</script>");
				
			} else {
				out.println("<script>");
				out.println("alert('게시글 추가 실패')");
				out.println("history.back()");
				out.println("</script>");
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}

}
