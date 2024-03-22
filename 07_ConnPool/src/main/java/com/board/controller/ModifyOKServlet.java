package com.board.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.board.model.BoardDAO;
import com.board.model.BoardDTO;

/**
 * Servlet implementation class ModifyOKServlet
 */
@WebServlet("/modify_ok")
public class ModifyOKServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ModifyOKServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//수정 폼 페이지에서 넘어온 데이터들을 board 테이블에 수정시키는 비지니스 로직
		
		//한글깨짐 방지 작업
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");

		//1단계: 수정 폼 페이지에서 넘어온 데이터들을 받아주자.
		String board_writer = request.getParameter("writer").trim();
		String board_title = request.getParameter("title").trim();
		String board_content = request.getParameter("content").trim();
		String board_pwd = request.getParameter("pwd").trim();
		//type="hidden"으로 설정된데이터들도 받아줘야한다.
		int board_no=  Integer.parseInt(request.getParameter("num").trim());
		String db_pwd = request.getParameter("db_pwd").trim();
		
		//2단계: 1단계에서 넘어온 데이터들을 dTO 객체에 저장해주자.
		BoardDTO dto = new BoardDTO();
		
		dto.setBoard_no(board_no);
		dto.setBoard_writer(board_writer);
		dto.setBoard_title(board_title);
		dto.setBoard_cont(board_content);
		dto.setBoard_pwd(board_pwd);
		
		BoardDAO dao = BoardDAO.getInstance();
		
		PrintWriter out = response.getWriter();
		
		if(board_pwd.equals(db_pwd)) {
			int check = dao.updateBoard(dto);
			
			if(check>0) {
				out.println("<script>");
				out.println("alert('게시글 수정 성공')");
				out.println("location.href='content.go?no="+dto.getBoard_no()+"'");
				out.println("</script>");
			} else {
				out.println("<script>");
				out.println("alert('게시글 수정 실패')");
				out.println("history.back()");
				out.println("</script>");
			}
		} else {
			//비번 틀림
			out.println("<script>");
			out.println("alert('비번 틀림')");
			out.println("history.back()");
			out.println("</script>");
			
		}
		
	}

}
