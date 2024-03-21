package com.board.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.board.model.BoardDAO;
import com.board.model.BoardDTO;

/**
 * Servlet implementation class SelectServlet
 */
@WebServlet("/select.go")
public class SelectServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public SelectServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//board 테이블에서 게시글 전체 목록을 조회한 게시글 목록을 view page로 이동시키는 비지니스 로직
		BoardDAO dao = BoardDAO.getInstance();
		
		//게시물의 전체 리스트를 조회하는 메서드 호출
		List<BoardDTO> boardList = dao.getBoardList();
		
		//조회한 게시물의 전체 목록을 view 페이지로 이동
		request.setAttribute("List", boardList);
		
		request.getRequestDispatcher("view/board_list.jsp").forward(request, response);
	}

}
