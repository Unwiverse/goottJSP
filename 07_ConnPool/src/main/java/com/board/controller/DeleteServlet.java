package com.board.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.board.model.BoardDAO;

/**
 * Servlet implementation class DeleteServlet
 */
@WebServlet("/delete")
public class DeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//삭제 폼 페이지에서 넘어온 글번호에 해당하는 게시글을 board 테이블에서 삭제하는 비지니스 로직
		response.setContentType("text/html; charset=UTF-8");
		
		int board_no = Integer.parseInt(request.getParameter("no").trim());
		String board_pwd = request.getParameter("pwd");
		
		BoardDAO dao = BoardDAO.getInstance();
		
		int check = dao.deleteBoard(board_no, board_pwd);
		
		PrintWriter out = response.getWriter();
		
		if(check>0) {
			dao.refreshingBoard(board_no);
			
			out.println("<script>");
			out.println("alert('게시글 삭제 성공')");
			out.println("location.href='select.go'");
			out.println("</script>");
			
		} else if(check == -1) { //비밀번호 틀렸을 때
			out.println("<script>");
			out.println("alert('비번 틀림')");
			out.println("history.back()");
			out.println("</script>");
		} else { //이도저도 아닐 때
			out.println("<script>");
			out.println("alert('게시글 삭제 실패')");
			out.println("history.back()");
			out.println("</script>");
		}
	}

}
