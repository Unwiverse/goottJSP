package com.emp.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.emp.model.EmpDAO;
import com.emp.model.EmpDTO;

/**
 * Servlet implementation class ContentServlet
 */
@WebServlet("/content")
public class ContentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ContentServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// get방식으로 넘어온 사원 번호에 해당하는 사원의 정보를 view 페이지로 넘기는 비지니스 로직
		int emp_no = Integer.parseInt(request.getParameter("no").trim());
		
		EmpDAO dao = new EmpDAO();
		
		EmpDTO cont = dao.contentEmp(emp_no);
		
		request.setAttribute("Content", cont);
		
		RequestDispatcher rd= 
		request.getRequestDispatcher("view/emp_content.jsp");
		
		rd.forward(request, response);
		
		
		
	}

}
