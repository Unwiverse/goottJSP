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
 * Servlet implementation class SearchServlet
 */
@WebServlet("/search.go")
public class SearchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SearchServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//검색어에 해당하는 게시글 관련 리스트를 board 테이블에서 조회해 view 페이지로 이동시키는 비지니스 로직
	request.setCharacterEncoding("UTF-8");
	
	String search_field = request.getParameter("field").trim();
	String search_keyword = request.getParameter("keyword").trim();
	
	BoardDAO dao = BoardDAO.getInstance();
	
	List<BoardDTO> searchList = dao.searchBoardList(search_field, search_keyword);
	
	request.setAttribute("Search", searchList);
	
	request.getRequestDispatcher("view/board_searchList.jsp")
	.forward(request, response);
		
	}

}
