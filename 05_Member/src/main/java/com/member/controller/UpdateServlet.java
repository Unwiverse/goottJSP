package com.member.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import com.member.model.MemberDAO;
import com.member.model.MemberDTO;

/**
 * Servlet implementation class UpdateServlet
 */
@WebServlet("/update.go")
public class UpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//get방식으로 넘어온 회원번호에 해당하는 회원 정보를 DB에서 조회해서 수정 폼 페이지로 이동하는 비지니스 로직
		int num = Integer.parseInt(request.getParameter("num"));
		MemberDAO dao = MemberDAO.getInstance();
		
		MemberDTO dto =dao.contentMember(num);
		
		request.setAttribute("dto", dto);
		
		request.getRequestDispatcher("view/member_modify.jsp")
		.forward(request, response);
		
		
	}

}
