package com.member.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import com.member.model.MemberDAO;
import com.member.model.MemberDTO;


@WebServlet("/content.go")
public class ContentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public ContentServlet() {
        super();
        
    }

	
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int mem_no = Integer.parseInt(request.getParameter("num").trim());
		
		MemberDAO dao = MemberDAO.getInstance();
		
		MemberDTO member = dao.contentMember(mem_no);
		
		request.setAttribute("member", member);
		
		request.getRequestDispatcher("view/member_content.jsp").forward(request, response);
	}

}
