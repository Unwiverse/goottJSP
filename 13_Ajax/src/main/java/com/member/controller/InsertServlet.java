package com.member.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.member.model.CustDTO;
import com.member.model.MemberDAO;

/**
 * Servlet implementation class InsertServlet
 */
@WebServlet("/insert.go")
public class InsertServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public InsertServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 입력 폼 페이지에서 넘어온 데이터들을 customer 테이블에 저장하는 비지니스 로직
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		String user_id = request.getParameter("unn").trim();
		String user_name = request.getParameter("name").trim();
		int user_age = Integer.parseInt(request.getParameter("age").trim()); 
		String user_phone = request.getParameter("phone").trim();
		String user_addr = request.getParameter("addr").trim();
		
		CustDTO dto = new CustDTO();
		dto.setId(user_id);
		dto.setName(user_name);
		dto.setAge(user_age);
		dto.setPhone(user_phone);
		dto.setAddr(user_addr);
		
		MemberDAO dao = MemberDAO.getInstance();
		
		int check = dao.insertCustomer(dto);
		
		out.println(check);
		
		
		
	}

}
