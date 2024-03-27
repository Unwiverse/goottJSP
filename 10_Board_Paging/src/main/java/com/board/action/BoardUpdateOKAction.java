package com.board.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.board.model.BoardDAO;
import com.board.model.BoardDTO;

public class BoardUpdateOKAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		
		int board_no = Integer.parseInt(request.getParameter("board_no").trim());
		String board_writer = request.getParameter("writer").trim();
		String board_title = request.getParameter("title").trim();
		String board_cont = request.getParameter("cont").trim();
		
		BoardDTO dto = new BoardDTO();
		
		dto.setBoard_no(board_no);
		dto.setBoard_writer(board_writer);
		dto.setBoard_title(board_title);
		dto.setBoard_cont(board_cont);
		
		BoardDAO dao = BoardDAO.getInstance();
		
		int check = dao.updateBoard(dto);
		
		try {
			PrintWriter out = response.getWriter();
			if(check >0) {
				out.println("<script>");
				out.println("alert('됐음')");
				out.println("location.href='content.go?num="+dto.getBoard_no()+"'");
				out.println("</script>");
				
			} else {
				out.println("<script>");
				out.println("alert('안됐음')");
				out.println("history.back()");
				out.println("</script>");
				
			}
			
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		
		return null;
	}

}
