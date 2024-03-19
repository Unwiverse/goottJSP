package com.member.controller;

import java.io.IOException;
import java.io.PrintWriter;

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
		//get 방식으로 넘어온 회원번호에 해당하는 회원을 member 테이블에서 삭제하는 비지니스 로직
		response.setContentType("text/html; charset=UTF-8");
		int member_no = Integer.parseInt(request.getParameter("num").trim());
		
		
		MemberDAO dao = MemberDAO.getInstance();
		
		int check = dao.deleteMember(member_no);
		
		PrintWriter out = response.getWriter();
		if(check >0) {
			dao.updateSequence(member_no);
			
			out.println("<script>");
			out.println("alert('콤플리트')");
			out.println("location.href='select.go'");
			out.println("</script>");
		} else {
			out.println("<script>");
			out.println("alert('언콤플리트')");
			out.println("history.back()");
			out.println("</script>");
		}
	}

}
