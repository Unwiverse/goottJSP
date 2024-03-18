package com.emp.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.emp.model.EmpDAO;

/**
 * Servlet implementation class DeleteServlet
 */
@WebServlet("/delete")
public class DeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public DeleteServlet() {
        super();
       
    }

	
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// get 방식으로 넘어온 사원번호에 해당하는 사원을 dB에서 삭제하는 비지니스 로직
		
		response.setContentType("text/html; charset=UTF-8");
		
		int emp_no = Integer.parseInt(request.getParameter("no").trim());
		
		EmpDAO dao = new EmpDAO();
		
		int check = dao.deleteEmp(emp_no);
		PrintWriter out = response.getWriter();
		
		if(check >0) {
			out.println("<script>");
			out.println("alert('사원정보삭제성')");
			out.println("location.href='select'");
			out.println("</script>");
		} else {
			out.println("<script>");
			out.println("alert('사원정보삭제실')");
			out.println("history.back()");
			out.println("</script>");
		}
		
	}

}
