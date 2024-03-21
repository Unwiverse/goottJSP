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
 * Servlet implementation class InsertServlet
 */
@WebServlet("/insert")
public class InsertServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public InsertServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		
		//1단계: 게시글 입력 폼 페이지에서 넘어온 데이터들을 받아줘야됨
		String board_writer = request.getParameter("writer").trim();
		String board_title = request.getParameter("title").trim();
		String board_cont = request.getParameter("content").trim();
		String board_pwd = request.getParameter("pwd").trim();
		
		//2단계: 1단계에서 넘어온 데이터들을 DTO 객체에 저장해주자.
		BoardDTO dto = new BoardDTO();
		
		dto.setBoard_writer(board_writer);
		dto.setBoard_title(board_title);
		dto.setBoard_cont(board_cont);
		dto.setBoard_pwd(board_pwd);
		
		//3단계: DTO객체를 DB에 전송해줘야함
		BoardDAO dao = BoardDAO.getInstance();
		int check = dao.insertBoard(dto);
		
		PrintWriter out= response.getWriter();
		
		if(check >0) {
			out.println("<script>");
			out.println("alert('됐다')");
			out.println("location.href='select.go'");
			out.println("</script>");
		} else {
			out.println("<script>");
			out.println("alert('안됐다')");
			out.println("history.back()");
			out.println("</script>");
		}
	}

}
