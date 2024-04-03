package com.member.controller;

import java.io.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.member.model.MemberDAO;

/**
 * Servlet implementation class DeleteServlet
 */
@WebServlet("/delete.go")
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
		// 넘겨받은 고객 번호에 맞는 사람을 customer 테이블에서 없애버리는 비지니스 로직
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8"); 
		response.setContentType("application/x-json; charset=UTF-8");
		
		PrintWriter out = response.getWriter();
		int user_no = Integer.parseInt(request.getParameter("no").trim());
		
		MemberDAO dao = MemberDAO.getInstance(); //DAO에서 만든 메서드를 실행시킬 MemberDAO 참조변수 생성
		
		int check = dao.deleteCustomer(user_no);
		
		out.println(check);
		
	}

}
