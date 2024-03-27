package com.board.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.board.model.BoardDAO;

public class DeleteBoardAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		response.setContentType("text/html; charset=UTF-8");
		
		int board_no = Integer.parseInt(request.getParameter("number").trim());
		BoardDAO dao = BoardDAO.getInstance();
		
		int check = dao.deleteBoard(board_no);
		
		try {
			PrintWriter out = response.getWriter();
			
			if(check> 0) {
				dao.updateBoardSequence(board_no);
				out.println("<script>");
				out.println("alert('ㅇㅇ')");
				out.println("location.href='select.go'");
				out.println("</script>");
				
			} else {
				out.println("<script>");
				out.println("alert('ㄴㄴ')");
				out.println("history.back()");
				out.println("</script>");
			}
			
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		
		return null;
	}

}
